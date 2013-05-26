package com.jdix.animature.entities;

import android.content.Context;
import android.content.res.TypedArray;

import com.jdix.animature.R;

/**
 * @author Jordan Aranda Tejada
 */
public class Attack {

	private final int		id;
	private final String	name;
	private final int		type;
	private final boolean	active;
	private final int		attribute;
	private final int		maxPP;
	private final int		power;
	private final int		probability;
	private final boolean	isFirst;

	public static Attack[]	attacks;

	public static final int	NORMAL_EFFECTIVE	= 0;
	public static final int	VERY_EFFECTIVE		= 1;
	public static final int	FEW_EFFECTIVE		= 2;
	public static final int	NOT_EFFECTIVE		= 3;

	private Attack(final int id, final Context context)
	{
		this.id = id;
		TypedArray array;

		this.name = context.getResources()
		.getStringArray(R.array.attacks_names)[id - 1];

		array = context.getResources().obtainTypedArray(R.array.attack_type);
		this.type = array.getInt(id - 1, 0);

		array = context.getResources().obtainTypedArray(R.array.attack_power);
		this.power = array.getInt(id - 1, 0);

		array = context.getResources().obtainTypedArray(
		R.array.attack_probability);
		this.probability = array.getInt(id - 1, 0);

		array = context.getResources().obtainTypedArray(R.array.attack_max_pp);
		this.maxPP = array.getInt(id - 1, 0);

		array = context.getResources()
		.obtainTypedArray(R.array.attack_is_first);
		this.isFirst = array.getInt(id - 1, 0) == 1;

		array = context.getResources().obtainTypedArray(
		R.array.attack_is_pasive);
		this.active = array.getInt(id - 1, 0) == 0;

		array = context.getResources().obtainTypedArray(
		R.array.attack_attributte);
		this.attribute = array.getInt(id - 1, 0);

		array.recycle();
	}

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public int getType()
	{
		return type;
	}

	public boolean isActive()
	{
		return active;
	}

	public int getAttribute()
	{
		return attribute;
	}

	public int getMaxPP()
	{
		return maxPP;
	}

	public int getPower()
	{
		return power;
	}

	public int getProbability()
	{
		return probability;
	}

	public boolean isFirst()
	{
		return isFirst;
	}

	/**
	 * @param id - Attack's id.
	 * @param context - The aplication context.
	 * @return The attack in this position.
	 */
	public static Attack load(final int id, final Context context)
	{
		final String[] attacksNames = context.getResources().getStringArray(
		R.array.attacks_names);

		if (attacks == null)
		{
			attacks = new Attack[attacksNames.length];
		}
		if (attacks[id - 1] == null)
		{
			attacks[id - 1] = new Attack(id, context);
		}
		return attacks[id - 1];
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
}