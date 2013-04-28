package com.jdix.animature.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.jdix.animature.entities.Captured;

public class CapturedDataSource extends DataSource {

	private final String[]	columns	= {"id", "animature", "save", "nickname",
	"sex", "status", "attack1", "attack1_pp", "attack2", "attack2_pp",
	"attack3", "attack3_pp", "attack4", "attack4_pp", "health", "level",
	"cur_exp", "exp", "box"			};

	public CapturedDataSource(final Context context, final String name,
	final CursorFactory factory, final int version)
	{
		dbHelper = new Database(context, name, factory, version);
	}

	public void createCaptured(final int animature, final int save,
	final String nickname, final int sex, final int status,
	final int capturedTime, final int attack1, final int attack1_pp,
	final int attack2, final int attack2_pp, final int attack3,
	final int attack3_pp, final int attack4, final int attack4_pp,
	final int health, final int level, final int cur_exp, final int exp,
	final int box)
	{
		final ContentValues values = new ContentValues();
		values.put("animature", animature);
		values.put("save", save);
		values.put("nickname", nickname);
		values.put("sex", sex);
		values.put("status", status);
		values.put("capturedTime", capturedTime);
		values.put("attack1", attack1);
		values.put("attack1_pp", attack1_pp);
		values.put("attack2", attack1);
		values.put("attack2_pp", attack1_pp);
		values.put("attack3", attack1);
		values.put("attack3_pp", attack1_pp);
		values.put("attack4", attack1);
		values.put("attack4_pp", attack1_pp);
		values.put("health", health);
		values.put("level", level);
		values.put("cur_exp", cur_exp);
		values.put("exp", exp);
		values.put("box", box);

		db.insert("Captured", null, values);
	}

	public Captured readCaptured(final int id)
	{
		super.db = super.dbHelper.getReadableDatabase();
		final Cursor c = db.query("Captured", columns, "id=" + id, null, null,
		null, null, null);
		if (c != null)
		{
			c.moveToFirst();
		}

		final Captured captured = cursorToCaptured(c);
		db.close();
		c.close();
		return captured;
	}

	public List<Captured> getAllCaptureds()
	{
		final List<Captured> CapturedList = new ArrayList<Captured>();

		final Cursor cursor = db.query("Captured", columns, null, null, null,
		null, null);
		cursor.moveToFirst();
		while ( ! cursor.isAfterLast())
		{
			final Captured captured = cursorToCaptured(cursor);
			CapturedList.add(captured);
			cursor.moveToNext();
		}

		cursor.close();
		return CapturedList;
	}

	public void deleteCaptured(final Captured captured)
	{
		final int id = captured.getIdAnimatureCapt();
		db.delete("Captured", "id" + " = " + id, null);
	}

	private Captured cursorToCaptured(final Cursor cursor)
	{
		final Captured captured = new Captured(cursor.getInt(0),
		cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4),
		cursor.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor.getInt(8),
		cursor.getInt(9), cursor.getInt(10), cursor.getInt(11),
		cursor.getInt(12), cursor.getInt(13), cursor.getInt(14),
		cursor.getInt(15), cursor.getInt(16), cursor.getInt(17),
		cursor.getInt(18), cursor.getInt(19));

		return captured;
	}
}
