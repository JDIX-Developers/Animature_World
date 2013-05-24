package com.jdix.animature.entities;

import java.io.Serializable;
import java.util.Random;

import android.content.Context;
import android.content.res.Resources;

import com.jdix.animature.R;

/**
 * @author Jordan Aranda Tejada
 */
public class Animature implements Serializable {

	private static final long	serialVersionUID	= - 2197066367542502490L;

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

	public static final int		OK					= 0;
	public static final int		PARALYZED			= 1;
	public static final int		BURNED				= 2;
	public static final int		POISONED			= 3;
	public static final int		SLEEPED				= 4;
	public static final int		FROZEN				= 5;

	private int					id;
	private int					animature;
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

	public Animature(final int id, final int animature, final int save,
	final String nickname, final int sex, final int status,
	final int capturedTime, final int a1, final int a1pp, final int a2,
	final int a2pp, final int a3, final int a3pp, final int a4, final int a4pp,
	final int level, final int currentExp, final int experience,
	final int healthAct, final int box)
	{
		this.id = id;
		this.animature = animature;
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

	/**
	 * @param id - The ID of the animature
	 * @param context - The context of the application
	 */
	public Animature(final int id, final Context context)
	{
		// TODO Auto-generated constructor stub
	}

	public int getId()
	{
		return id;
	}

	public void setId(final int id)
	{
		this.id = id;
	}

	/**
	 * @return The type of the animature
	 */
	public int getType()
	{
		return getType(animature);
	}

	/**
	 * Gets the type of the animature
	 * 
	 * @param animature - The animature to check
	 * @return The type of the animature
	 */
	public static int getType(final int animature)
	{
		return Integer.parseInt(Resources.getSystem().getStringArray(
		R.array.animature_type)[animature - 1]);
	}

	public int getAnimature()
	{
		return animature;
	}

	public void setAnimature(final int animature)
	{
		this.animature = animature;
	}

	public int getSave()
	{
		return save;
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

	public Animature getCapturedDamage(final Animature captDo, final int atk)
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
	 * Loads a capturable by ID
	 * 
	 * @param id - The ID of the capturable
	 * @param context - The context of the application
	 * @return The Capturable loaded
	 */
	public static Animature load(final int id, final Context context)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param type - The type to check
	 * @param animature - The animature to check
	 * @return If the animature is of the given type
	 */
	public static boolean isOfType(final int type, final int animature)
	{
		return getType(animature) == type;
	}

	/**
	 * @param animature - The animature to check
	 * @return - The name of the animature
	 */
	public static String getName(final int animature)
	{
		return Resources.getSystem().getStringArray(R.array.animature_names)[animature - 1];
	}

	/**
	 * @return The qualities of the animature
	 */
	public int[] getQualities()
	{
		return getQualities(animature);
	}

	/**
	 * @param animature - The animature to check
	 * @return The qualities of the animature
	 */
	public static int[] getQualities(final int animature)
	{
		final int[] qualities = new int[5];

		qualities[SPEED] = Integer.parseInt(Resources.getSystem()
		.getStringArray(R.array.animature_speed)[animature - 1]);
		qualities[DEFENSE] = Integer.parseInt(Resources.getSystem()
		.getStringArray(R.array.animature_defense)[animature - 1]);
		qualities[AGILITY] = Integer.parseInt(Resources.getSystem()
		.getStringArray(R.array.animature_agility)[animature - 1]);
		qualities[STRENGTH] = Integer.parseInt(Resources.getSystem()
		.getStringArray(R.array.animature_strength)[animature - 1]);
		qualities[PRECISION] = Integer.parseInt(Resources.getSystem()
		.getStringArray(R.array.animature_precision)[animature - 1]);

		return qualities;
	}

	/**
	 * Gets the evolution level for the animature
	 * 
	 * @return The Animature's evolution level.
	 */
	public int getLevelEvo()
	{
		return getLevelEvo(animature);
	}

	/**
	 * Gets the evolution level for the given animature
	 * 
	 * @param animature - The animature to check
	 * @return The evolution level for the animature
	 */
	public static int getLevelEvo(final int animature)
	{
		return Integer.parseInt(Resources.getSystem().getStringArray(
		R.array.animature_level_evo)[animature - 1]);
	}

	/**
	 * @return The base experience for the animature
	 */
	public int getBaseExp()
	{
		return getBaseExp(animature);
	}

	/**
	 * Gets the base experience for the given animature
	 * 
	 * @param animature - The animature to check
	 * @return The base experience
	 */
	public static int getBaseExp(final int animature)
	{
		return Integer.parseInt(Resources.getSystem().getStringArray(
		R.array.animature_base_exp)[animature - 1]);
	}
}