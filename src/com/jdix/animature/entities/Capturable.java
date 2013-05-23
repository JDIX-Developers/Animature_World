package com.jdix.animature.entities;

import java.util.Random;

import android.content.Context;

/**
 * @author Jordan Aranda Tejada
 */
public class Capturable extends Animature {

	private static final long	serialVersionUID	= - 2197066367542502490L;
	public static final int		NORMAL				= 0;
	public static final int		PARALYZED			= 1;
	public static final int		BURNED				= 2;
	public static final int		POISONED			= 3;
	public static final int		SLEEPED				= 4;
	public static final int		FROZEN				= 5;

	private int					id;
	private int					idAnimature;
	private int					save;
	private String				nickname;
	private int					sex;
	private int					status;
	private int					capturedTime;
	private Attack[]			attacks				= new Attack[4];
	private final int[]			attackN				= new int[4];
	private int[]				attacksPP			= new int[4];
	private int					level;
	private int					currentExp;
	private int					experience;
	private int					healthMax			= 0;
	private int					healthAct;
	private int					box;
	private final int[]			cualitiesC			= new int[5];
	private Context				context;

	public Capturable()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Capturable(final int id, final int idAnimature, final int save,
	final String nickname, final int sex, final int status,
	final int capturedTime, final int a1, final int a1pp, final int a2,
	final int a2pp, final int a3, final int a3pp, final int a4, final int a4pp,
	final int level, final int currentExp, final int experience,
	final int healthAct, final int box)
	{
		super();

		this.id = id;
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
		this.currentExp = currentExp;
		this.experience = experience;
		this.healthAct = healthAct;
		this.box = box;
	}

	public int getIdAnimatureCapt()
	{
		return id;
	}

	public void setIdAnimatureCapt(final int idAnimatureCapt)
	{
		this.id = idAnimatureCapt;
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

	public int getCurrentExp()
	{
		return currentExp;
	}

	public void setCurrentExp(final int currentExp)
	{
		this.currentExp = currentExp;
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

	public boolean levelUp()
	{
		boolean levelUp = false;
		while (this.currentExp >= this.experience)
		{
			this.level += 1;
			this.cualitiesC[SPEED] += (this.cualitiesC[SPEED] / 3);
			this.cualitiesC[DEFENSE] += (this.cualitiesC[DEFENSE] / 3);
			this.cualitiesC[AGILITY] += (this.cualitiesC[AGILITY] / 3);
			this.cualitiesC[STRENGTH] += (this.cualitiesC[STRENGTH] / 3);
			this.cualitiesC[PRECISION] += (this.cualitiesC[PRECISION] / 3);
			this.healthAct += (this.healthAct / 3);
			this.currentExp -= this.experience;
			this.experience = (int) Math.pow(this.level, 3);
			levelUp = true;
		}
		return levelUp;
	}

	public boolean evolution()
	{
		boolean evolution = false;
		if (this.level == this.getLevelEvo())
		{
			this.idAnimature += 1;
			evolution = true;
		}
		return evolution;
	}

	public void healAnimature()
	{
		for (int i = 1; i <= this.getLevel(); i++)
		{
			for (int j = 0; j < 5; j++)
			{
				this.setCualitiesC(
				this.getCualitiesC(j) + (this.getCualitiesC(j) / 3), j);
			}
		}
		this.healthAct = this.healthMax;
		this.status = NORMAL;
	}

	public int giveExp(final int battleType, final Capturable enemy)
	{
		int gExp = 0;
		int baseExp;
		baseExp = enemy.getBaseExp();
		if (battleType == 0)
		{
			gExp = (baseExp * (this.level)) / 7;
		}
		else
		{
			gExp = (int) ((baseExp * (this.level) * 1.5) / 7);
		}
		return gExp;
	}

	public Capturable getCapturedDamage(final Capturable captDo, final int atk)
	{
		final int rand = (new Random()).nextInt(100);
		final Attack attack = captDo.getAttack(atk);

		if (rand <= (attack.getProbability() + (captDo.getCualitiesC(PRECISION) - this
		.getCualitiesC(AGILITY))))
		{
			if ( ! attack.getActive())
			{
				if (this.getCualitiesC(attack.getAttribute()) > 2)
				{
					this.setCualitiesC(
					this.getCualitiesC(attack.getAttribute()) - 2,
					attack.getAttribute());
				}
			}
			else
			{
				/*
				 * this.setHealthAct(this.getHealthAct() - getDamage(captDo,
				 * attack));
				 */
			}
		}
		return this;
	}

	/**
	 * Loads a capturabli by ID
	 * 
	 * @param id - The ID of the capturable
	 * @param context - The context of the application
	 * @return The Capturable loaded
	 */
	public static Capturable load(final int id, final Context context)
	{
		// TODO Auto-generated method stub
		return null;
	}
}