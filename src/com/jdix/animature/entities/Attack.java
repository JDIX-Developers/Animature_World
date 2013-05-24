package com.jdix.animature.entities;

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

	public int getType()
	{
		return type;
	}

	public void setType(final int type)
	{
		this.type = type;
	}

	public int getMaxpp()
	{
		return maxpp;
	}

	public void setMaxpp(final int maxpp)
	{
		this.maxpp = maxpp;
	}

	public boolean getActive()
	{
		return active;
	}

	public void setActive(final boolean active)
	{
		this.active = active;
	}

	public int getAttribute()
	{
		return attribute;
	}

	public void setAttribute(final int attribute)
	{
		this.attribute = attribute;
	}

	public int getPower()
	{
		return power;
	}

	public void setPower(final int power)
	{
		this.power = power;
	}

	public double getProbability()
	{
		return probability;
	}

	public void setProbability(final int probability)
	{
		this.probability = probability;
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
	 * @param animature The defensor Animature's type.
	 * @return Return 0 if normal effective, 1 if very effective, 2 if
	 *         ineffective and 3 if not effective
	 */
	public int getEffectivenes(final Animature animature)
	{
		int effective = NORMAL_EFFECTIVE;
		switch (this.type)
		{
			case Animature.NORMAL:
				switch (animature.getType())
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
		// case Animature.FIRE:
		// if (animature.isOfType(Animature.GRASS)
		// || animature.isOfType(Animature.ICE)
		// || animature.isOfType(Animature.BUG)
		// || animature.isOfType(Animature.STEEL))
		// {
		// effective = VERY_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.FIRE)
		// || animature.isOfType(Animature.WATER)
		// || animature.isOfType(Animature.ROCK)
		// || animature.isOfType(Animature.DRAGON))
		// {
		// effective = FEW_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.ELEMENTAL))
		// {
		// effective = NOT_EFFECTIVE;
		// }
		// break;
		// case Animature.WATER:
		// if (animature.isOfType(Animature.FIRE)
		// || animature.isOfType(Animature.GROUND)
		// || animature.isOfType(Animature.ROCK))
		// {
		// effective = VERY_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.WATER)
		// || animature.isOfType(Animature.GRASS)
		// || animature.isOfType(Animature.DRAGON))
		// {
		// effective = FEW_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.ELEMENTAL))
		// {
		// effective = NOT_EFFECTIVE;
		// }
		// break;
		// case Animature.ELECTRIC:
		// if (animature.isOfType(Animature.WATER)
		// || animature.isOfType(Animature.FLYING))
		// {
		// effective = VERY_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.ELECTRIC)
		// || animature.isOfType(Animature.GRASS)
		// || animature.isOfType(Animature.DRAGON))
		// {
		// effective = FEW_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.GROUND)
		// || animature.isOfType(Animature.ELEMENTAL))
		// {
		// effective = NOT_EFFECTIVE;
		// }
		// break;
		// case Animature.GRASS:
		// if (animature.isOfType(Animature.WATER)
		// || animature.isOfType(Animature.GROUND)
		// || animature.isOfType(Animature.ROCK))
		// {
		// effective = VERY_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.FIRE)
		// || animature.isOfType(Animature.GRASS)
		// || animature.isOfType(Animature.POISON)
		// || animature.isOfType(Animature.FLYING)
		// || animature.isOfType(Animature.BUG)
		// || animature.isOfType(Animature.DRAGON)
		// || animature.isOfType(Animature.STEEL))
		// {
		// effective = FEW_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.ELEMENTAL))
		// {
		// effective = NOT_EFFECTIVE;
		// }
		// break;
		// case Animature.ICE:
		// if (animature.isOfType(Animature.GRASS)
		// || animature.isOfType(Animature.GROUND)
		// || animature.isOfType(Animature.FLYING)
		// || animature.isOfType(Animature.DRAGON))
		// {
		// effective = VERY_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.FIRE)
		// || animature.isOfType(Animature.WATER)
		// || animature.isOfType(Animature.ICE)
		// || animature.isOfType(Animature.STEEL))
		// {
		// effective = FEW_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.ELEMENTAL))
		// {
		// effective = NOT_EFFECTIVE;
		// }
		// break;
		// case Animature.FIGHTING:
		// if (animature.isOfType(Animature.NORMAL)
		// || animature.isOfType(Animature.ICE)
		// || animature.isOfType(Animature.ROCK)
		// || animature.isOfType(Animature.DARK)
		// || animature.isOfType(Animature.STEEL))
		// {
		// effective = VERY_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.POISON)
		// || animature.isOfType(Animature.FLYING)
		// || animature.isOfType(Animature.PSYCHIC)
		// || animature.isOfType(Animature.BUG))
		// {
		// effective = FEW_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.GHOST)
		// || animature.isOfType(Animature.ELEMENTAL))
		// {
		// effective = NOT_EFFECTIVE;
		// }
		// break;
		// case Animature.POISON:
		// if (animature.isOfType(Animature.GRASS))
		// {
		// effective = VERY_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.POISON)
		// || animature.isOfType(Animature.GROUND)
		// || animature.isOfType(Animature.ROCK)
		// || animature.isOfType(Animature.GHOST))
		// {
		// effective = FEW_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.STEEL)
		// || animature.isOfType(Animature.ELEMENTAL))
		// {
		// effective = NOT_EFFECTIVE;
		// }
		// break;
		// case Animature.GROUND:
		// if (animature.isOfType(Animature.FIRE)
		// || animature.isOfType(Animature.ELECTRIC)
		// || animature.isOfType(Animature.POISON)
		// || animature.isOfType(Animature.ROCK)
		// || animature.isOfType(Animature.STEEL))
		// {
		// effective = VERY_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.GRASS)
		// || animature.isOfType(Animature.BUG))
		// {
		// effective = FEW_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.FLYING)
		// || animature.isOfType(Animature.ELEMENTAL))
		// {
		// effective = NOT_EFFECTIVE;
		// }
		// break;
		// case Animature.FLYING:
		// if (animature.isOfType(Animature.GRASS)
		// || animature.isOfType(Animature.FIGHTING)
		// || animature.isOfType(Animature.BUG))
		// {
		// effective = VERY_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.ELECTRIC)
		// || animature.isOfType(Animature.ROCK)
		// || animature.isOfType(Animature.STEEL))
		// {
		// effective = FEW_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.ELEMENTAL))
		// {
		// effective = NOT_EFFECTIVE;
		// }
		// break;
		// case Animature.PSYCHIC:
		// if (animature.isOfType(Animature.FIGHTING)
		// || animature.isOfType(Animature.POISON))
		// {
		// effective = VERY_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.PSYCHIC)
		// || animature.isOfType(Animature.STEEL))
		// {
		// effective = FEW_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.DARK)
		// || animature.isOfType(Animature.ELEMENTAL))
		// {
		// effective = NOT_EFFECTIVE;
		// }
		// break;
		// case Animature.BUG:
		// if (animature.isOfType(Animature.GRASS)
		// || animature.isOfType(Animature.PSYCHIC)
		// || animature.isOfType(Animature.DARK))
		// {
		// effective = VERY_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.FIRE)
		// || animature.isOfType(Animature.FIGHTING)
		// || animature.isOfType(Animature.POISON)
		// || animature.isOfType(Animature.FLYING)
		// || animature.isOfType(Animature.GHOST)
		// || animature.isOfType(Animature.STEEL))
		// {
		// effective = FEW_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.ELEMENTAL))
		// {
		// effective = NOT_EFFECTIVE;
		// }
		// break;
		// case Animature.ROCK:
		// if (animature.isOfType(Animature.FIRE)
		// || animature.isOfType(Animature.ICE)
		// || animature.isOfType(Animature.FLYING)
		// || animature.isOfType(Animature.BUG))
		// {
		// effective = VERY_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.FIGHTING)
		// || animature.isOfType(Animature.GROUND)
		// || animature.isOfType(Animature.STEEL))
		// {
		// effective = FEW_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.ELEMENTAL))
		// {
		// effective = NOT_EFFECTIVE;
		// }
		// break;
		// case Animature.GHOST:
		// if (animature.isOfType(Animature.PSYCHIC)
		// || animature.isOfType(Animature.GHOST))
		// {
		// effective = VERY_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.DARK)
		// || animature.isOfType(Animature.STEEL))
		// {
		// effective = FEW_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.NORMAL)
		// || animature.isOfType(Animature.ELEMENTAL))
		// {
		// effective = NOT_EFFECTIVE;
		// }
		// break;
		// case Animature.DRAGON:
		// if (animature.isOfType(Animature.DRAGON))
		// {
		// effective = VERY_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.STEEL))
		// {
		// effective = FEW_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.ELEMENTAL))
		// {
		// effective = NOT_EFFECTIVE;
		// }
		// break;
		// case Animature.DARK:
		// if (animature.isOfType(Animature.PSYCHIC)
		// || animature.isOfType(Animature.GHOST))
		// {
		// effective = VERY_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.FIGHTING)
		// || animature.isOfType(Animature.DARK)
		// || animature.isOfType(Animature.STEEL))
		// {
		// effective = FEW_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.ELEMENTAL))
		// {
		// effective = NOT_EFFECTIVE;
		// }
		// break;
		// case Animature.STEEL:
		// if (animature.isOfType(Animature.ICE)
		// || animature.isOfType(Animature.ROCK))
		// {
		// effective = VERY_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.FIRE)
		// || animature.isOfType(Animature.WATER)
		// || animature.isOfType(Animature.ELECTRIC)
		// || animature.isOfType(Animature.STEEL))
		// {
		// effective = FEW_EFFECTIVE;
		// }
		// else if (animature.isOfType(Animature.ELEMENTAL))
		// {
		// effective = NOT_EFFECTIVE;
		// }
		// break;
		// case Animature.ELEMENTAL:
		// effective = VERY_EFFECTIVE;
		// break;
		}

		return effective;

	}
}