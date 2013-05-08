package edu.calpoly.csc409.crave.dbmanagement;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import android.content.Context;

public class USDADatabase extends SQLiteAssetHelper {
	private static final String DB_NAME = "usda";
	private static final int DB_VERSION = 1;
	
	// Package scope, get at the DB using USDADatabaseManager
	USDADatabase(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
}
