package com.jdix.animature.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class SaveDataSource extends DataSource {

	private final String[]	columns	= {"id", "character", "stage",
	"last_played", "started", "total_time", "steps", "an1", "an2", "an3",
	"an4", "an5", "an6", "map", "coord_x", "coord_y", "neighbour", "first_an",
	"orientation", "last_HealingMap", "last_HealingX", "last_HealingY",
	"medals", "money"				};

	public SaveDataSource(final Context context, final String name,
	final CursorFactory factory, final int version)
	{
		dbHelper = new Database(context, name, factory, version);
	}

	public void createSave(final int id, final int character, final int stage,
	final int last_played, final int started, final int total_time,
	final int steps, final int an1, final int an2, final int an3,
	final int an4, final int an5, final int an6, final int map,
	final int coord_x, final int coord_y, final int neighbour,
	final int first_an, final int orientation, final int last_HealingMap,
	final int last_HealingX, final int last_HealingY, final int medals,
	final int money)
	{
		final ContentValues values = new ContentValues();
		values.put("character", character);
		values.put("stage", stage);
		values.put("last_played", last_played);
		values.put("started", started);
		values.put("total_time", total_time);
		values.put("steps", steps);
		values.put("an1", an1);
		values.put("an2", an2);
		values.put("an3", an3);
		values.put("an4", an4);
		values.put("an5", an5);
		values.put("an6", an6);
		values.put("map", map);
		values.put("coord_x", coord_x);
		values.put("coord_y", coord_y);
		values.put("neighbour", neighbour);
		values.put("first_an", first_an);
		values.put("orientation", orientation);
		values.put("last_HealingMap", last_HealingMap);
		values.put("last_HealingX", last_HealingX);
		values.put("last_HealingY", last_HealingY);
		values.put("medals", medals);
		values.put("money", money);

		db.insert("Save", null, values);
	}

	public int readSaveColInt(final int id, final int pos)
	{
		this.db = this.dbHelper.getReadableDatabase();
		final Cursor c = db.query("Save", columns, "id=" + id, null, null,
		null, null, null);
		if (c != null)
		{
			c.moveToFirst();
		}

		final int colInt = c.getInt(pos);
		db.close();
		c.close();
		return colInt;
	}

	public void deleteSave(final int id)
	{
		db.delete("Animature", "id" + " = " + id, null);
	}
}
