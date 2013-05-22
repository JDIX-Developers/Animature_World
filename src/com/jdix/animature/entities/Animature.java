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
	public static final int		STRENGTH			= 3;
	public static final int		PRECISION			= 4;

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
	public static final int		ELEMENTAL			= 131072;

	private int					id;
	private String				name;
	private String				description;
	private double				height;
	private double				weight;
	private int					type;
	private final int[]			cualities			= new int[5];
	private int					health;
	private int					levelEvo;
	private int					baseExp;
	private int					captureRange;

	/**
	 * Constructor
	 */
	public Animature()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * 
	 * @param id Animature's id
	 * @param name Animature's name
	 * @param description Animature's description
	 * @param height Animature's height
	 * @param weight Animature's weight
	 * @param type Animature's type
	 * @param speed Animature's speed
	 * @param defense Animature's defense
	 * @param agility Animature's agility
	 * @param strenght Animature's strength
	 * @param precission Animature's precision
	 * @param health Animature's health
	 * @param levelEvo Animature's level to evolve
	 * @param baseExp Animature's experience
	 * @param captureRange Animature's capture range
	 */
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
		this.cualities[STRENGTH] = strenght;
		this.cualities[PRECISION] = precission;
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

	public int getVelocity()
	{
		return this.cualities[Animature.SPEED];
	}

	public void setVelocity(final int velocity)
	{
		this.cualities[Animature.SPEED] = velocity;
	}

	public int getDefense()
	{
		return this.cualities[Animature.DEFENSE];
	}

	public void setDefense(final int defense)
	{
		this.cualities[Animature.DEFENSE] = defense;
	}

	public int getAgility()
	{
		return this.cualities[Animature.AGILITY];
	}

	public void setAgility(final int agility)
	{
		this.cualities[Animature.AGILITY] = agility;
	}

	public int getStrenght()
	{
		return this.cualities[Animature.STRENGTH];
	}

	public void setStrenght(final int strenght)
	{
		this.cualities[Animature.STRENGTH] = strenght;
	}

	public int getPrecission()
	{
		return this.cualities[Animature.PRECISION];
	}

	public void setPrecission(final int precission)
	{
		this.cualities[Animature.PRECISION] = precission;
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