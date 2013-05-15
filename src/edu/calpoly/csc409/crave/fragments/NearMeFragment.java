package edu.calpoly.csc409.crave.fragments;

import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;

import edu.calpoly.csc409.crave.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class NearMeFragment extends Fragment {
	GoogleMap mMap;
	
	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_near_me,
				container, false);
		
		String foodStr = getArguments().getString("search_text");
		
		mMap = ((SupportMapFragment)this.getFragmentManager().findFragmentById(R.id.near_me_map)).getMap();
		
		if (this.mMap != null) {
			this.mMap.setMyLocationEnabled(true);
			
			UiSettings uiSettings = this.mMap.getUiSettings();
			uiSettings.setZoomControlsEnabled(false);
			uiSettings.setMyLocationButtonEnabled(true);
			uiSettings.setCompassEnabled(true);
		}
		
		Geocoder geocoder = new Geocoder(this.getActivity());
		LocationManager locManager = (LocationManager)this.getActivity().getSystemService(Context.LOCATION_SERVICE);
		
		try {
			Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			List<Address> places = geocoder.getFromLocationName(foodStr, 10, loc.getLatitude() - 10, loc.getLongitude() - 10, loc.getLatitude() + 10, loc.getLongitude() + 10);
			Log.d("NearMeFragment", "" + places.size());
			Toast.makeText(getActivity(), foodStr + " places found: " + places.size(), Toast.LENGTH_SHORT).show();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
				
		return rootView;
	}
}