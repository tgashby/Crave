package edu.calpoly.csc409.crave.fragments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.*;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.widget.*;
import edu.calpoly.csc409.crave.customviews.PlaceListAdapter;
import edu.calpoly.csc409.crave.customviews.PlaceView;
import edu.calpoly.csc409.crave.pojos.Place;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.*;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.calpoly.csc409.crave.R;
import edu.calpoly.csc409.crave.activities.MainActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NearMeFragment extends Fragment {
	private ListView mPlacesListView;
    private PlaceListAdapter mPlaceListAdapter;
    private ArrayList<Place> mPlaceList;
    private boolean mUseGPS;
    private Location mLoc;

	// Places API constants
	private static final String PLACES_API_KEY = "AIzaSyD6sNTujHh7moB345pdeqk5XBCO4j32l_s";
	private static final int SEARCH_RADIUS = 2000;
	private static final String PLACE_TYPES = "food|bar|store";
	
	// Web Constants
	private static final int STATUS_OK = 200;

    private static final double MET_TO_MILES = 0.000621371;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_near_me,
				container, false);
		
		String foodStr = getArguments().getString(MainActivity.FOOD_STRING_KEY).trim();

        mPlacesListView = (ListView)rootView.findViewById(R.id.near_me_places_list);
        mPlacesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LatLng latLng = ((PlaceView)view).getPlace().getLatLng();
                String uri = String.format(Locale.ENGLISH,
                 "http://maps.google.com/maps?daddr=%f,%f(%s)", latLng.latitude, latLng.longitude, ((PlaceView)view).getPlace().getName());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                getActivity().startActivity(intent);
            }
        });

        mPlaceList = new ArrayList<Place>();

        mPlaceListAdapter = new PlaceListAdapter(getActivity(), mPlaceList);
        mPlacesListView.setAdapter(mPlaceListAdapter);

        LocationManager locManager = (LocationManager)this.getActivity()
                .getSystemService(Context.LOCATION_SERVICE);

		mUseGPS = false;
        if (locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            mUseGPS = true;
        }
		
		try {
            if (mUseGPS) {
                mLoc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }

            // This happens if GPS disabled or something else went wrong
            if (mLoc == null) {
                mLoc = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

			String placesSearchStr = "https://maps.googleapis.com/maps/api/place/nearbysearch/" +
				    "json?location="+mLoc.getLatitude()+","+mLoc.getLongitude()+
				    "&keyword=" + URLEncoder.encode(foodStr, "UTF-8") +
				    "&radius=" + SEARCH_RADIUS + "&sensor=true" +
				    "&types=" + URLEncoder.encode(PLACE_TYPES, "UTF-8") +
				    "&key=" + PLACES_API_KEY;

            Toast.makeText(getActivity(), "Getting Places Near You...", Toast.LENGTH_LONG).show();
			new GetPlaces().execute(placesSearchStr);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
				
		return rootView;
	}

    private class GetPlaces extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... placesURL) {
			StringBuilder placesBuilder = new StringBuilder();
			
			for (String placeSearchURL : placesURL) {
				HttpClient placesClient = new DefaultHttpClient();
				
				try {
					HttpResponse placesResponse = 
					 executeGetRequest(placesClient, placeSearchURL);
					
					StatusLine placeSearchStatus = placesResponse.getStatusLine();
					
					if (placeSearchStatus != null &&
					 placeSearchStatus.getStatusCode() == STATUS_OK) {
						placesBuilder = getJSONResponse(placesResponse);
					}
				}
				catch(Exception e) {
				    e.printStackTrace();
				}
			}
			
			return placesBuilder.toString();
		}
		
		private StringBuilder getJSONResponse(HttpResponse placesResponse) {
			StringBuilder placesBuilder = new StringBuilder();
			
			try {
				HttpEntity placesEntity = placesResponse.getEntity();
				InputStream placesContent = placesEntity.getContent();
				InputStreamReader placesInput = new InputStreamReader(placesContent);
				BufferedReader placesReader = new BufferedReader(placesInput);
				
				String lineIn;
				while ((lineIn = placesReader.readLine()) != null) {
				    placesBuilder.append(lineIn);
				}
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			
			return placesBuilder;
		}

		private HttpResponse executeGetRequest(HttpClient placesClient, String placeSearchURL) {
			HttpGet placesGet = new HttpGet(placeSearchURL);
			HttpResponse placesResponse = null;
			
			try {
				placesResponse = placesClient.execute(placesGet);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return placesResponse;
		}

		@Override
		protected void onPostExecute (String result) {
			try {
				JSONObject json = new JSONObject(result);
				JSONArray placesArr = json.getJSONArray("results");
				int numResults = placesArr.length();
				JSONObject place;
				LatLng latLng = null;
				String placeName = "";
				boolean hasName = false, hasLoc = false;
                float[] dist = new float[1];
				
				for (int placeNdx = 0; placeNdx < numResults; placeNdx++) {
					place = placesArr.getJSONObject(placeNdx);
					
					if (place.has("name")) {
						placeName = place.getString("name");
						hasName = true;
					}
					
					if (hasName && place.has("geometry")) {
						JSONObject geometry = place.getJSONObject("geometry");
						if (geometry.has("location")) {
							JSONObject location = geometry.getJSONObject("location");
							
							latLng = new LatLng(location.getDouble("lat"), location.getDouble("lng"));
							hasLoc = true;
						}
					}
					
					if (hasName && hasLoc) {
//						mMap.addMarker(new MarkerOptions()
//						 .position(latLng)
//						 .title(placeName));
                        Location.distanceBetween(mLoc.getLatitude(), mLoc.getLongitude(),
                         latLng.latitude, latLng.longitude, dist);
                        mPlaceList.add(new Place(placeName, dist[0] * MET_TO_MILES, latLng));
					}
				}
			}
			catch (JSONException e) {
				e.printStackTrace();
			}

            if (mPlaceList.size() > 0) {
                Toast.makeText(getActivity(), "Got them!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getActivity(), "Unfortunately we couldn't find anything near you.", Toast.LENGTH_LONG).show();
            }

            mPlaceListAdapter.notifyDataSetChanged();
		}
	}
}