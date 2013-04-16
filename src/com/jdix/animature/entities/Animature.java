package com.jdix.animature.entities;

import android.graphics.drawable.Drawable;

public class Animature {

	/*
	 * La clase animature contiene todos los datos de los animatures. Contiene
	 * los datos necesarios para realizar combates entre ellos mediante
	 * algoritmos que utilicen las cualidades del animature.
	 */

	public static final int	VELOCITY	= 0;
	public static final int	DEFENSE		= 1;
	public static final int	AGILITY		= 2;
	public static final int	STRENGHT	= 3;
	public static final int	PRECISSION	= 4;

	public static final int	FRONTIMG	= 0;
	public static final int	BACKIMG		= 1;

	protected int			id_Animature;
	protected String		name;
	protected double		height;
	protected double		weight;
	protected int			type;
	protected int[]			cualities;
	protected int			health;
	protected int			level_evo;
	protected Drawable[]	imgAnimature;
	protected Drawable		iconAnimature;

	public Animature()
	{
		// TODO
	}

	public Animature(int id_Animature, String name, double height,
	double weight, int type, int[] cualities, int health, int level_evo,
	Attack[] attacks, Drawable[] imgAnimature, Drawable iconAnimature)
	{
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.type = type;
		this.cualities = cualities;
		this.health = health;
		this.level_evo = level_evo;
		this.imgAnimature = imgAnimature;
		this.iconAnimature = iconAnimature;
	}

	public int getId_Animature()
	{
		return id_Animature;
	}

	public void setId_Animature(int id_Animature)
	{
		this.id_Animature = id_Animature;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getHeight()
	{
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

	public double getWeight()
	{
		return weight;
	}

	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public int[] getCualities()
	{
		return cualities;
	}

	public void setCualities(int[] cualities)
	{
		this.cualities = cualities;
	}

	public int getHealth()
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
	}

	public int getLevel_evo()
	{
		return level_evo;
	}

	public void setLevel_evo(int level_evo)
	{
		this.level_evo = level_evo;
	}

	public Drawable[] getImgAnimature()
	{
		return imgAnimature;
	}

	public void setImgAnimature(Drawable[] imgAnimature)
	{
		this.imgAnimature = imgAnimature;
	}

	public Drawable getIconAnimature()
	{
		return iconAnimature;
	}

	public void setIconAnimature(Drawable iconAnimature)
	{
		this.iconAnimature = iconAnimature;
	}
}