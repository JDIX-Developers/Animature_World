package com.jdix.animature.entities;

import android.content.Context;

/**
 * @author Jordan Aranda Tejada
 */
public class Attack {

	private int				id;
	private String			name;
	private int				type;
	private int				maxpp;
	private boolean			active;
	private int				attribute;
	private int				power;
	private int				probability;				// 0 - 100 (Example: 60%
														// - 60)
	private boolean			isFirst;

	public static final int	NORMAL_EFFECTIVE	= 0;
	public static final int	VERY_EFFECTIVE		= 1;
	public static final int	FEW_EFFECTIVE		= 2;
	public static final int	NOT_EFFECTIVE		= 3;

	public Attack()
	{

	}

	public Attack(final int id, final String name, final int type,
	final int maxpp, final boolean active, final int attribute,
	final int power, final int probability, final boolean isFirst)
	{
		this.id = id;
		this.name = name;
		this.type = type;
		this.maxpp = maxpp;
		this.active = active;
		this.attribute = attribute;
		this.power = power;
		this.probability = probability;
		this.isFirst = isFirst;
	}

	public boolean getActive()
	{
		return active;
	}

	public int getAttribute()
	{
		return attribute;
	}

	public double getProbability()
	{
		return probability;
	}

	public int getPower()
	{
		return power;
	}

	public void setPower(final int power)
	{
		this.power = power;
	}

	public boolean isFirst()
	{
		return isFirst;
	}

	public void setFirst(final boolean isFirst)
	{
		this.isFirst = isFirst;
	}

