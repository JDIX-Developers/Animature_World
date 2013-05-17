package com.jdix.animature.entities;

public class Animature {

	/*
	 * La clase animature contiene todos los datos de los animatures. Contiene
	 * los datos necesarios para realizar combates entre ellos mediante
	 * algoritmos que utilicen las cualidades del animature.
	 */

	public static final int	SPEED		= 0;
	public static final int	DEFENSE		= 1;
	public static final int	AGILITY		= 2;
	public static final int	STRENGHT	= 3;
	public static final int	PRECISSION	= 4;

	public static final int	FRONTIMG	= 0;
	public static final int	BACKIMG		= 1;

	public static final int	NORMAL		= 0;
	public static final int	FIRE		= 1;
	public static final int	WATER		= 2;
	public static final int	GRASS		= 3;
	public static final int	ELECTRIC	= 4;
	public static final int	ICE			= 5;
	public static final int	FIGHTING	= 6;
	public static final int	POISON		= 7;
	public static final int	GROUND		= 8;
	public static final int	FLYING		= 9;
	public static final int	PSYCHIC		= 10;
	public static final int	BUG			= 11;
	public static final int	ROCK		= 12;
	public static final int	GHOST		= 13;
	public static final int	DRAGON		= 14;
	public static final int	DARK		= 15;
	public static final int	STEEL		= 16;

	protected int			id_Animature;
	protected String		name;
	protected double		height;
	protected double		weight;
	protected int			type;
	protected int			type2;
	protected int[]			cualities	= new int[5];
	protected int			health;
	protected int			level_evo;
	protected int			baseExp;

	public Animature()
	{
		// TODO
	}

	public Animature(final int id_Animature, final String name,
	final double height, final double weight, final int type, final int type2,
	final int speed, final int defense, final int agility, final int strenght,
	final int precission, final int health, final int level_evo,
	final int baseExp)
	{
		this.id_Animature = id_Animature;
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.type = type;
		this.type2 = type2;
		this.cualities[SPEED] = speed;
		this.cualities[DEFENSE] = defense;
		this.cualities[AGILITY] = agility;
		this.cualities[STRENGHT] = strenght;
		this.cualities[PRECISSION] = precission;
		this.health = health;
		this.level_evo = level_evo;
		this.baseExp = baseExp;
	}

	public int getId_Animature()
	{
		return id_Animature;
	}

	public void setId_Animature(final int id_Animature)
	{
		this.id_Animature = id_Animature;
	}

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public double getHeight()
	{
		return height;
	}

	public void setHeight(final double height)
	{
		this.height = height;
	}

	public double getWeight()
	{
		return weight;
	}

	public void setWeight(final double weight)
	{
		this.weight = weight;
	}

	public int getType()
	{
		return type;
	}

	public void setType(final int type)
	{
		this.type = type;
	}

	public int getType2()
	{
		return type2;
	}

	public void setType2(final int type2)
	{
		this.type2 = type2;
	}

	public int[] getCualities()
	{
		return cualities;
	}

	public void setCualities(final int[] cualities)
	{
		this.cualities = cualities;
	}

	public int getHealth()
	{
		return health;
	}

	public void setHealth(final int health)
	{
		this.health = health;
	}

	public int getLevel_evo()
	{
		return level_evo;
	}

	public void setLevel_evo(final int level_evo)
	{
		this.level_evo = level_evo;
	}

	public int getBaseExp()
	{
		return baseExp;
	}

	public void setBaseExp(final int baseExp)
	{
		this.baseExp = baseExp;
	}
}