package edu.calpoly.csc409.crave.dbmanagement;

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
		
		/*Cursor tableNames = mReadableDB.rawQuery("select name from sqlite_master where type = ?", new String[] { "table" });
		Log.d("~Table Count~", tableNames.getCount()+"");
		tableNames.moveToFirst();
		Log.d("~Table Names~", "test");
		Log.d("~Table Names~", tableNames.getString(0));
		tableNames.moveToNext();
		Log.d("~Table Names~", tableNames.getString(0));
		tableNames.moveToNext();
		Log.d("~Table Names~", tableNames.getString(0));*/
		
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
	 * @param food - String to search for
	 * @return a Cursor pointing to all rows that matched the food, or null if
	 * the USDADatabaseManager has not been initialized.
	 */
	public static Cursor getNutrInfo(String ndbno) {
		// Don't even bother if USDADatabaseManager is uninitialized
		if (mInstance == null) {
			Log.e("USDADatabaseManager", "searchForFood called on uninitialized manager");
			return null;
		}
		
		/*Cursor tableNames = mReadableDB.rawQuery("select name from sqlite_master where type = ?", new String[] { "table" });
		Log.d("~Table Count~", tableNames.getCount()+"");
		tableNames.moveToFirst();
		Log.d("~Table Names~", "test");
		Log.d("~Table Names~", tableNames.getString(0));
		tableNames.moveToNext();
		Log.d("~Table Names~", tableNames.getString(0));
		tableNames.moveToNext();
		Log.d("~Table Names~", tableNames.getString(0));*/
		
		// LOLOLOL me neither...		
		Cursor toRet;
		
		/*toRet = mReadableDB.rawQuery("Select * FROM Nutrient_Definition def INNER JOIN Nutrient_Data data ON def.Nutr_No = data.Nutr_No" +
				" WHERE data.NDB_NO = ?;", new String[] { ndbno });*/
		toRet = mReadableDB.rawQuery("Select * FROM Nutrient_Data" +
				" WHERE NDB_NO = ?;", new String[] { ndbno });
		
		
		toRet.moveToFirst();
		return toRet;
	}
}
