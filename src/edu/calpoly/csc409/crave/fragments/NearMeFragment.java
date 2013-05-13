package edu.calpoly.csc409.crave.fragments;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import edu.calpoly.csc409.crave.R;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NearMeFragment extends Fragment {
	GoogleMap mMap;
	
	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_near_me,
				container, false);
		
		mMap = ((SupportMapFragment)this.getFragmentManager().findFragmentById(R.id.near_me_map)).getMap();
		mMap.getUiSettings().setZoomControlsEnabled(false);
				
		return rootView;
	}
}