package com.jdix.animature.entities;

import android.content.res.Resources;

import com.jdix.animature.R;

/**
 * @author Jordan Aranda Tejada
 */
public class AnimatureOld {

	public static final int	SPEED		= 0;
	public static final int	DEFENSE		= 1;
	public static final int	AGILITY		= 2;
	public static final int	STRENGTH	= 3;
	public static final int	PRECISION	= 4;

	public static final int	NORMAL		= 1;
	public static final int	FIRE		= 2;
	public static final int	WATER		= 4;
	public static final int	GRASS		= 8;
	public static final int	ELECTRIC	= 16;
	public static final int	ICE			= 32;
	public static final int	FIGHTING	= 64;
	public static final int	POISON		= 128;
	public static final int	GROUND		= 256;
	public static final int	FLYING		= 512;
	public static final int	PSYCHIC		= 1024;
	public static final int	BUG			= 2048;
	public static final int	ROCK		= 4096;
	public static final int	GHOST		= 8192;
	public static final int	DRAGON		= 16384;
	public static final int	DARK		= 32768;
	public static final int	STEEL		= 65536;
	public static final int	ELEMENTAL	= 131072;

	public static final int	OK			= 0;
	public static final int	PARALYZED	= 1;
	public static final int	BURNED		= 2;
	public static final int	POISONED	= 3;
	public static final int	SLEEPED		= 4;
	public static final int	FROZEN		= 5;

	private int				id;
	private String			name;
	private double			height;
	private double			weight;
	private int				type;
	private final int[]		cualities	= new int[5];
	private int				health;
	private int				levelEvo;
	private int				baseExp;
	private int				captureRange;

	/**
	 * Constructor
	 * 
	 * @param id Animature's id
	 */
	public Animature(final int id)
	{
		this.id = id;
		this.name = Resources.getSystem().getStringArray(
		R.array.animature_names)[id - 1];
		this.height = Double.parseDouble(Resources.getSystem().getStringArray(
		R.array.animature_height)[id - 1]);
		this.weight = Double.parseDouble(Resources.getSystem().getStringArray(
		R.array.animature_weight)[id - 1]);
		this.type = Integer.parseInt(Resources.getSystem().getStringArray(
		R.array.animature_type)[id - 1]);
		this.cualities[SPEED] = Integer.parseInt(Resources.getSystem()
		.getStringArray(R.array.animature_speed)[id - 1]);
		this.cualities[DEFENSE] = Integer.parseInt(Resources.getSystem()
		.getStringArray(R.array.animature_defense)[id - 1]);
		this.cualities[AGILITY] = Integer.parseInt(Resources.getSystem()
		.getStringArray(R.array.animature_agility)[id - 1]);
		this.cualities[STRENGTH] = Integer.parseInt(Resources.getSystem()
		.getStringArray(R.array.animature_strength)[id - 1]);
		this.cualities[PRECISION] = Integer.parseInt(Resources.getSystem()
		.getStringArray(R.array.animature_precision)[id - 1]);
		this.health = Integer.parseInt(Resources.getSystem().getStringArray(
		R.array.animature_health)[id - 1]);
		this.levelEvo = Integer.parseInt(Resources.getSystem().getStringArray(
		R.array.animature_level_evo)[id - 1]);
		this.baseExp = Integer.parseInt(Resources.getSystem().getStringArray(
		R.array.animature_base_exp)[id - 1]);
		this.captureRange = Integer.parseInt(Resources.getSystem()
		.getStringArray(R.array.animature_capture_range)[id - 1]);
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