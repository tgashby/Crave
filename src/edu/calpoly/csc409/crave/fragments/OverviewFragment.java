package edu.calpoly.csc409.crave.fragments;

import edu.calpoly.csc409.crave.R;
import edu.calpoly.csc409.crave.activities.MainActivity;
import edu.calpoly.csc409.crave.dbmanagement.USDADatabaseManager;
import edu.calpoly.csc409.crave.pojos.NutritionFacts;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OverviewFragment extends Fragment {
	NutritionFacts m_nutFacts;
	protected String mFoodStr;
	
	
	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_overview,
				container, false);
		
		mFoodStr = this.getArguments().getString(MainActivity.FOOD_STRING_KEY);
		
		initializeNutFacts();
		initLayout(rootView);
		
		return rootView;
	}

	/**
	 * Query the DB for the various Nutrition Facts
	 */
	private void initializeNutFacts() {
		m_nutFacts = new NutritionFacts();
		
		Cursor foodCursor = USDADatabaseManager.getNDBNO(mFoodStr);
		Log.d("~~Database Query getNDBNO~~", foodCursor.getString(foodCursor.getColumnIndex("NDB_No")));
		
		//Currently just uses the first entry
		String ndbno = foodCursor.getString(foodCursor.getColumnIndex("NDB_No"));
		
		Cursor nutrCursor = USDADatabaseManager.getNutrInfo(ndbno);
		double val;
		for (int i = 0; i < nutrCursor.getCount(); i++) {
			val = nutrCursor.getDouble(nutrCursor.getColumnIndex("Nutr_Val"));
			
			switch (nutrCursor.getInt(nutrCursor.getColumnIndex("Nutr_No")))
			{
			case 301:
				m_nutFacts.setCalcium(val); // I'm important
				break;
			case 208:
				/*// Kilocalories, special treatment
				double temp = nutrCursor.getDouble(nutrCursor.getColumnIndex("Nutr_Val"));
				temp = temp * 1000;*/
				m_nutFacts.setCalories(val); // I'm important
				break;
			case 205:
				m_nutFacts.setCarbs(val); // I'm important
				break;
			case 601:
				m_nutFacts.setCholesterol(val); // I'm important
				break;
			case 291:
				m_nutFacts.setFiber(val); // I'm important
				break;
			case 303:
				m_nutFacts.setIron(val); // I'm important
				break;
			case 203:
				m_nutFacts.setProtein(val); // I'm important
				break;
			case 606:
				m_nutFacts.setSatFat(val); // I'm important
				break;
			case 307:
				m_nutFacts.setSodium(val); // I'm important
				break;
			case 269:
				m_nutFacts.setSugar(val); // I'm important
				break;
			case 204:
				m_nutFacts.setTotalFat(val); // I'm important
				break;
			case 318:
				m_nutFacts.setVitA(val); // I'm important
				break;
			case 418:
				m_nutFacts.setVitB12(val); // I'm important
				break;
			case 401:
				m_nutFacts.setVitC(val); // I'm important
				break;
			case 324:
				m_nutFacts.setVitD(val); // I'm important
				break;
			case 323:
				m_nutFacts.setVitE(val); // I'm important 
				break;
			default:
			}
			
			nutrCursor.moveToNext();
		}
		
		/*// These actually get displayed in the current layout
		m_nutFacts.setCalcium(2); // I'm important
		m_nutFacts.setCalories(100); // I'm important
		m_nutFacts.setCarbs(3); // I'm important
		m_nutFacts.setCholesterol(6); // I'm important
		m_nutFacts.setFiber(10); // I'm important
		m_nutFacts.setIron(14); // I'm important
		m_nutFacts.setProtein(21); // I'm important
		m_nutFacts.setSatFat(24); // I'm important
		m_nutFacts.setSodium(26); // I'm important
		m_nutFacts.setSugar(27); // I'm important
		m_nutFacts.setTotalFat(30); // I'm important
		m_nutFacts.setVitA(31); // I'm important
		m_nutFacts.setVitB12(32); // I'm important
		m_nutFacts.setVitC(35); // I'm important
		m_nutFacts.setVitD(36); // I'm important
		m_nutFacts.setVitE(37); // I'm important
		
		// These don't, don't worry about it unless you want to
		m_nutFacts.setAlcohol(0);
		m_nutFacts.setCaffeine(1);
		m_nutFacts.setCaroteneA(4);
		m_nutFacts.setCaroteneB(5);
		m_nutFacts.setCholine(7);
		m_nutFacts.setCopper(8);
		m_nutFacts.setCryptoxanthin(9);
		m_nutFacts.setFolateDFE(11);
		m_nutFacts.setFolateFood(12);
		m_nutFacts.setFolicAcid(13);
		m_nutFacts.setLutein(15);
		m_nutFacts.setLycopene(16);
		m_nutFacts.setMagnesium(17);
		m_nutFacts.setNiacin(18);
		m_nutFacts.setPhosphorus(19);
		m_nutFacts.setPotassium(20);
		m_nutFacts.setRetinol(22);
		m_nutFacts.setRiboflavin(23);
		m_nutFacts.setSelenium(25);
		m_nutFacts.setTheobromine(28);
		m_nutFacts.setThiamin(29);
		m_nutFacts.setVitB6(33);
		m_nutFacts.setVitBAdded(34);
		m_nutFacts.setVitEAdded(38);
		m_nutFacts.setVitK(39);
		m_nutFacts.setWater(40);
		m_nutFacts.setZinc(41);*/
	}

	/**
	 * Initialize the layout.
	 * You shouldn't have to touch this... At least not for Vert Prototype
	 * Several things missing, don't care for Vert Prototype
	 */
	private void initLayout(View rootView) {
		TextView currView;
		
//		currView = ((TextView)rootView.findViewById(R.id.overview_calcium));
//		currView.setText(currView.getText() + m_nutFacts.getCalciumString());
		
		currView = ((TextView)rootView.findViewById(R.id.overview_calories));
		currView.setText(currView.getText() + m_nutFacts.getCaloriesString());
		
//		// TODO
//		currView = ((TextView)rootView.findViewById(R.id.overview_caloriesfromfat));
//		currView.setText(currView.getText() + "CAL_FROM_FAT_COMP");

		currView = ((TextView)rootView.findViewById(R.id.overview_cholesterol));
		currView.setText(currView.getText() + m_nutFacts.getCholesterolString());

		currView = ((TextView)rootView.findViewById(R.id.overview_dietaryfiber));
		currView.setText(currView.getText() + m_nutFacts.getFiberString());

//		currView = ((TextView)rootView.findViewById(R.id.overview_iron));
//		currView.setText(currView.getText() + m_nutFacts.getIronString());

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

//		// TODO
//		currView = ((TextView)rootView.findViewById(R.id.overview_transfat));
//		currView.setText(currView.getText() + "TRANS_FAT");
//
//		currView = ((TextView)rootView.findViewById(R.id.overview_vitaminA));
//		currView.setText(currView.getText() + m_nutFacts.getVitAString());
//
//		// TODO
//		currView = ((TextView)rootView.findViewById(R.id.overview_vitaminB));
//		currView.setText(currView.getText() + m_nutFacts.getVitB12String());
//
//		currView = ((TextView)rootView.findViewById(R.id.overview_vitaminC));
//		currView.setText(currView.getText() + m_nutFacts.getVitCString());
//
//		currView = ((TextView)rootView.findViewById(R.id.overview_vitaminD));
//		currView.setText(currView.getText() + m_nutFacts.getVitDString());
//
//		currView = ((TextView)rootView.findViewById(R.id.overview_vitaminE));
//		currView.setText(currView.getText() + m_nutFacts.getVitEString());
		
	}
}