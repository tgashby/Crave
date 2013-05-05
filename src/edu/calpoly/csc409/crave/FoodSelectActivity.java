package edu.calpoly.csc409.crave;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FoodSelectActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_select);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.food_select, menu);
		return true;
	}

}
