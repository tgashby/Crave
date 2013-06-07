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
	protected String mNDBNO;
    Cursor mFoodCursor;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_overview,
				container, false);
		
		mFoodStr = this.getArguments().getString(MainActivity.FOOD_STRING_KEY);
        mNDBNO = this.getArguments().getString(MainActivity.NDBNO_STRING_KEY);
		mFoodCursor = USDADatabaseManager.getFoodFromNDBNO(mNDBNO);

        USDADatabaseManager.initializeNutFacts(mFoodStr, mNDBNO);
        m_nutFacts = USDADatabaseManager.getNutFacts();
        //m_nutFacts.logVals();
		initLayout(rootView);
		
		return rootView;
	}

	/**
	 * Initialize the layout.
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

		currView = ((TextView)rootView.findViewById(R.id.overview_servingsize));
		currView.setText(currView.getText() + m_nutFacts.getServSize());

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