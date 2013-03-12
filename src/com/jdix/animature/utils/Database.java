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
		db.execSQL("CREATE TABLE User (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT, username TEXT)");
		db.execSQL("INSERT into User VALUES ('', '', '', '')");
		db.execSQL("CREATE TABLE Animature (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, height INTEGER, weight INTEGER, type INTEGER, velocity INTEGER, defense INTEGER, agility INTEGER, strenght INTEGER, precission INTEGER, health INTEGER, level_evo INTEGER)");
		db.execSQL("INSERT into Animature VALUES ('', '', '', '', '', '', '', '', '', '', '', '')");
		db.execSQL("CREATE TABLE Save (id INTEGER PRIMARY KEY AUTOINCREMENT, user INTEGER, stage, last_played, started, total_time, steps, *an1*, *an2*, *an3*, *an4*, *an5*, *an6*, *map*, coord_x, coord_y, neighbour, *1st_an*, orientation, last_Healing, medals, money, FOREIGN KEY(user) REFERENCES User(id))");
		// TODO From file and delete file (maybe)
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// TODO Upgrade

	}
}