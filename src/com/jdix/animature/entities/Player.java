package com.jdix.animature.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.jdix.animature.R;
import com.jdix.animature.map.Square;
import com.jdix.animature.utils.Database;

public class Player {

	/*
	 * La clase player contiene todos los datos del protagonista y de la
	 * partida. Para poder continuar la partida se utilizan los stage para saber
	 * en que momento del juego estamos.
	 */

	public static final int	NORTH		= 0;
	public static final int	EAST		= 1;
	public static final int	SOUTH		= 2;
	public static final int	WEST		= 3;

	public static final int	NORTHLEFT	= 4;
	public static final int	NORTHRIGHT	= 5;
	public static final int	EASTLEFT	= 6;
	public static final int	EASTRIGHT	= 7;
	public static final int	SOUTHLEFT	= 8;
	public static final int	SOUTHRIGHT	= 9;
	public static final int	WESTLEFT	= 10;
	public static final int	WESTRIGHT	= 11;

	public static final int	BOY			= 0;
	public static final int	GIRL		= 1;

	public static Player	player;

	private int				id;
	private String			name;
	private int				sex;
	private String			neighborName;
	private int				idFirstAnimature;
	private int				stage;
	private Date			startDate;
	private Date			lastPlayed;
	private int				steps;
	private Capturable[]	activeAnimatures;
	private int				posX;
	private int				posY;
	private int				orientation;
	private int				lastHealingMap;
	private int				lastHealingX;
	private int				lastHealingY;
	private int				medals;
	private int				money;
	private final Bitmap	bitmap;
	private final Animature	firstAnim;
	private Vector<Item>	items;

	// private SQLiteDatabase db; // TODO check

	private Player(final int id, final String name, final int sex,
	final String neighborName, final int stage, final Date startDate,
	final Date lastPlayed, final int steps,
	final Capturable[] activeAnimatures, final int posX, final int posY,
	final int orientation, final int lastHealingMap, final int lastHealingX,
	final int lastHealingY, final int medals, final int money,
	final Vector<Item> items, final Animature firstAnim, final Context context)
	{
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.neighborName = neighborName;
		this.stage = stage;
		this.startDate = startDate;
		this.lastPlayed = lastPlayed;
		this.steps = steps;
		this.activeAnimatures = activeAnimatures;
		this.posX = posX;
		this.posY = posY;
		this.orientation = orientation;
		this.lastHealingMap = lastHealingMap;
		this.medals = medals;
		this.money = money;
		this.items = items;
		this.firstAnim = firstAnim;
		this.bitmap = BitmapFactory.decodeResource(context.getResources(),
		R.drawable.player); // TODO support girl/boy bitmaps
	}

	/**
	 * Gets the current player
	 * 
	 * @return The current player
	 */
	public static Player getInstance()
	{
		return player;
	}

	/**
	 * Sets a new player, for starting the game
	 * 
	 * @param name - The name of the player
	 * @param sex - The sex of the player
	 * @param neighborName - The name of the neighbor
	 * @param activeAnimatures - The Animatures in it's bag
	 * @param posX - The current X coordinate position
	 * @param posY - The current Y coordinate position
	 * @param orientation - The current orientation
	 * @param firstAnim - The first animature of the player
	 * @param context - The context of the application
	 */
	public static void set(final String name, final int sex,
	final String neighborName, final Capturable[] activeAnimatures,
	final int posX, final int posY, final int orientation,
	final Animature firstAnim, final Context context)
	{
		final Date now = new Date();
		player = new Player(0, name, sex, neighborName, 0, now, now, 0,
		activeAnimatures, posX, posY, orientation, 0, 0, 0, 0, 0,
		new Vector<Item>(), firstAnim, context);
	}

	/**
	 * Saves the current player to the database
	 * 
	 * @param context - The context of the application
	 */
	public void save(final Context context)
	{
		if (this.id == 0)
		{
			// TODO insert new record and update ID
		}
		else
		{
			// TODO update the save
		}
	}

	/**
	 * Loads a player from the database. It works as setPlayer(), but in this
	 * case the player is loaded from the database
	 * 
	 * @param id - The ID for the player to load
	 * @param context - The context of the application
	 */
	public static void load(final int id, final Context context)
	{
		// TODO
	}

	public int getId()
	{
		return id;
	}

