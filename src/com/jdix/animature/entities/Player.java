package com.jdix.animature.entities;

import java.util.Vector;

import android.graphics.drawable.Drawable;

public class Player {
/*
 * La clase player contiene todos los datos del protagonista
 * y de la partida. Para poder continuar la partida se utilizan los stage
 * para saber en que momento del juego estamos.
 */

	public static final int	NORTH	= 0;
	public static final int	EAST	= 1;
	public static final int	SOUTH	= 2;
	public static final int	WEST	= 3;

	private int				id_Player;
	private String			name;
	private int				sex;				// 0-Boy, 1-Girl
	private String			neighborName;
	private int				stage;
	private int				started;
	private int				last_Played;
	private int				steps;
	private Animature[]		activeAnimatures;
	private int				coord_X;
	private int				coord_Y;
	private int				orientation;
	private int				last_Healing;
	private int				medals;
	private int				money;
	private Drawable[]		imgPlayer;
	private Vector<Item>	playerItems;

	public Player()
	{

	}

	public Player(int id_Animature, String name, int sex, String neighborName,
	int stage, int started, int last_Played, int steps,
	Animature[] activeAnimatures, int coord_X, int coord_Y, int orientation,
	int last_Healing, int medals, int money, Drawable[] imgPlayer,
	Vector<Item> playerItems)
	{
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
		this.imgPlayer = imgPlayer;
		this.playerItems = playerItems;
	}

	public int getId_Player()
	{
		return id_Player;
	}

	public void setId_Player(int id_Player)
	{
		this.id_Player = id_Player;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getSex()
	{
		return sex;
	}

	public void setSex(int sex)
	{
		this.sex = sex;
	}

	public String getNeighborName()
	{
		return neighborName;
	}

	public void setNeighborName(String neighborName)
	{
		this.neighborName = neighborName;
	}

	public int getStage()
	{
		return stage;
	}

	public void setStage(int stage)
	{
		this.stage = stage;
	}

	public int getStarted()
	{
		return started;
	}

	public void setStarted(int started)
	{
		this.started = started;
	}

	public int getLast_Played()
	{
		return last_Played;
	}

	public void setLast_Played(int last_Played)
	{
		this.last_Played = last_Played;
	}

	public int getSteps()
	{
		return steps;
	}

	public void setSteps(int steps)
	{
		this.steps = steps;
	}

	public Animature[] getActiveAnimatures()
	{
		return activeAnimatures;
	}

	public void setActiveAnimatures(Animature[] activeAnimatures)
	{
		this.activeAnimatures = activeAnimatures;
	}

	public int getCoord_X()
	{
		return coord_X;
	}

	public void setCoord_X(int coord_X)
	{
		this.coord_X = coord_X;
	}

	public int getCoord_Y()
	{
		return coord_Y;
	}

	public void setCoord_Y(int coord_Y)
	{
		this.coord_Y = coord_Y;
	}

	public int getOrientation()
	{
		return orientation;
	}

	public void setOrientation(int orientation)
	{
		this.orientation = orientation;
	}

	public int getLast_Healing()
	{
		return last_Healing;
	}

	public void setLast_Healing(int last_Healing)
	{
		this.last_Healing = last_Healing;
	}

	public int getMedals()
	{
		return medals;
	}

	public void setMedals(int medals)
	{
		this.medals = medals;
	}

	public int getMoney()
	{
		return money;
	}

	public void setMoney(int money)
	{
		this.money = money;
	}

	public Drawable[] getImgPlayer()
	{
		return imgPlayer;
	}

	public void setImgPlayer(Drawable[] imgPlayer)
	{
		this.imgPlayer = imgPlayer;
	}

	public Vector<Item> getPlayerItems()
	{
		return playerItems;
	}

	public void setPlayerItems(Vector<Item> playerItems)
	{
		this.playerItems = playerItems;
	}
}