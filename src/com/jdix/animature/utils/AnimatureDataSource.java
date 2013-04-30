package com.jdix.animature.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.jdix.animature.entities.Animature;

public class AnimatureDataSource extends DataSource {

	private final String[]	columns	= {"id", "name", "height", "weight",
	"type", "type2", "speed", "defense", "agility", "strenght", "precission",
	"health", "level_evo"			};

	public AnimatureDataSource(final Context context, final String name,
	final CursorFactory factory, final int version)
	{
		dbHelper = new Database(context, name, factory, version);
	}

	public void createAnimature(final int id_Animature, final String name,
	final double height, final double weight, final int type, final int type2,
	final int speed, final int defense, final int agility, final int strenght,
	final int precission, final int health, final int level_evo)
	{
		final ContentValues values = new ContentValues();
		values.put("id", id_Animature);
		values.put("name", name);
		values.put("height", height);
		values.put("weight", weight);
		values.put("type", type);
		values.put("type2", type2);
		values.put("speed", speed);
		values.put("defense", defense);
		values.put("agility", agility);
		values.put("strenght", strenght);
		values.put("precission", precission);
		values.put("health", health);
		values.put("level_evo", level_evo);

		db.insert("Animature", null, values);
	}

	public Animature readAnimature(final int id)
	{
		this.db = this.dbHelper.getReadableDatabase();
		final Cursor c = db.query("Animature", columns, "id=" + id, null, null,
		null, null, null);
		if (c != null)
		{
			c.moveToFirst();
		}

		final Animature animature = cursorToAnimature(c);
		db.close();
		c.close();
		return animature;
	}

	public List<Animature> getAllAnimatures()
	{
		final List<Animature> AnimatureList = new ArrayList<Animature>();

		final Cursor cursor = db.query("Animature", columns, null, null, null,
		null, null);
		cursor.moveToFirst();
		while ( ! cursor.isAfterLast())
		{
			final Animature animature = cursorToAnimature(cursor);
			AnimatureList.add(animature);
			cursor.moveToNext();
		}

		cursor.close();
		return AnimatureList;
	}

	public void deleteAnimature(final Animature animature)
	{
		final int id = animature.getId_Animature();
		db.delete("Animature", "id" + " = " + id, null);
	}

	private Animature cursorToAnimature(final Cursor cursor)
	{
		final Animature animature = new Animature(cursor.getInt(0),
		cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3),
		cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7),
		cursor.getInt(8), cursor.getInt(9), cursor.getInt(10),
		cursor.getInt(11), cursor.getInt(12), null, null);

		return animature;
	}
}
