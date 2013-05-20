package com.jdix.animature.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jdix.animature.entities.Attack;
import com.jdix.animature.entities.Capturable;
import com.jdix.animature.entities.Item;

public class DataSource {

	protected SQLiteDatabase	db;
	protected Database			dbHelper;

	private final String[]		columnsA	= {"id", "name", "type", "max_pp",
	"active", "ifPass", "power", "probability"};
	private final String[]		columnsCap	= {"id", "animature", "save",
	"nickname", "sex", "status", "attack1", "attack1_pp", "attack2",
	"attack2_pp", "attack3", "attack3_pp", "attack4", "attack4_pp", "health",
	"level", "cur_exp", "exp", "box"		};
	private final String[]		columnsAnim	= {"id", "name", "height",
	"weight", "type", "type2", "speed", "defense", "agility", "strenght",
	"precission", "health", "level_evo", "baseExp", "captureRange"};
	private final String[]		columnsSave	= {"id", "character", "stage",
	"last_played", "started", "total_time", "steps", "an1", "an2", "an3",
	"an4", "an5", "an6", "map", "coord_x", "coord_y", "neighbour", "first_an",
	"orientation", "last_HealingMap", "last_HealingX", "last_HealingY",
	"medals", "money"						};
	private final String[]		columnsItem	= {"id", "name", "type",
	"description"							};

	public DataSource(final Context context)
	{
		dbHelper = new Database(context);
	}

	public void open()
	{
		db = dbHelper.getWritableDatabase();
	}

	public void close()
	{
		dbHelper.close();
	}

	public void createAttack(final String name, final int type,
	final int max_pp, final int active, final int ifPass, final int power,
	final int probability)
	{
		final ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("type", type);
		values.put("max_pp", max_pp);
		values.put("active", active);
		values.put("ifPass", ifPass);
		values.put("power", power);
		values.put("probability", probability);

		db.insert("ATTACK", null, values);
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
		values.put("attack2", attack2);
		values.put("attack2_pp", attack2_pp);
		values.put("attack3", attack3);
		values.put("attack3_pp", attack3_pp);
		values.put("attack4", attack4);
		values.put("attack4_pp", attack4_pp);
		values.put("health", health);
		values.put("level", level);
		values.put("cur_exp", cur_exp);
		values.put("exp", exp);
		values.put("box", box);

		db.insert("CAPTURED", null, values);
	}

	public void createAnimature(final String name, final double height,
	final double weight, final int type, final int type2, final int speed,
	final int defense, final int agility, final int strenght,
	final int precission, final int health, final int level_evo,
	final int baseExp, final int captureRange)
	{
		final ContentValues values = new ContentValues();
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
		values.put("baseExp", baseExp);
		values.put("captureRange", captureRange);

		db.insert("ANIMATURE", null, values);
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

		db.insert("SAVE", null, values);
	}

	public void createItem(final String name, final int type,
	final String description)
	{
		final ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("type", type);
		values.put("description", description);

		db.insert("ITEM", null, values);
	}