	public void setId(final int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public int getSex()
	{
		return sex;
	}

	public void setSex(final int sex)
	{
		this.sex = sex;
	}

	public String getNeighborName()
	{
		return neighborName;
	}

	public void setNeighborName(final String neighborName)
	{
		this.neighborName = neighborName;
	}

	public int getStage()
	{
		return stage;
	}

	public void setStage(final int stage)
	{
		this.stage = stage;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(final Date startDate)
	{
		this.startDate = startDate;
	}

	public Date getLastPlayed()
	{
		return lastPlayed;
	}

	public void setLastPlayed(final Date lastPlayed)
	{
		this.lastPlayed = lastPlayed;
	}

	public int getSteps()
	{
		return steps;
	}

	public void setSteps(final int steps)
	{
		this.steps = steps;
	}

	public Animature[] getActiveAnimatures()
	{
		return activeAnimatures;
	}

	public void setActiveAnimatures(final Capturable[] activeAnimatures)
	{
		this.activeAnimatures = activeAnimatures;
	}

	// TODO get, add and remove individual animatures

	/**
	 * @return The first animature of the player
	 */
	public Animature getFirstAnimature()
	{
		return firstAnim;
	}

	public int getX()
	{
		return posX;
	}

	public void setX(final int posX)
	{
		this.posX = posX;
	}

	public int getY()
	{
		return posY;
	}

	public void setY(final int posY)
	{
		this.posY = posY;
	}

	public int getOrientation()
	{
		return orientation;
	}

	public void setOrientation(final int orientation)
	{
		this.orientation = orientation;
	}

	/**
	 * @return The last map there healed
	 */
	public int getLastHealingMap()
	{
		return lastHealingMap;
	}

	/**
	 * @return The X coordinate of the last healing
	 */
	public int getLastHealingX()
	{
		return lastHealingX;
	}

	/**
	 * @return The X coordinate of the last healing
	 */
	public int getLastHealingY()
	{
		return lastHealingY;
	}

	/**
	 * Sets the healing position to current. In the future is expected to heal
	 * the animatures
	 * 
	 * @param map - The current map index
	 */
	public void heal(final int map)
	{
		this.lastHealingMap = map;
		this.lastHealingX = posX;
		this.lastHealingY = posY;
	}

	public int getMedals()
	{
		return medals;
	}

	public void setMedals(final int medals)
	{
		this.medals = medals;
	}

	public int getMoney()
	{
		return money;
	}

	public void setMoney(final int money)
	{
		this.money = money;
	}

	public String getPlayedTime()
	{
		final Calendar calendar = new GregorianCalendar();

		calendar.set(calendar.get(Calendar.YEAR),
		calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE),
		calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
		calendar.get(Calendar.SECOND));

		this.lastPlayed = new Date(calendar.getTimeInMillis());

		final long playedTime = this.lastPlayed.getTime()
		- this.startDate.getTime();
		final long hour = playedTime / 3600000;
		final long rHour = playedTime % 3600000;
		final long minute = rHour / 60000;
		final long rMinute = rHour % 60000;
		final long second = rMinute / 1000;

		return hour + ":" + minute + ":" + second;
	}

	/**
	 * @param position - The position of the player
	 * @return The current bitmap of the player.
	 */
	public Bitmap getBitmap(final int position)
	{
		return Bitmap.createBitmap(bitmap, position
		* Square.getSprite().getSize(),
		(int) Math.round(sex * 1.5 * position * Square.getSprite().getSize()),
		Square.getSprite().getSize(),
		(int) Math.round(1.5 * Square.getSprite().getSize()));
	}

	/**
	 * Changes the player items vector.
	 * 
	 * @param items - The items of the player
	 */
	public void setItems(final Vector<Item> items)
	{
		this.items = items;
	}

	// TODO add and remove individual items

	/**
	 * Method to get the player captured animatures.
	 * 
	 * @param context - The game context
	 * @return The number of captured animatures of player
	 */
	public int getCaptured(final Context context)
	{
		int total = 0;
		final SQLiteDatabase db = (new Database(context)).getReadableDatabase();

		final Cursor c = db.rawQuery(
		"SELECT COUNT(*) FROM CAPTURABLE WHERE save="
		+ Player.getInstance().getId(), null);

		if (c.moveToFirst())
		{
			while ( ! c.isAfterLast())
			{
				total = c.getInt(0);
			}
		}
		c.close();
		db.close();
		return total;
	}

	/**
	 * Method to get the player viewed animatures.
	 * 
	 * @param context - The game context
	 * @return The number of viewed animatures of player
	 */
	public int getViewed(final Context context)
	{
		int total = 0;
		final SQLiteDatabase db = (new Database(context)).getReadableDatabase();

		final Cursor c = db.rawQuery("SELECT COUNT(*) FROM VIEWED WHERE save="
		+ getId(), null);

		if (c.moveToFirst())
		{
			while ( ! c.isAfterLast())
			{
				total = c.getInt(0);
			}
		}
		c.close();
		db.close();
		return total;
	}

}