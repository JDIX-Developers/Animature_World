package com.jdix.animature.entities;

import java.util.Random;

import android.content.Context;

public class Capturable extends Animature {

	public static final int	NORMAL		= 0;
	public static final int	PARALYZED	= 1;
	public static final int	BURNED		= 2;
	public static final int	POISONED	= 3;
	public static final int	SLEEPED		= 4;
	public static final int	FROZEN		= 5;

	private int				idAnimatureCapt;
	private int				idAnimature;
	private int				save;
	private String			nickname;
	private int				sex;
	private int				status;
	private int				capturedTime;
	private Attack[]		attacks		= new Attack[4];
	private final int[]		attackN		= new int[4];
	private int[]			attacksPP	= new int[4];
	private int				level;
	private int				current_exp;
	private int				experience;
	private int				healthMax	= 0;
	private int				healthAct;
	private int				box;
	private final int[]		cualitiesC	= new int[5];
	private Context			context;

	private final boolean	isWeak[]	= new boolean[17];
	private final boolean	isStrong[]	= new boolean[17];

	public Capturable()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Capturable(final int idAnimatureCapt, final int idAnimature,
	final int save, final String nickname, final int sex, final int status,
	final int capturedTime, final int a1, final int a1pp, final int a2,
	final int a2pp, final int a3, final int a3pp, final int a4, final int a4pp,
	final int level, final int current_exp, final int experience,
	final int healthAct, final int box)
	{
		super();

		this.idAnimatureCapt = idAnimatureCapt;
		this.idAnimature = idAnimature;
		this.save = save;
		this.nickname = nickname;
		this.sex = sex;
		this.status = status;
		this.capturedTime = capturedTime;
		this.attackN[0] = a1;
		this.attackN[1] = a2;
		this.attackN[2] = a3;
		this.attackN[3] = a4;
		this.attacksPP[0] = a1pp;
		this.attacksPP[1] = a2pp;
		this.attacksPP[2] = a3pp;
		this.attacksPP[3] = a4pp;
		this.level = level;
		this.current_exp = current_exp;
		this.experience = experience;
		this.healthAct = healthAct;
		this.box = box;

		for (int i = 0; i < isWeak.length; i++)
		{
			isWeak[i] = false;
			isStrong[i] = false;
		}
	}

	public int getIdAnimatureCapt()
	{
		return idAnimatureCapt;
	}

	public void setIdAnimatureCapt(final int idAnimatureCapt)
	{
		this.idAnimatureCapt = idAnimatureCapt;
	}

	public int getIdAnimature()
	{
		return idAnimature;
	}

	public void setIdAnimature(final int idAnimature)
	{
		this.idAnimature = idAnimature;
	}

	public int getSave()
	{
		return save;
	}

