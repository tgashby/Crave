package edu.calpoly.csc409.crave.dbmanagement;

import java.util.ArrayList;

import edu.calpoly.csc409.crave.pojos.NutritionFacts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

public class USDADatabaseManager {
	protected static USDADatabaseManager mInstance = null;
	private static USDADatabase mDatabase = null;
	private static SQLiteDatabase mReadableDB = null;
	
	/**
	 * Initializes the Singleton class
	 * @param c - Context for use in initializing the USDADatabase
	 */
	public static void initialize(Context c) {
		if (mInstance == null) {
			mInstance = new USDADatabaseManager();
			mDatabase = new USDADatabase(c);
			mReadableDB = mDatabase.getReadableDatabase();
		}
	}
	
	
	private static NutritionFacts m_nutFacts;
	
	/**
	 * Search for a food by name. 
	 * WARNING: If USDADatabaseManager has not been initialized,
	 * it does NOTHING.
	 * @param food - String to search for
	 * @return a Cursor pointing to all rows that matched the food, or null if
	 * the USDADatabaseManager has not been initialized.
	 */
	public static Cursor searchForFood(String food) {
		// Don't even bother if USDADatabaseManager is uninitialized
		if (mInstance == null) {
			Log.e("USDADatabaseManager", "searchForFood called on uninitialized manager");
			return null;
		}
		
		// LOLOLOL I have no idea what I'm doing...
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		
		Cursor toRet;
		
		String table = "MainFoodDesc";
		String [] columns = {"`Food code`", "`Main food description`"};
		String rows = "`Main food description` LIKE `%" + food + "%`";
		
		qb.setTables(table);
		toRet = qb.query(mReadableDB, columns, rows, null, null, null, null);
		
		toRet.moveToFirst();
		return toRet;
	}
	
	/**
	 * Search for a food by name and get its unique identifier. 
	 * WARNING: If USDADatabaseManager has not been initialized,
	 * it does NOTHING.
	 * @param food - String to search for
	 * @return a Cursor pointing to all rows that matched the food, or null if
	 * the USDADatabaseManager has not been initialized.
	 */
	public static Cursor getNDBNO(String food) {
		// Don't even bother if USDADatabaseManager is uninitialized
		if (mInstance == null) {
			Log.e("USDADatabaseManager", "searchForFood called on uninitialized manager");
			return null;
		}
		
		// LOLOLOL me neither...		
		Cursor toRet;
		
		toRet = mReadableDB.rawQuery("SELECT NDB_No, Search, Shrt_Desc FROM Food_Description " +
				"WHERE Search LIKE ?;", new String[] { "%"+food+"%" });
		
		
		toRet.moveToFirst();
		return toRet;
	}
	
	/**
	 * Get Nutrition Information from NDBNo. 
	 * WARNING: If USDADatabaseManager has not been initialized,
	 * it does NOTHING.
	 * @param ndbno - NDBNo to get nutrition info for.
	 * @return a Cursor pointing to all rows that matched the food, or null if
	 * the USDADatabaseManager has not been initialized.
	 */
	public static Cursor getNutrInfo(String ndbno) {
		// Don't even bother if USDADatabaseManager is uninitialized
		if (mInstance == null) {
			Log.e("USDADatabaseManager", "searchForFood called on uninitialized manager");
			return null;
		}
		
		Cursor toRet;
		
		/*toRet = mReadableDB.rawQuery("Select * FROM Nutrient_Definition def INNER JOIN Nutrient_Data data ON def.Nutr_No = data.Nutr_No" +
				" WHERE data.NDB_NO = ?;", new String[] { ndbno });*/
		toRet = mReadableDB.rawQuery("Select * FROM Nutrient_Data" +
				" WHERE NDB_NO = ?;", new String[] { ndbno });
		
		
		toRet.moveToFirst();
		return toRet;
	}
	
	/**
	 * Get Alternatives from NDBNo. Uses currently stored nutrition facts object
	 * WARNING: If USDADatabaseManager has not been initialized,
	 * it does NOTHING.
	 * @param ndbno - NDBNo for the food in question
	 * @return an arraylist of alternative search terms
	 * the USDADatabaseManager has not been initialized.
	 */
	public static ArrayList<String> getAlternatives(String ndbno) {
		// Don't even bother if USDADatabaseManager is uninitialized
		if (mInstance == null) {
			Log.e("USDADatabaseManager", "searchForFood called on uninitialized manager");
			return null;
		}
		
		ArrayList<String> alts = new ArrayList<String>();
		
		
		
		
		return alts;
	}
	
	/**
	 * Query the DB for the various Nutrition Facts
	 * foodStr - String search term, searches the Search col of the db
	 */
	public static void initializeNutFacts(String foodStr) {
		m_nutFacts = new NutritionFacts();
		
		Cursor foodCursor = getNDBNO(foodStr);
		
		//Log.d("~~Database Query getNDBNO~~", foodCursor.getString(foodCursor.getColumnIndex("NDB_No")));
		
		//Currently just uses the first entry
		String ndbno = foodCursor.getString(foodCursor.getColumnIndex("NDB_No"));
		
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
	
	/*
	 * Getter for the nutrition facts object.
	 * WARNING: Must initialize nutrtion facts object first.
	 */
	public static NutritionFacts getNutFacts() {
		return m_nutFacts;
	}
}
