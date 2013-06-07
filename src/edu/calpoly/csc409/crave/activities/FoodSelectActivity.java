package edu.calpoly.csc409.crave.activities;

import java.util.ArrayList;

import edu.calpoly.csc409.crave.R;
import edu.calpoly.csc409.crave.dbmanagement.USDADatabaseManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FoodSelectActivity extends Activity {
	protected ListView m_vwFoodList;
	protected ArrayAdapter<String> m_adapter;
	protected String mFoodStr;
	protected Cursor mFoodCur;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mFoodStr = this.getIntent().getStringExtra(SearchActivity.SEARCH_STRING_KEY);
		mFoodCur = USDADatabaseManager.getNDBNO(mFoodStr);
		//Log.d("~FoodSelectActivity~", mFoodStr + " " + mFoodCur.getCount());
		
		InitLayout();
		InitListeners();
		//Log.d("~FoodSelectActivity~", "hi1.7");
	}
	
	private void InitLayout() {
		setContentView(R.layout.activity_food_select);
		m_vwFoodList = (ListView) findViewById(R.id.food_select_list);
		m_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getFoods());
		m_vwFoodList.setAdapter(m_adapter);
		//Log.d("~FoodSelectActivity~", "hi");
	}
	
	private void InitListeners() {
		//Log.d("~FoodSelectActivity~", "hi1.5");
		m_vwFoodList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				launchMainActivity(arg2);
				
			} 
		});
		//Log.d("~FoodSelectActivity~", "hi1.6");
	}
	
	private void launchMainActivity(int pos) {
		//Log.d("~FoodSelectActivity~", "hi2");
		mFoodCur.moveToPosition(pos);
		
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra(MainActivity.FOOD_STRING_KEY, mFoodCur.getString(mFoodCur.getColumnIndex("Search")));
		//Log.d("~FoodSelectActivity~", "hi2.1");
		intent.putExtra(MainActivity.NDBNO_STRING_KEY, mFoodCur.getString(mFoodCur.getColumnIndex("NDB_No")));
		//Log.d("~FoodSelectActivity~", "hi2.2");
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.food_select, menu);
		return true;
	}
	
	private ArrayList<String> getFoods() {
		ArrayList<String> ret = new ArrayList<String>();
		mFoodCur.moveToFirst();
		
		while (!mFoodCur.isAfterLast()) {
			ret.add(mFoodCur.getString(mFoodCur.getColumnIndex("Long_Desc")));
			//Log.d("~~~", "Added "+mFoodCur.getColumnIndex("Long_Desc"));
			mFoodCur.moveToNext();
		}
		
		return ret;
	}

}
