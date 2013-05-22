package edu.calpoly.csc409.crave.activities;

import edu.calpoly.csc409.crave.R;
import edu.calpoly.csc409.crave.dbmanagement.USDADatabaseManager;
import edu.calpoly.csc409.crave.fragments.AlternativesFragment;
import edu.calpoly.csc409.crave.fragments.NearMeFragment;
import edu.calpoly.csc409.crave.fragments.OverviewFragment;
import edu.calpoly.csc409.crave.fragments.RecipeFragment;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	protected static final int OVERVIEW_POS = 0;
	protected static final int RECIPE_POS = 1;
	protected static final int NEAR_ME_POS = 2;
	protected static final int ALTERNATIVES_POS = 3;
	
	protected static final int TAB_COUNT = 4;
	
	protected Cursor mFoodCursor;
	protected String mFoodStr;
	
	public static final String FOOD_STRING_KEY = "food_string";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mFoodStr = this.getIntent().getStringExtra(SearchActivity.SEARCH_STRING_KEY);
		
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});
		
		for (int i = 0; i < TAB_COUNT; i++) {
            ActionBar.Tab newTab = actionBar.newTab();
            newTab.setTabListener(this);

            switch (i) {
                case OVERVIEW_POS:
                    newTab.setIcon(R.drawable.overview);
                    break;

                case RECIPE_POS:
                    newTab.setIcon(R.drawable.recipes);
                    break;

                case NEAR_ME_POS:
                    newTab.setIcon(R.drawable.near_me);
                    break;

                case ALTERNATIVES_POS:
                    newTab.setIcon(R.drawable.alternatives);
                    break;
            }

			actionBar.addTab(newTab);
		}
		
//		mFoodCursor = USDADatabaseManager.searchForFood(foodStr);
//		Log.d("DEBUG", "Cursor loaded");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.search_again:
			Intent intent = new Intent(this, SearchActivity.class);
			this.startActivity(intent);
			break;
			
		default:
			return super.onOptionsItemSelected(item);
		}
		
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		// Unused for now
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		// Unused for now
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			Fragment frag = null;
			
			switch (position) {
			case OVERVIEW_POS:
				frag = new OverviewFragment();
				break;
			case RECIPE_POS:
				frag = new RecipeFragment();
				break;
			case NEAR_ME_POS:
				frag = new NearMeFragment();
				break;
			case ALTERNATIVES_POS:
				frag = new AlternativesFragment();
				break;
			}
			
			// Add the food item they are search for so each fragment has access
			Bundle args = new Bundle();
			args.putString(FOOD_STRING_KEY, mFoodStr);
			frag.setArguments(args);
			
			return frag;
		}

		@Override
		public int getCount() {
			return TAB_COUNT;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case OVERVIEW_POS:
				return getString(R.string.overview_title);
			case RECIPE_POS:
				return getString(R.string.recipes_title);
			case NEAR_ME_POS:
				return getString(R.string.near_me_title);
			case ALTERNATIVES_POS:
				return getString(R.string.alternatives_title);
			}
			return null;
		}
	}
}
