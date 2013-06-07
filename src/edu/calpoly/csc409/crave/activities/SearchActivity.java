package edu.calpoly.csc409.crave.activities;

import android.widget.Button;
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
	public static final String NDBNO_STRING_KEY = "ndbno_string";
	
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

        ((Button)findViewById(R.id.search_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToMainActivity(m_vwCraveSearch.getText().toString());
            }
        });
	}

	protected void moveToMainActivity(String foodString) {
		
		Cursor foodCursor = USDADatabaseManager.getNDBNO(foodString);
		
		//Log.d("~~Database Query getNDBNO~~", foodCursor.getCount()+"");
		
		if (foodCursor == null || foodCursor.getCount() == 0) {
			//Log.d("~~Database Query getNDBNO~~", "In the for loop!");
			
			Toast toast = Toast.makeText(this, "Food Not Found", Toast.LENGTH_SHORT);
	    	toast.show();
			
	    	m_vwCraveSearch.setText("");
			return;
		} else if (foodCursor.getCount() == 1) {
			Intent intent = new Intent(this, MainActivity.class);
			intent.putExtra(MainActivity.FOOD_STRING_KEY, foodCursor.getString(foodCursor.getColumnIndex("Search")));
			intent.putExtra(MainActivity.NDBNO_STRING_KEY, foodCursor.getString(foodCursor.getColumnIndex("NDB_No")));
			startActivity(intent);
		} else if (foodCursor.getCount() > 500) {
			Log.d("~~Database Query getNDBNO~~", foodCursor.getCount()+"");
			Toast toast = Toast.makeText(this, "Please Narrow Your Search", Toast.LENGTH_SHORT);
	    	toast.show();
			return;
		} else {
			Intent intent = new Intent(this, FoodSelectActivity.class);
			intent.putExtra(SEARCH_STRING_KEY, foodString);
			
			this.startActivity(intent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

}
