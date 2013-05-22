package edu.calpoly.csc409.crave.activities;

import edu.calpoly.csc409.crave.R;
import edu.calpoly.csc409.crave.dbmanagement.USDADatabaseManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends Activity {

	protected EditText m_vwCraveSearch;
	
	public static final String SEARCH_STRING_KEY = "search_string";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		m_vwCraveSearch = (EditText)findViewById(R.id.crave_search);
		
		m_vwCraveSearch.setOnKeyListener(new OnKeyListener () {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(event.getAction() == KeyEvent.ACTION_DOWN && 
				 (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER)) {
					moveToMainActivity(m_vwCraveSearch.getText().toString());
				}
				
				return false;
			}
			
		});
	}

	protected void moveToMainActivity(String foodString) {
		
		Cursor foodCursor = USDADatabaseManager.getNDBNO(foodString);
		
		Log.d("~~Database Query getNDBNO~~", foodCursor.getCount()+"");
		
		if (foodCursor == null || foodCursor.getCount() == 0) {
			Log.d("~~Database Query getNDBNO~~", "In the for loop!");
			
			Toast toast = Toast.makeText(this, "Food Not Found", Toast.LENGTH_SHORT);
	    	toast.show();
			
	    	m_vwCraveSearch.setText("");
			return;
		}
		
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra(SEARCH_STRING_KEY, foodString);
		
		this.startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

}
