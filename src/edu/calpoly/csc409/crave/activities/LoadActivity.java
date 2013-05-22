package edu.calpoly.csc409.crave.activities;

import android.content.Context;
import android.location.LocationManager;
import android.provider.Settings;
import android.widget.Toast;
import edu.calpoly.csc409.crave.R;
import edu.calpoly.csc409.crave.dbmanagement.USDADatabaseManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class LoadActivity extends Activity {
    private static final int ENABLE_GPS_REQUEST_CODE = 1;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load);
		
		// I'M IMPORTANT, DON'T FORGET ABOUT ME
		USDADatabaseManager.initialize(this);

        LocationManager locManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            Toast.makeText(this, "Please Enable GPS", Toast.LENGTH_SHORT).show();
            startActivityForResult(intent, ENABLE_GPS_REQUEST_CODE);
        }
        else {
            goToSearchActivity();
        }
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ENABLE_GPS_REQUEST_CODE) {
            goToSearchActivity();
        }
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
