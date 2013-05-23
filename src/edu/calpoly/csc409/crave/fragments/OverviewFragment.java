package edu.calpoly.csc409.crave.fragments;

import edu.calpoly.csc409.crave.R;
import edu.calpoly.csc409.crave.activities.MainActivity;
import edu.calpoly.csc409.crave.dbmanagement.USDADatabaseManager;
import edu.calpoly.csc409.crave.pojos.NutritionFacts;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OverviewFragment extends Fragment {
	NutritionFacts m_nutFacts;
	protected String mFoodStr;
    Cursor mFoodCursor;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_overview,
				container, false);
		
		mFoodStr = this.getArguments().getString(MainActivity.FOOD_STRING_KEY);
        mFoodCursor = USDADatabaseManager.getNDBNO(mFoodStr);

		initializeNutFacts();
		initLayout(rootView);
		
		return rootView;
	}

	/**
	 * Query the DB for the various Nutrition Facts
	 */
	private void initializeNutFacts() {
		m_nutFacts = new NutritionFacts();
		
		//Log.d("~~Database Query getNDBNO~~", mFoodCursor.getString(mFoodCursor.getColumnIndex("NDB_No")));
		
		//Currently just uses the first entry
		String ndbno = mFoodCursor.getString(mFoodCursor.getColumnIndex("NDB_No"));
		
		Cursor nutrCursor = USDADatabaseManager.getNutrInfo(ndbno);
		
		double val;
		for (int i = 0; i < nutrCursor.getCount(); i++) {
			val = nutrCursor.getDouble(nutrCursor.getColumnIndex("Nutr_Val"));
			
			switch (nutrCursor.getInt(nutrCursor.getColumnIndex("Nutr_No")))
			{
			case 208:
				m_nutFacts.setCalories(val);
				break;

			case 205:
				m_nutFacts.setCarbs(val);
				break;
			case 601:
				m_nutFacts.setCholesterol(val);
				break;
			case 291:
				m_nutFacts.setFiber(val);
				break;
			case 203:
				m_nutFacts.setProtein(val);
				break;
			case 606:
				m_nutFacts.setSatFat(val);
				break;
			case 307:
				m_nutFacts.setSodium(val);
				break;
			case 269:
				m_nutFacts.setSugar(val);
				break;
			case 204:
				m_nutFacts.setTotalFat(val);
				break;
			default:
			}
			
			nutrCursor.moveToNext();
		}
	}

	/**
	 * Initialize the layout.
	 * You shouldn't have to touch this... At least not for Vert Prototype
	 * Several things missing, don't care for Vert Prototype
	 */
	private void initLayout(View rootView) {
		TextView currView;

        currView = ((TextView)rootView.findViewById(R.id.overview_food_title));
        currView.setText(mFoodCursor.getString(mFoodCursor.getColumnIndex("Search")));

        currView = ((TextView)rootView.findViewById(R.id.overview_food_desc));
        currView.setText(mFoodCursor.getString(mFoodCursor.getColumnIndex("Shrt_Desc")).toLowerCase());

		currView = ((TextView)rootView.findViewById(R.id.overview_calories));
		currView.setText(currView.getText() + m_nutFacts.getCaloriesString());

		currView = ((TextView)rootView.findViewById(R.id.overview_cholesterol));
		currView.setText(currView.getText() + m_nutFacts.getCholesterolString());

		currView = ((TextView)rootView.findViewById(R.id.overview_dietaryfiber));
		currView.setText(currView.getText() + m_nutFacts.getFiberString());

		currView = ((TextView)rootView.findViewById(R.id.overview_protein));
		currView.setText(currView.getText() + m_nutFacts.getProteinString());

		currView = ((TextView)rootView.findViewById(R.id.overview_saturatedfat));
		currView.setText(currView.getText() + m_nutFacts.getSatFatString());

		// TODO
		currView = ((TextView)rootView.findViewById(R.id.overview_servingsize));
		currView.setText(currView.getText() + "SERV_SIZE");

		currView = ((TextView)rootView.findViewById(R.id.overview_sodium));
		currView.setText(currView.getText() + m_nutFacts.getSodiumString());

		currView = ((TextView)rootView.findViewById(R.id.overview_sugars));
		currView.setText(currView.getText() + m_nutFacts.getSugarString());

		currView = ((TextView)rootView.findViewById(R.id.overview_totalcarbs));
		currView.setText(currView.getText() + m_nutFacts.getCarbsString());

		currView = ((TextView)rootView.findViewById(R.id.overview_totalfat));
		currView.setText(currView.getText() + m_nutFacts.getTotalFatString());
	}
}