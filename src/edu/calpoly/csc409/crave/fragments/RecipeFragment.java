package edu.calpoly.csc409.crave.fragments;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import edu.calpoly.csc409.crave.R;
import edu.calpoly.csc409.crave.activities.MainActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class RecipeFragment extends Fragment {
	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_recipe,
				container, false);
		
		String foodStr = getArguments().getString(MainActivity.FOOD_STRING_KEY);
		
		WebView webView = (WebView)rootView.findViewById(R.id.recipe_webview);
		
		try {
			webView.loadUrl("http://www.yummly.com/recipes?q=" + URLEncoder.encode(foodStr, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// Supposedly this is dangerous... Not sure if we want it or not.
//		WebSettings webSettings = webView.getSettings();
//		webSettings.setJavaScriptEnabled(true);
		
		webView.setWebViewClient(new WebViewClient());
		
		return rootView;
	}
}
