package com.jdix.animature.entities;

import java.util.Random;

public class Attack {

	private int				id_Attack;
	private String			name_Attack;
	private int				type_Attack;
	private int				max_pp;
	private int				active;			// 0 if it is a passive attack,
												// 1 if it is an active attack
	private int				ifPass;			// If active==0, has the number
												// of the cuality to change
	private int				power;
	private int				probability;		// 0 - 100 (Example: 60% - 60)

	public static final int	SPEED		= 0;
	public static final int	DEFENSE		= 1;
	public static final int	AGILITY		= 2;
	public static final int	STRENGHT	= 3;
	public static final int	PRECISSION	= 4;

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

	public Attack()
	{

	}

	public Attack(final int id_Attack, final String name_Attack,
	final int type_Attack, final int max_pp, final int active,
	final int ifPass, final int power, final int probability)
	{
		this.id_Attack = id_Attack;
		this.name_Attack = name_Attack;
		this.type_Attack = type_Attack;
		this.max_pp = max_pp;
		this.active = active;
		this.ifPass = ifPass;
		this.power = power;
		this.probability = probability;
	}

	public int getId_Attack()
	{
		return id_Attack;
	}

	public void setId_Attack(final int id_Attack)
	{
		this.id_Attack = id_Attack;
	}

	public String getName_Attack()
	{
		return name_Attack;
	}

	public void setName_Attack(final String name_Attack)
	{
		this.name_Attack = name_Attack;
	}

	public int getType_Attack()
	{
		return type_Attack;
	}

	public void setType_Attack(final int type_Attack)
	{
		this.type_Attack = type_Attack;
	}

	public int getMax_pp()
	{
		return max_pp;
	}

	public void setMax_pp(final int max_pp)
	{
		this.max_pp = max_pp;
	}

	public int getActive()
	{
		return active;
	}

	public void setActive(final int active)
	{
		this.active = active;
	}

	public int getIfPass()
	{
		return ifPass;
	}

	public void setIfPass(final int ifPass)
	{
		this.ifPass = ifPass;
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

	public Captured getCapturedDamage(final Captured captRec,
	final Captured captDo)
	{
		final int rand = (new Random()).nextInt(100);

		if (rand <= (this.probability + (captDo.getCualitiesC(PRECISSION) - captRec
		.getCualitiesC(AGILITY))))
		{
			if (this.active == 0)
			{
				if (captRec.getCualitiesC(this.ifPass) > 2)
				{
					captRec.setCualitiesC(
					captRec.getCualitiesC(this.ifPass) - 2, this.ifPass);
				}
			}
			else
			{
				captRec.setHealthAct(captRec.getHealthAct()
				- getDamage(captRec, captDo));
			}
		}

		return captRec;
	}

	public int getDamage(final Captured cR, final Captured cD)
	{
		int damage = 0;

		if (cR.isWeak(this.type_Attack))
		{
			damage = (this.power / 100)
			* (cD.getCualitiesC(STRENGHT) / cR.getCualitiesC(DEFENSE))
			* cD.getCualitiesC(STRENGHT) * 2;
		}
		else if (cR.isStrong(this.type_Attack))
		{
			damage = (this.power / 100)
			* (cD.getCualitiesC(STRENGHT) / cR.getCualitiesC(DEFENSE))
			* cD.getCualitiesC(STRENGHT) / 2;
		}
		else
		{
			damage = (this.power / 100)
			* (cD.getCualitiesC(STRENGHT) / cR.getCualitiesC(DEFENSE))
			* cD.getCualitiesC(STRENGHT);
		}

		return damage;
	}
}