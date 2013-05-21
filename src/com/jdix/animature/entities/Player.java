package com.jdix.animature.entities;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

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

	private int				id_Player;
	private String			name;
	private int				sex;
	private String			neighborName;
	private int				idFirstAnimature;
	private int				stage;
	private long			started;
	private long			last_Played;
	private int				steps;
	private Animature[]		activeAnimatures;
	private int				coord_X;
	private int				coord_Y;
	private int				orientation;
	private int				last_Healing;
	private int				medals;
	private int				money;
	private final Bitmap	bitmap;
	private Vector<Item>	playerItems;
	private SQLiteDatabase	db;

	private Player(final int idPlayer, final String name, final int sex,
	final String neighborName, final int stage, final long started,
	final long last_Played, final int steps,
	final Animature[] activeAnimatures, final int coord_X, final int coord_Y,
	final int orientation, final int last_Healing, final int medals,
	final int money, final Bitmap bitmap, final Vector<Item> playerItems)
	{
		this.id_Player = idPlayer;
		this.name = name;
		this.sex = sex;
		this.neighborName = neighborName;
		this.stage = stage;
		this.started = started;
		this.last_Played = last_Played;
		this.steps = steps;
		this.activeAnimatures = activeAnimatures;
		this.coord_X = coord_X;
		this.coord_Y = coord_Y;
		this.orientation = orientation;
		this.last_Healing = last_Healing;
		this.medals = medals;
		this.money = money;
		this.playerItems = playerItems;
		this.bitmap = bitmap;
	}

	public static Player getInstance()
	{
		return player;
	}

	public static void setPlayer(final int idPlayer, final String name,
	final int sex, final String neighborName, final int stage,
	final long started, final long last_Played, final int steps,
	final Animature[] activeAnimatures, final int coord_X, final int coord_Y,
	final int orientation, final int last_Healing, final int medals,
	final int money, final Bitmap bitmap, final Vector<Item> playerItems)
	{
		player = new Player(idPlayer, name, sex, neighborName, stage, started,
		last_Played, steps, activeAnimatures, coord_X, coord_Y, orientation,
		last_Healing, medals, money, bitmap, playerItems);
	}

	public int getId_Player()
	{
		return id_Player;
	}

	public void setId_Player(final int id_Player)
	{
		this.id_Player = id_Player;
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

	public long getStarted()
	{
		return started;
	}

	public void setStarted(final long started)
	{
		this.started = started;
	}

	public long getLast_Played()
	{
		return last_Played;
	}

	public void setLast_Played(final long last_Played)
	{
		this.last_Played = last_Played;
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

	public void setActiveAnimatures(final Animature[] activeAnimatures)
	{
		this.activeAnimatures = activeAnimatures;
	}

	public int getCoord_X()
	{
		return coord_X;
	}

	public void setCoord_X(final int coord_X)
	{
		this.coord_X = coord_X;
	}

	public int getCoord_Y()
	{
		return coord_Y;
	}

	public void setCoord_Y(final int coord_Y)
	{
		this.coord_Y = coord_Y;
	}

	public int getOrientation()
	{
		return orientation;
	}

	public void setOrientation(final int orientation)
	{
		this.orientation = orientation;
	}

	public int getLast_Healing()
	{
		return last_Healing;
	}

	public void setLast_Healing(final int last_Healing)
	{
		this.last_Healing = last_Healing;
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

		this.last_Played = calendar.getTimeInMillis();

		final long playedTime = this.last_Played - this.started;
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
	 * @param playerItems
	 */
	public void setPlayerItems(final Vector<Item> playerItems)
	{
		this.playerItems = playerItems;
	}

	/**
	 * Method to get the player captured animatures.
	 * 
	 * @param context The game context
	 * @return The number of captured animatures of player
	 */
	public int getPlayerCaptured(final Context context)
	{
		int total = 0;
		db = (new Database(context)).getReadableDatabase();
		final Cursor c = db.rawQuery(
		"SELECT COUNT(*) FROM CAPTURABLE WHERE save="
		+ Player.getInstance().getId_Player(), null);
		if (c.getCount() > 0)
		{
			c.moveToFirst();
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
	 * @param context The game context
	 * @return The number of viewed animatures of player
	 */
	public int getPlayerViewed(final Context context)
	{
		int total = 0;
		db = (new Database(context)).getReadableDatabase();
		final Cursor c = db.rawQuery("SELECT COUNT(*) FROM VIEWED WHERE save="
		+ Player.getInstance().getId_Player(), null);
		if (c.getCount() > 0)
		{
			c.moveToFirst();
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