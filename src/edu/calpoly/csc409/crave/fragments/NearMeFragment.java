package edu.calpoly.csc409.crave.fragments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.List;

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
import android.widget.Toast;

/**
 * KNOWN ISSUES:
 * This relies on GPS being on. If it isn't the app crashes!
 */

public class NearMeFragment extends Fragment {
	private GoogleMap mMap;
	
	// Places API constants
	private static final String PLACES_API_KEY = "AIzaSyD6sNTujHh7moB345pdeqk5XBCO4j32l_s";
	private static final int SEARCH_RADIUS = 2000;
	private static final String PLACE_TYPES = "food|bar|store";
	
	// Web Constants
	private static final int STATUS_OK = 200;
	
	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_near_me,
				container, false);
		
		String foodStr = getArguments().getString(MainActivity.FOOD_STRING_KEY).trim();
		
		mMap = ((SupportMapFragment)this.getFragmentManager()
									 .findFragmentById(R.id.near_me_map)).getMap();
		
		if (this.mMap != null) {
			this.mMap.setMyLocationEnabled(true);
			
			UiSettings uiSettings = this.mMap.getUiSettings();
			uiSettings.setZoomControlsEnabled(false);
			uiSettings.setMyLocationButtonEnabled(true);
			uiSettings.setCompassEnabled(true);
		}
		
		LocationManager locManager = (LocationManager)this.getActivity()
									  .getSystemService(Context.LOCATION_SERVICE);
		
		try {
			Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			String placesSearchStr = "https://maps.googleapis.com/maps/api/place/nearbysearch/" +
				    "json?location="+loc.getLatitude()+","+loc.getLongitude()+
				    "&keyword=" + URLEncoder.encode(foodStr, "UTF-8") +
				    "&radius=" + SEARCH_RADIUS + "&sensor=true" +
				    "&types=" + URLEncoder.encode(PLACE_TYPES, "UTF-8") +
				    "&key=" + PLACES_API_KEY;
		
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
						mMap.addMarker(new MarkerOptions()
						 .position(latLng)
						 .title(placeName));
					}
				}
			} 
			catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
}