package com.jdix.animature.entities;

import java.io.Serializable;

/**
 * @author Jordan Aranda Tejada
 */
public class Animature implements Serializable {

	private static final long	serialVersionUID	= - 7697636197467488947L;

	public static final int		SPEED				= 0;
	public static final int		DEFENSE				= 1;
	public static final int		AGILITY				= 2;
	public static final int		STRENGHT			= 3;
	public static final int		PRECISSION			= 4;

	public static final int		NORMAL				= 1;
	public static final int		FIRE				= 2;
	public static final int		WATER				= 4;
	public static final int		GRASS				= 8;
	public static final int		ELECTRIC			= 16;
	public static final int		ICE					= 32;
	public static final int		FIGHTING			= 64;
	public static final int		POISON				= 128;
	public static final int		GROUND				= 256;
	public static final int		FLYING				= 512;
	public static final int		PSYCHIC				= 1024;
	public static final int		BUG					= 2048;
	public static final int		ROCK				= 4096;
	public static final int		GHOST				= 8192;
	public static final int		DRAGON				= 16384;
	public static final int		DARK				= 32768;
	public static final int		STEEL				= 65536;

	private int					id;
	private String				name;
	private String				description;
	private double				height;
	private double				weight;
	private int					type;
	private int[]				cualities			= new int[5];
	private int					health;
	private int					levelEvo;
	private int					baseExp;
	private int					captureRange;

	public Animature(final int id, final String name, final String description,
	final double height, final double weight, final int type, final int speed,
	final int defense, final int agility, final int strenght,
	final int precission, final int health, final int levelEvo,
	final int baseExp, final int captureRange)
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.height = height;
		this.weight = weight;
		this.type = type;
		this.cualities[SPEED] = speed;
		this.cualities[DEFENSE] = defense;
		this.cualities[AGILITY] = agility;
		this.cualities[STRENGHT] = strenght;
		this.cualities[PRECISSION] = precission;
		this.health = health;
		this.levelEvo = levelEvo;
		this.baseExp = baseExp;
		this.captureRange = captureRange;
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(final String description)
	{
		this.description = description;
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

	public int getLevelEvo()
	{
		return levelEvo;
	}

	public void setLevelEvo(final int levelEvo)
	{
		this.levelEvo = levelEvo;
	}

	public int getBaseExp()
	{
		return baseExp;
	}

	public void setBaseExp(final int baseExp)
	{
		this.baseExp = baseExp;
	}

	public int getCaptureRange()
	{
		return captureRange;
	}

	public void setCaptureRange(final int captureRange)
	{
		this.captureRange = captureRange;
	}

	/**
	 * @param type - The type to check
	 * @return if the animature is of the given type
	 */
	public boolean isOfType(final int type)
	{
		return (this.type & type) == type;
	}
}