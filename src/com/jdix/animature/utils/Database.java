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
		db.execSQL("CREATE TABLE User (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT, username TEXT, is_current INTEGER)");
		db.execSQL("CREATE TABLE Animature (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, height INTEGER, weight INTEGER, type INTEGER, speed INTEGER, defense INTEGER, agility INTEGER, strenght INTEGER, precission INTEGER, health INTEGER, level_evo INTEGER)");
		db.execSQL("CREATE TABLE Save (id INTEGER PRIMARY KEY AUTOINCREMENT, user INTEGER, character INTEGER, stage INTEGER, last_played DATE, started DATE, total_time INTEGER, steps INTEGER, an1 INTEGER, an2 INTEGER, an3 INTEGER, an4 INTEGER, an5 INTEGER, an6 INTEGER, map INTEGER, coord_x INTEGER, coord_y INTEGER, neighbour INTEGER, first_an INTEGER, orientation INTEGER, last_HealingMap INTEGER, last_HealingX INTEGER, last_HealingY INTEGER, medals INTEGER, money INTEGER, FOREIGN KEY(user) REFERENCES User(id), FOREIGN KEY(an1) REFERENCES Captured(id), FOREIGN KEY(an2) REFERENCES Captured(id), FOREIGN KEY(an3) REFERENCES Captured(id), FOREIGN KEY(an4) REFERENCES Captured(id), FOREIGN KEY(an5) REFERENCES Captured(id), FOREIGN KEY(an6) REFERENCES Captured(id), FOREIGN KEY(first_an) REFERENCES Animature(id))");
		db.execSQL("CREATE TABLE Captured (id INTEGER PRIMARY KEY AUTOINCREMENT, animature INTEGER, save INTEGER, nickname TEXT, sex INTEGER, attack1 INTEGER, attack1_pp INTEGER, attack2 INTEGER, attack2_pp INTEGER, attack3 INTEGER, attack3_pp INTEGER, attack4 INTEGER, attack4_pp INTEGER, health INTEGER, exp INTEGER, box INTEGER, FOREIGN KEY(animature) REFERENCES Animature(id), FOREIGN KEY(save) REFERENCES Save(id), FOREIGN KEY(attack1) REFERENCES Attacks(id), FOREIGN KEY(attack2) REFERENCES Attacks(id), FOREIGN KEY(attack3) REFERENCES Attacks(id), FOREIGN KEY(attack4) REFERENCES Attacks(id))");
		db.execSQL("CREATE TABLE Bag (save INTEGER PRIMARY KEY, object INTEGER, quantity INTEGER, FOREIGN KEY(save) REFERENCES Save(id), FOREIGN KEY(object) REFERENCES Objects(id))");
		db.execSQL("CREATE TABLE Objects (id INTEGER PRIMARY KEY AUTOINCREMENT, type INTEGER");
		db.execSQL("CREATE TABLE Enemies (id INTEGER PRIMARY KEY AUTOINCREMENT, map INTEGER, coord_x INTEGER, coord_y INTEGER, an1 INTEGER, an2 INTEGER, an3 INTEGER, an4 INTEGER, an5 INTEGER, an6 INTEGER, FOREIGN KEY(an1) REFERENCES Captured(id), FOREIGN KEY(an2) REFERENCES Captured(id), FOREIGN KEY(an3) REFERENCES Captured(id), FOREIGN KEY(an4) REFERENCES Captured(id), FOREIGN KEY(an5) REFERENCES Captured(id), FOREIGN KEY(an6) REFERENCES Captured(id))");
		db.execSQL("CREATE TABLE Fights (save INTEGER PRIMARY KEY, enemy INTEGER, FOREIGN KEY(save) REFERENCES Save(id), FOREIGN KEY(enemy) REFERENCES Enemies(id))");
		db.execSQL("CREATE TABLE Attacks (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, type INTEGER, max_pp INTEGER, power INTEGER, probability INTEGER)");
		// TODO Create DB in SQLite.
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// TODO Upgrade
	}
}