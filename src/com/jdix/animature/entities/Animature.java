package com.jdix.animature.entities;

import java.io.Serializable;

/**
 * @author Jordan Aranda Tejada
 */
public class Animature implements Serializable {

	private static final long	serialVersionUID	= - 7697636197467488947L;

	/**
	 * Animature's speed.
	 */
	public static final int		SPEED				= 0;
	/**
	 * Animature's defense.
	 */
	public static final int		DEFENSE				= 1;
	/**
	 * Animature's agility.
	 */
	public static final int		AGILITY				= 2;
	/**
	 * Animature's strength.
	 */
	public static final int		STRENGTH			= 3;
	/**
	 * Animature's precision.
	 */
	public static final int		PRECISION			= 4;

	/**
	 * Animature type normal.
	 */
	public static final int		NORMAL				= 1;
	/**
	 * Animature type fire.
	 */
	public static final int		FIRE				= 2;
	/**
	 * Animature type water.
	 */
	public static final int		WATER				= 4;
	/**
	 * Animature type grass.
	 */
	public static final int		GRASS				= 8;
	/**
	 * Animature type electric.
	 */
	public static final int		ELECTRIC			= 16;
	/**
	 * Animature type ice.
	 */
	public static final int		ICE					= 32;
	/**
	 * Animature type fighting.
	 */
	public static final int		FIGHTING			= 64;
	/**
	 * Animature type poison.
	 */
	public static final int		POISON				= 128;
	/**
	 * Animature type ground.
	 */
	public static final int		GROUND				= 256;
	/**
	 * Animature type flying.
	 */
	public static final int		FLYING				= 512;
	/**
	 * Animature type psychic.
	 */
	public static final int		PSYCHIC				= 1024;
	/**
	 * Animature type bug.
	 */
	public static final int		BUG					= 2048;
	/**
	 * Animature type rock.
	 */
	public static final int		ROCK				= 4096;
	/**
	 * Animature type ghost.
	 */
	public static final int		GHOST				= 8192;
	/**
	 * Animature type dragon.
	 */
	public static final int		DRAGON				= 16384;
	/**
	 * Animature tyoe dark.
	 */
	public static final int		DARK				= 32768;
	/**
	 * Animature type steel.
	 */
	public static final int		STEEL				= 65536;
	/**
	 * Animature type elemental (trick).
	 */
	public static final int		ELEMENTAL			= 131072;

	private int					id;
	private String				name;
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
	public Animature(final int id, final String name, final double height,
	final double weight, final int type, final int speed, final int defense,
	final int agility, final int strenght, final int precission,
	final int health, final int levelEvo, final int baseExp,
	final int captureRange)
	{
		this.id = id;
		this.name = name;
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

	/**
	 * Method to get Animature's id.
	 * 
	 * @return Animature's id.
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Method to set Animature's id.
	 * 
	 * @param id Animature's new id.
	 */
	public void setId(final int id)
	{
		this.id = id;
	}

	/**
	 * Method to get Animature's name.
	 * 
	 * @return Animature's name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Method to set Animature's name.
	 * 
	 * @param name Animature's new name.
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	/**
	 * Method to get Animature's height.
	 * 
	 * @return Animature's height.
	 */
	public double getHeight()
	{
		return height;
	}

	/**
	 * Method to set Animature's height.
	 * 
	 * @param height Animature's new height.
	 */
	public void setHeight(final double height)
	{
		this.height = height;
	}

	/**
	 * Method to get Animature's weight.
	 * 
	 * @return Animature's weight.
	 */
	public double getWeight()
	{
		return weight;
	}

	/**
	 * Method to set Animature's weight.
	 * 
	 * @param weight Animature's new weight.
	 */
	public void setWeight(final double weight)
	{
		this.weight = weight;
	}

	/**
	 * Method to get Animature's type in binary.
	 * 
	 * @return Animature's type in binary.
	 */
	public int getType()
	{
		return type;
	}

	/**
	 * Method to set Animature's type in binary.
	 * 
	 * @param type Animature's new type in binary.
	 */
	public void setType(final int type)
	{
		this.type = type;
	}

	/**
	 * Method to get Animature's cualities (Speed, Defense, Agility, Strength
	 * and Precision).
	 * 
	 * @return Array with Animature's cualities.
	 */
	public int[] getCualities()
	{
		return cualities;
	}

	/**
	 * Method to get Animature's health.
	 * 
	 * @return Animature's health.
	 */
	public int getHealth()
	{
		return health;
	}

	/**
	 * Method to set Animature's health.
	 * 
	 * @param health Animature's new health.
	 */
	public void setHealth(final int health)
	{
		this.health = health;
	}

	/**
	 * Method to get Animature's level to evolve.
	 * 
	 * @return The Animature's evolve level.
	 */
	public int getLevelEvo()
	{
		return levelEvo;
	}

	/**
	 * Method to set Animature's level to evolve.
	 * 
	 * @param levelEvo Animature's new evolve level.
	 */
	public void setLevelEvo(final int levelEvo)
	{
		this.levelEvo = levelEvo;
	}

	/**
	 * Method to get Animature's experience base.
	 * 
	 * @return Animature's experience base.
	 */
	public int getBaseExp()
	{
		return baseExp;
	}

	/**
	 * Method to set Animature's experience base.
	 * 
	 * @param baseExp Animature's new experience base.
	 */
	public void setBaseExp(final int baseExp)
	{
		this.baseExp = baseExp;
	}

	/**
	 * Method to get Animature's capture range.
	 * 
	 * @return Animature's capture range.
	 */
	public int getCaptureRange()
	{
		return captureRange;
	}

	/**
	 * Method to set Animature's capture range.
	 * 
	 * @param captureRange Animature's new capture range.
	 */
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