package edu.calpoly.csc409.crave.fragments;

import android.widget.ImageView;
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
        initImage(rootView, mFoodCursor.getInt(mFoodCursor.getColumnIndex("FdGrp_Cd")));

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

    protected void initImage(View rootView, int foodGroup) {
        final int DAIRY_EGGS = 100;
        final int POULTRY = 500;
        final int SOUPS_SAUCES = 600;
        final int SAUSAGES_LUNCHMEAT = 700;
        final int CEREAL = 800;
        final int FRUIT = 900;
        final int PORK = 1000;
        final int VEGETABLES = 1100;
        final int NUTS_SEEDS = 1200;
        final int BEEF = 1300;
        final int BEVERAGES = 1400;
        final int FINFISH_SHELLFISH = 1500;
        final int LEGUMES = 1600;
        final int LAMB_VEAL_GAME = 1700;
        final int BAKED = 1800;
        final int SWEETS = 1900;
        final int PASTA = 2000;
        final int FAST_FOOD = 2100;
        final int MEALS = 2200;
        final int SNACKS = 2550;
        final int INDIAN_ALASKAN = 3500;

        ImageView imageView = (ImageView)rootView.findViewById(R.id.overview_fg_image);

        switch (foodGroup) {
            case DAIRY_EGGS:
                imageView.setBackgroundResource(R.drawable.fg_milk);
                break;

            case POULTRY:
                imageView.setBackgroundResource(R.drawable.fg_poultry);
                break;

            case SOUPS_SAUCES:
                imageView.setBackgroundResource(R.drawable.fg_soup);
                break;

            case SAUSAGES_LUNCHMEAT:
                imageView.setBackgroundResource(R.drawable.fg_sausage);
                break;

            case CEREAL:
                imageView.setBackgroundResource(R.drawable.fg_cereal);
                break;

            case FRUIT:
                imageView.setBackgroundResource(R.drawable.fg_fruit);
                break;

            case PORK:
                imageView.setBackgroundResource(R.drawable.fg_beef);
                break;

            case VEGETABLES:
                imageView.setBackgroundResource(R.drawable.fg_veggies);
                break;

            case NUTS_SEEDS:
                imageView.setBackgroundResource(R.drawable.fg_cornucopia);
                break;

            case BEEF:
                imageView.setBackgroundResource(R.drawable.fg_beef);
                break;

            case BEVERAGES:
                imageView.setBackgroundResource(R.drawable.fg_coffee);
                break;

            case FINFISH_SHELLFISH:
                imageView.setBackgroundResource(R.drawable.fg_cornucopia);
                break;

            case LEGUMES:
                imageView.setBackgroundResource(R.drawable.fg_cornucopia);
                break;

            case LAMB_VEAL_GAME:
                imageView.setBackgroundResource(R.drawable.fg_lamb);
                break;

            case BAKED:
                imageView.setBackgroundResource(R.drawable.fg_bread);
                break;

            case SWEETS:
                imageView.setBackgroundResource(R.drawable.fg_icecream);
                break;

            case PASTA:
                imageView.setBackgroundResource(R.drawable.fg_cornucopia);
                break;

            case FAST_FOOD:
                imageView.setBackgroundResource(R.drawable.fg_burger);
                break;

            case MEALS:
                imageView.setBackgroundResource(R.drawable.fg_lasagna);
                break;

            case SNACKS:
                imageView.setBackgroundResource(R.drawable.fg_sandwich);
                break;

            case INDIAN_ALASKAN:
                imageView.setBackgroundResource(R.drawable.fg_cornucopia);
                break;

            default:
                break;
        }
    }
}