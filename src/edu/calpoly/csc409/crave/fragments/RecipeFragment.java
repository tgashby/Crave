package edu.calpoly.csc409.crave.fragments;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import edu.calpoly.csc409.crave.R;
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
		View rootView = inflater.inflate(R.layout.view_recipe,
				container, false);
		
		String foodStr = getArguments().getString("search_text");
		
		WebView webView = (WebView)rootView.findViewById(R.id.recipe_webview);
		
		try {
			webView.loadUrl("http://www.yummly.com/recipes?q=" + URLEncoder.encode(foodStr, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
//		WebSettings webSettings = webView.getSettings();
//		webSettings.setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient());
		
//		int sdk = android.os.Build.VERSION.SDK_INT;
//		if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//		   rootView.setBackgroundResource(R.drawable.recipe_sceen);
//		} else {
//		    rootView.setBackground(getResources().getDrawable(R.drawable.recipe_sceen));
//		}
		
		return rootView;
	}
}