	public Attack readAttack(final int id)
	{
		this.db = this.dbHelper.getReadableDatabase();
		final Cursor c = db.query("ATTACK", columnsA, "id=" + id, null, null,
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
		final Cursor cursor = db.query("ATTACK", columnsA, null, null, null,
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
		db.delete("ATTACK", "id" + " = " + id, null);
	}

	public Attack cursorToAttack(final Cursor cursor)
	{
		final Attack attack;

		final int id = cursor.getInt(0);
		final String name = cursor.getString(1);
		final int type = cursor.getInt(2);
		final int max_pp = cursor.getInt(3);
		final int active = cursor.getInt(4);
		final int ifPass = cursor.getInt(5);
		final int power = cursor.getInt(6);
		final int probability = cursor.getInt(7);

		attack = new Attack(id, name, type, max_pp, active, ifPass, power,
		probability);

		return attack;
	}

	public Capturable readCaptured(final int id)
	{
		db = dbHelper.getReadableDatabase();
		final Cursor c = db.query("CAPTURED", columnsCap, "id=" + id, null,
		null, null, null, null);
		if (c != null)
		{
			c.moveToFirst();
		}

		final Capturable captured = cursorToCaptured(c);
		db.close();
		c.close();
		return captured;
	}

	public List<Capturable> getAllCaptureds()
	{
		final List<Capturable> CapturedList = new ArrayList<Capturable>();

		final Cursor cursor = db.query("CAPTURED", columnsCap, null, null,
		null, null, null);
		cursor.moveToFirst();
		while ( ! cursor.isAfterLast())
		{
			final Capturable captured = cursorToCaptured(cursor);
			CapturedList.add(captured);
			cursor.moveToNext();
		}

		cursor.close();
		return CapturedList;
	}

	public void deleteCaptured(final Capturable captured)
	{
		final int id = captured.getIdAnimatureCapt();
		db.delete("CAPTURED", "id" + " = " + id, null);
	}

	public Capturable cursorToCaptured(final Cursor cursor)
	{
		final Capturable captured;

		final int id = cursor.getInt(0);
		final int idAnimature = cursor.getInt(1);
		final int save = cursor.getInt(2);
		final String nickname = cursor.getString(3);
		final int sex = cursor.getInt(4);
		final int status = cursor.getInt(5);
		final int capturedTime = cursor.getInt(6);
		final int attack1 = cursor.getInt(7);
		final int attack1_pp = cursor.getInt(8);
		final int attack2 = cursor.getInt(9);
		final int attack2_pp = cursor.getInt(10);
		final int attack3 = cursor.getInt(11);
		final int attack3_pp = cursor.getInt(12);
		final int attack4 = cursor.getInt(13);
		final int attack4_pp = cursor.getInt(14);
		final int health = cursor.getInt(15);
		final int level = cursor.getInt(16);
		final int cur_exp = cursor.getInt(17);
		final int exp = cursor.getInt(18);
		final int box = cursor.getInt(19);

		captured = new Capturable(id, idAnimature, save, nickname, sex, status,
		capturedTime, attack1, attack1_pp, attack2, attack2_pp, attack3,
		attack3_pp, attack4, attack4_pp, health, level, cur_exp, exp, box);

		return captured;
	}

	public int readAnimatureColInt(final int id, final int pos)
	{
		this.db = this.dbHelper.getReadableDatabase();
		final Cursor c = db.query("ANIMATURE", columnsAnim, "id=" + id, null,
		null, null, null, null);
		if (c != null)
		{
			c.moveToFirst();
		}

		final int colInt = c.getInt(pos);
		db.close();
		c.close();
		return colInt;
	}

	/*
	 * public Animature readAnimature(final int id) { this.db =
	 * this.dbHelper.getReadableDatabase(); final Cursor c =
	 * db.query("ANIMATURE", columnsAnim, "id=" + id, null, null, null, null,
	 * null); if (c != null) { c.moveToFirst(); } final Animature animature =
	 * cursorToAnimature(c); db.close(); c.close(); return animature; } public
	 * List<Animature> getAllAnimatures() { final List<Animature> AnimatureList
	 * = new ArrayList<Animature>(); final Cursor cursor = db.query("ANIMATURE",
	 * columnsAnim, null, null, null, null, null); cursor.moveToFirst(); while (
	 * ! cursor.isAfterLast()) { final Animature animature =
	 * cursorToAnimature(cursor); AnimatureList.add(animature);
	 * cursor.moveToNext(); } cursor.close(); return AnimatureList; } public
	 * void deleteAnimature(final Animature animature) { final int id =
	 * animature.getId_Animature(); db.delete("ANIMATURE", "id" + " = " + id,
	 * null); } public Animature cursorToAnimature(final Cursor cursor) { final
	 * Animature animature = new Animature(cursor.getInt(0),
	 * cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3),
	 * cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7),
	 * cursor.getInt(8), cursor.getInt(9), cursor.getInt(10), cursor.getInt(11),
	 * cursor.getInt(12), cursor.getInt(13), cursor.getInt(14)); return
	 * animature; }
	 */

	public int readSaveColInt(final int id, final int pos)
	{
		this.db = this.dbHelper.getReadableDatabase();
		final Cursor c = db.query("SAVE", columnsSave, "id=" + id, null, null,
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
		db.delete("SAVE", "id" + " = " + id, null);
	}

	public Item readItem(final int id)
	{
		db = dbHelper.getReadableDatabase();
		final Cursor c = db.query("ITEM", columnsItem, "id=" + id, null, null,
		null, null, null);
		if (c != null)
		{
			c.moveToFirst();
		}

		final Item item = cursorToItem(c);
		db.close();
		c.close();
		return item;
	}

	public List<Item> getAllItems()
	{
		final List<Item> ItemList = new ArrayList<Item>();

		final Cursor cursor = db.query("ITEM", columnsItem, null, null, null,
		null, null);
		cursor.moveToFirst();
		while ( ! cursor.isAfterLast())
		{
			final Item item = cursorToItem(cursor);
			ItemList.add(item);
			cursor.moveToNext();
		}

		cursor.close();
		return ItemList;
	}

	public void deleteItem(final Item item)
	{
		final int id = item.getId();
		db.delete("ITEM", "id" + " = " + id, null);
	}

	public Item cursorToItem(final Cursor cursor)
	{
		final Item item;

		final int id = cursor.getInt(0);
		final String name = cursor.getString(1);
		final int type = cursor.getInt(2);
		final String description = cursor.getString(3);

		item = new Item(id, name, type, description, 0);

		return item;
	}
}
