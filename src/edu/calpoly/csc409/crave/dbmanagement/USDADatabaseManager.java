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
}
