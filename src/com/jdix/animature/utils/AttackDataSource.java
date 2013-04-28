package com.jdix.animature.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.jdix.animature.entities.Attack;

public class AttackDataSource extends DataSource {

	private final String[]	columns	= {"id", "name", "type", "max_pp", "power",
	"probability"					};

	public AttackDataSource(final Context context, final String name,
	final CursorFactory factory, final int version)
	{
		dbHelper = new Database(context, name, factory, version);
	}

	public void createAttack(final String nombre, final int type,
	final int max_pp, final int power, final int probability)
	{
		final ContentValues values = new ContentValues();
		values.put("nombre", nombre);
		values.put("type", type);
		values.put("max_pp", max_pp);
		values.put("power", power);
		values.put("probability", probability);

		db.insert("Attacks", null, values);
	}

	public Attack readAttack(final int id)
	{
		super.db = super.dbHelper.getReadableDatabase();
		final Cursor c = db.query("Attacks", columns, "id=" + id, null, null,
		null, null, null);
		if (c != null)
		{
			c.moveToFirst();
		}

		final Attack attack = cursorToAttack(c);
		db.close();
		c.close();
		return attack;
	}

	public List<Attack> getAllAttacks()
	{
		final List<Attack> AttackList = new ArrayList<Attack>();

		final Cursor cursor = db.query("Attacks", columns, null, null, null,
		null, null);
		cursor.moveToFirst();
		while ( ! cursor.isAfterLast())
		{
			final Attack attack = cursorToAttack(cursor);
			AttackList.add(attack);
			cursor.moveToNext();
		}

		cursor.close();
		return AttackList;
	}

	public void deleteAttack(final Attack attack)
	{
		final int id = attack.getId_Attack();
		db.delete("Attacks", "id" + " = " + id, null);
	}

	private Attack cursorToAttack(final Cursor cursor)
	{
		final Attack attack = new Attack();
		attack.setId_Attack(cursor.getInt(0));
		attack.setName_Attack(cursor.getString(1));
		attack.setType_Attack(cursor.getInt(2));
		attack.setMax_pp(cursor.getInt(3));
		attack.setPower(cursor.getInt(4));
		attack.setProbability(cursor.getInt(5));
		return attack;
	}
}