	/**
	 * @param animature The defender Animature's type.
	 * @param context - The context of the application
	 * @return Return 0 if normal effective, 1 if very effective, 2 if
	 *         ineffective and 3 if not effective
	 */
	public int getEffectivenes(final Animature animature, final Context context)
	{
		int effective = NORMAL_EFFECTIVE;
		switch (this.type)
		{
			case Animature.NORMAL:
				switch (animature.getType(context))
				{
					case Animature.ROCK:
					case Animature.STEEL:
						effective = FEW_EFFECTIVE;
					break;
					case Animature.GHOST:
					case Animature.ELEMENTAL:
						effective = NOT_EFFECTIVE;
				}
			break;
			case Animature.FIRE:
				switch (animature.getType(context))
				{
					case Animature.GRASS:
					case Animature.ICE:
					case Animature.BUG:
					case Animature.STEEL:
						effective = VERY_EFFECTIVE;
					break;
					case Animature.FIRE:
					case Animature.WATER:
					case Animature.ROCK:
					case Animature.DRAGON:
						effective = FEW_EFFECTIVE;
					break;
					case Animature.ELEMENTAL:
						effective = NOT_EFFECTIVE;
					break;
				}
			break;
			case Animature.WATER:
				switch (animature.getType(context))
				{
					case Animature.FIRE:
					case Animature.GROUND:
					case Animature.ROCK:
						effective = VERY_EFFECTIVE;
					break;
					case Animature.WATER:
					case Animature.GRASS:
					case Animature.DRAGON:
						effective = FEW_EFFECTIVE;
					break;
					case Animature.ELEMENTAL:
						effective = NOT_EFFECTIVE;
					break;
				}
			break;
			case Animature.ELECTRIC:
				switch (animature.getType(context))
				{
					case Animature.WATER:
					case Animature.FLYING:
						effective = VERY_EFFECTIVE;
					break;
					case Animature.ELECTRIC:
					case Animature.GRASS:
					case Animature.DRAGON:
						effective = FEW_EFFECTIVE;
					break;
					case Animature.GROUND:
					case Animature.ELEMENTAL:
						effective = NOT_EFFECTIVE;
					break;
				}
			break;
			case Animature.GRASS:
				switch (animature.getType(context))
				{
					case Animature.WATER:
					case Animature.GROUND:
					case Animature.ROCK:
						effective = VERY_EFFECTIVE;
					break;
					case Animature.FIRE:
					case Animature.GRASS:
					case Animature.POISON:
					case Animature.FLYING:
					case Animature.BUG:
					case Animature.DRAGON:
					case Animature.STEEL:
						effective = FEW_EFFECTIVE;
					break;
					case Animature.ELEMENTAL:
						effective = NOT_EFFECTIVE;
					break;
				}
			break;
			case Animature.ICE:
				switch (animature.getType(context))
				{
					case Animature.GRASS:
					case Animature.GROUND:
					case Animature.FLYING:
					case Animature.DRAGON:
						effective = VERY_EFFECTIVE;
					break;
					case Animature.FIRE:
					case Animature.WATER:
					case Animature.ICE:
					case Animature.STEEL:
						effective = FEW_EFFECTIVE;
					break;
					case Animature.ELEMENTAL:
						effective = NOT_EFFECTIVE;
					break;
				}
			break;
			case Animature.FIGHTING:
				switch (animature.getType(context))
				{
					case Animature.NORMAL:
					case Animature.ICE:
					case Animature.ROCK:
					case Animature.DARK:
					case Animature.STEEL:
						effective = VERY_EFFECTIVE;
					break;
					case Animature.POISON:
					case Animature.FLYING:
					case Animature.PSYCHIC:
					case Animature.BUG:
						effective = FEW_EFFECTIVE;
					break;
					case Animature.GHOST:
					case Animature.ELEMENTAL:
						effective = NOT_EFFECTIVE;
					break;
				}
			break;
			case Animature.POISON:
				switch (animature.getType(context))
				{
					case Animature.GRASS:
						effective = VERY_EFFECTIVE;
					break;
					case Animature.POISON:
					case Animature.GROUND:
					case Animature.ROCK:
					case Animature.GHOST:
						effective = FEW_EFFECTIVE;
					break;
					case Animature.STEEL:
					case Animature.ELEMENTAL:
						effective = NOT_EFFECTIVE;
					break;
				}
			break;
			case Animature.GROUND:
				switch (animature.getType(context))
				{
					case Animature.FIRE:
					case Animature.ELECTRIC:
					case Animature.POISON:
					case Animature.ROCK:
					case Animature.STEEL:
						effective = VERY_EFFECTIVE;
					break;
					case Animature.GRASS:
					case Animature.BUG:
						effective = FEW_EFFECTIVE;
					break;
					case Animature.FLYING:
					case Animature.ELEMENTAL:
						effective = NOT_EFFECTIVE;
					break;
				}
			break;
			case Animature.FLYING:
				switch (animature.getType(context))
				{
					case Animature.GRASS:
					case Animature.FIGHTING:
					case Animature.BUG:
						effective = VERY_EFFECTIVE;
					break;
					case Animature.ELECTRIC:
					case Animature.ROCK:
					case Animature.STEEL:
						effective = FEW_EFFECTIVE;
					break;
					case Animature.ELEMENTAL:
						effective = NOT_EFFECTIVE;
					break;
				}
			break;
			case Animature.PSYCHIC:
				switch (animature.getType(context))
				{
					case Animature.FIGHTING:
					case Animature.POISON:
						effective = VERY_EFFECTIVE;
					break;
					case Animature.PSYCHIC:
					case Animature.STEEL:
						effective = FEW_EFFECTIVE;
					break;
					case Animature.DARK:
					case Animature.ELEMENTAL:
						effective = NOT_EFFECTIVE;
					break;
				}
			break;
			case Animature.BUG:
				switch (animature.getType(context))
				{
					case Animature.GRASS:
					case Animature.PSYCHIC:
					case Animature.DARK:
						effective = VERY_EFFECTIVE;
					break;
					case Animature.FIRE:
					case Animature.FIGHTING:
					case Animature.POISON:
					case Animature.FLYING:
					case Animature.GHOST:
					case Animature.STEEL:
						effective = FEW_EFFECTIVE;
					break;
					case Animature.ELEMENTAL:
						effective = NOT_EFFECTIVE;
					break;
				}
			break;
			case Animature.ROCK:
				switch (animature.getType(context))
				{
					case Animature.FIRE:
					case Animature.ICE:
					case Animature.FLYING:
					case Animature.BUG:
						effective = VERY_EFFECTIVE;
					break;
					case Animature.FIGHTING:
					case Animature.GROUND:
					case Animature.STEEL:
						effective = FEW_EFFECTIVE;
					break;
					case Animature.ELEMENTAL:
						effective = NOT_EFFECTIVE;
					break;
				}
			break;
			case Animature.GHOST:
				switch (animature.getType(context))
				{
					case Animature.PSYCHIC:
					case Animature.GHOST:
						effective = VERY_EFFECTIVE;
					break;
					case Animature.DARK:
					case Animature.STEEL:
						effective = FEW_EFFECTIVE;
					break;
					case Animature.NORMAL:
					case Animature.ELEMENTAL:
						effective = NOT_EFFECTIVE;
					break;
				}
			break;
			case Animature.DRAGON:
				switch (animature.getType(context))
				{
					case Animature.DRAGON:
						effective = VERY_EFFECTIVE;
					break;
					case Animature.STEEL:
						effective = FEW_EFFECTIVE;
					break;
					case Animature.ELEMENTAL:
						effective = NOT_EFFECTIVE;
					break;
				}
			break;
			case Animature.DARK:
				switch (animature.getType(context))
				{
					case Animature.PSYCHIC:
					case Animature.GHOST:
						effective = VERY_EFFECTIVE;
					break;
					case Animature.FIGHTING:
					case Animature.DARK:
					case Animature.STEEL:
						effective = FEW_EFFECTIVE;
					break;
					case Animature.ELEMENTAL:
						effective = NOT_EFFECTIVE;
					break;
				}
			break;
			case Animature.STEEL:
				switch (animature.getType(context))
				{
					case Animature.ICE:
					case Animature.ROCK:
						effective = VERY_EFFECTIVE;
					break;
					case Animature.FIRE:
					case Animature.WATER:
					case Animature.ELECTRIC:
					case Animature.STEEL:
						effective = NOT_EFFECTIVE;
					break;
					case Animature.ELEMENTAL:
						effective = NOT_EFFECTIVE;
					break;
				}
			break;
			case Animature.ELEMENTAL:
				effective = VERY_EFFECTIVE;
			break;
		}

		return effective;

	}

	public Attack(final int id, final Context context)
	{
		// TODO Auto-generated method stub
	}

}