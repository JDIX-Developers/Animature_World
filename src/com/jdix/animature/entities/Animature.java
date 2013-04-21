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
	protected int			current_level;
	protected int			level_evo;
	protected Drawable[]	imgAnimature;
	protected Drawable		iconAnimature;

	private final int		normal		= 0;
	private final int		fire		= 1;
	private final int		water		= 2;
	private final int		grass		= 3;
	private final int		electric	= 4;
	private final int		ice			= 5;
	private final int		fighting	= 6;
	private final int		poison		= 7;
	private final int		ground		= 8;
	private final int		flying		= 9;
	private final int		psychic		= 10;
	private final int		bug			= 11;
	private final int		rock		= 12;
	private final int		ghost		= 13;
	private final int		dragon		= 14;
	private final int		dark		= 15;
	private final int		steel		= 16;

	public Animature()
	{
		// TODO
	}

	public Animature(final int id_Animature, final String name,
	final double height, final double weight, final int type,
	final int[] cualities, final int health, final int current_level,
	final int level_evo, final Attack[] attacks, final Drawable[] imgAnimature,
	final Drawable iconAnimature)
	{
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.type = type;
		this.cualities = cualities;
		this.health = health;
		this.current_level = current_level;
		this.level_evo = level_evo;
		this.imgAnimature = imgAnimature;
		this.iconAnimature = iconAnimature;
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

	public int getCurrent_level()
	{
		return current_level;
	}

	public void setCurrent_level(final int current_level)
	{
		this.current_level = current_level;
	}

	public int getLevel_evo()
	{
		return level_evo;
	}

	public void setLevel_evo(final int level_evo)
	{
		this.level_evo = level_evo;
	}

	public Drawable[] getImgAnimature()
	{
		return imgAnimature;
	}

	public void setImgAnimature(final Drawable[] imgAnimature)
	{
		this.imgAnimature = imgAnimature;
	}

	public Drawable getIconAnimature()
	{
		return iconAnimature;
	}

	public void setIconAnimature(final Drawable iconAnimature)
	{
		this.iconAnimature = iconAnimature;
	}
}