	public void setSave(final int save)
	{
		this.save = save;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(final String nickname)
	{
		this.nickname = nickname;
	}

	public int getSex()
	{
		return sex;
	}

	public void setSex(final int sex)
	{
		this.sex = sex;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(final int status)
	{
		this.status = status;
	}

	public int getCapturedTime()
	{
		return capturedTime;
	}

	public void setCapturedTime(final int capturedTime)
	{
		this.capturedTime = capturedTime;
	}

	public Attack[] getAttacks()
	{
		return attacks;
	}

	public void setAttacks(final Attack[] attacks)
	{
		this.attacks = attacks;
	}

	public int getAttackN(final int pos)
	{
		return this.attackN[pos];
	}

	public Attack getAttack(final int pos)
	{
		return attacks[pos];
	}

	public void setAttack(final int pos, final Attack attack)
	{
		this.attacks[pos] = attack;
	}

	public int[] getAttacksPP()
	{
		return attacksPP;
	}

	public void setAttacksPP(final int[] attacksPP)
	{
		this.attacksPP = attacksPP;
	}

	public int getAttackPP(final int pos)
	{
		return attacksPP[pos];
	}

	public void reduceAttackPP(final int pos)
	{
		this.attacksPP[pos] -= 1;
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(final int level)
	{
		this.level = level;
	}

	public int getCurrent_exp()
	{
		return current_exp;
	}

	public void setCurrent_exp(final int current_exp)
	{
		this.current_exp = current_exp;
	}

	public int getExperience()
	{
		return experience;
	}

	public void setExperience(final int experience)
	{
		this.experience = experience;
	}

	public int getHealthMax()
	{
		return healthMax;
	}

	public void setHealthMax(final int healthMax)
	{
		this.healthMax = healthMax;
	}

	public int getHealthAct()
	{
		return healthAct;
	}

	public void setHealthAct(final int healthAct)
	{
		this.healthAct = healthAct;
	}

	public int getCualitiesC(final int pos)
	{
		return this.cualitiesC[pos];
	}

	public void setCualitiesC(final int quantity, final int pos)
	{
		this.cualitiesC[pos] = quantity;
	}

	public boolean isWeak(final int type)
	{
		return this.isWeak[type];
	}

	public boolean isStrong(final int type)
	{
		return this.isStrong[type];
	}

	/*
	 * public boolean levelUp() { boolean levelUp = false; ands.open(); while
	 * (this.current_exp >= this.experience) { this.level += 1;
	 * this.cualitiesC[SPEED] += (this.cualities[SPEED] / 3);
	 * this.cualitiesC[DEFENSE] += (this.cualities[DEFENSE] / 3);
	 * this.cualitiesC[AGILITY] += (this.cualities[AGILITY] / 3);
	 * this.cualitiesC[STRENGHT] += (this.cualities[STRENGHT] / 3);
	 * this.cualitiesC[PRECISSION] += (this.cualities[PRECISSION] / 3);
	 * this.healthAct += (this.healthAct / 3); this.current_exp -=
	 * this.experience; this.experience = (int) Math.pow(this.level, 3); levelUp
	 * = true; } ands.close(); return levelUp; }
	 */

	/*
	 * public boolean evolution() { boolean evolution = false; if (this.level ==
	 * ands.readAnimatureColInt(this.idAnimature, 12)) { evolution = true;
	 * this.id_Animature += 1; } return evolution; }
	 */

	public boolean heal()
	{
		boolean healed = false;

		if ((this.healthAct < this.healthMax) || (this.status != NORMAL))
		{
			this.healthAct = this.healthMax;
			this.status = NORMAL;
			healed = true;
		}
		return healed;
	}

	/*
	 * public int giveExp(final int battleType, final int idC) { int gExp = 0;
	 * int baseExp; ands.open(); baseExp = ands.readAnimatureColInt(idC, 13); if
	 * (battleType == 0) { gExp = (baseExp * (this.level)) / 7; } else { gExp =
	 * (int) ((baseExp * (this.level) * 1.5) / 7); } ands.close(); return gExp;
	 * }
	 */

	public Capturable getCapturedDamage(final Capturable captDo, final int atk)
	{
		final int rand = (new Random()).nextInt(100);
		final Attack attack = captDo.getAttack(atk);

		if (rand <= (attack.getProbability() + (captDo
		.getCualitiesC(PRECISSION) - this.getCualitiesC(AGILITY))))
		{
			if (attack.getActive() == 0)
			{
				if (this.getCualitiesC(attack.getIfPass()) > 2)
				{
					this.setCualitiesC(
					this.getCualitiesC(attack.getIfPass()) - 2,
					attack.getIfPass());
				}
			}
			else
			{
				this.setHealthAct(this.getHealthAct()
				- getDamage(captDo, attack));
			}
		}
		return this;
	}

	public int getDamage(final Capturable cD, final Attack attack)
	{
		int damage = 0;

		if (this.isWeak(attack.getType_Attack()))
		{
			damage = (attack.getPower() / 100)
			* (cD.getCualitiesC(STRENGHT) / this.getCualitiesC(DEFENSE))
			* cD.getCualitiesC(STRENGHT) * 2;
		}
		else if (this.isStrong(attack.getType_Attack()))
		{
			damage = (attack.getPower() / 100)
			* (cD.getCualitiesC(STRENGHT) / this.getCualitiesC(DEFENSE))
			* cD.getCualitiesC(STRENGHT) / 2;
		}
		else
		{
			damage = (attack.getPower() / 100)
			* (cD.getCualitiesC(STRENGHT) / this.getCualitiesC(DEFENSE))
			* cD.getCualitiesC(STRENGHT);
		}

		return damage;
	}
}