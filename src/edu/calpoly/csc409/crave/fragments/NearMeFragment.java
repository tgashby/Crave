package edu.calpoly.csc409.crave.fragments;

import edu.calpoly.csc409.crave.R;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NearMeFragment extends Fragment {
	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_dummy,
				container, false);
		
		int sdk = android.os.Build.VERSION.SDK_INT;
		if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
		   rootView.setBackgroundResource(R.drawable.nearby_screen);
		} else {
		    rootView.setBackground(getResources().getDrawable(R.drawable.nearby_screen));
		}
		
		return rootView;
	}
}