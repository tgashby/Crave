package edu.calpoly.csc409.crave.activities;

import edu.calpoly.csc409.crave.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

public class SearchActivity extends Activity {

	protected EditText m_vwCraveSearch;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		m_vwCraveSearch = (EditText)findViewById(R.id.crave_search);
		
		m_vwCraveSearch.setOnKeyListener(new OnKeyListener () {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(event.getAction() == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER))
				{
					moveToMainActivity(m_vwCraveSearch.getText().toString());
				}
				
				return false;
			}
			
		});
	}

	protected void moveToMainActivity(String foodString) {
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("searchText", foodString);
		
		this.startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

}
