package com.jdix.animature.utils;

import android.database.sqlite.SQLiteDatabase;

public class DataSource {

	protected SQLiteDatabase	db;
	protected Database			dbHelper;

	public void open()
	{
		db = dbHelper.getWritableDatabase();
	}

	public void close()
	{
		dbHelper.close();
	}
}
