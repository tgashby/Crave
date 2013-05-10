package edu.calpoly.csc409.crave.activities;

import edu.calpoly.csc409.crave.R;
import edu.calpoly.csc409.crave.dbmanagement.USDADatabaseManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class LoadActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load);
		
		// I'M IMPORTANT, DON'T FORGET ABOUT ME
		USDADatabaseManager.initialize(this);
		
		View rootView = findViewById(android.R.id.content);
		
		rootView.setOnClickListener(new OnClickListener () {
			@Override
			public void onClick(View view) {
				goToSearchActivity();
			}
		});
	}

	protected void goToSearchActivity() {
		Intent intent = new Intent(this, SearchActivity.class);
		
		this.startActivity(intent);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.load, menu);
		return true;
	}

}
