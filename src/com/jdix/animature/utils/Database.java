package com.jdix.animature.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
	/**
	 * @param context Context of the application
	 * @param name Name of the database
	 * @param factory Current CursorFactory
	 * @param version Database version
	 */
	public Database(Context context, String name, CursorFactory factory,
			int version)
	{
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE user (email TEXT, password TEXT)");
		db.execSQL("INSERT into user VALUES ('', '')");
		db.execSQL("CREATE TABLE animatures ()");
		// TODO From file and delete file (maybe)
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// TODO Upgrade
		db.execSQL("DROP TABLE IF EXISTS user");
		db.execSQL("CREATE TABLE user (email TEXT, password TEXT)");
		db.execSQL("INSERT into user VALUES ('', '', '')");
	}
}