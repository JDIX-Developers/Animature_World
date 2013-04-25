package com.jdix.animature.entities;

public class Captured extends Animature {

	public static final int	NORMAL		= 0;
	public static final int	PARALYZED	= 1;
	public static final int	BURNED		= 2;
	public static final int	POISONED	= 3;
	public static final int	SLEEPED		= 4;
	public static final int	FROZEN		= 5;

	private int				idAnimatureCapt;
	private int				nickname;
	private int				capturedTime;
	private Attack[]		attacks		= new Attack[4];
	private int[]			attacksPP	= new int[4];
	private int				level;
	private int				current_exp;
	private int				experience;
	private int				healthMax;
	private int				healthAct;
	private int				status;

	public static final int	NORMALT		= 0;
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

	private final boolean	weak[]		= new boolean[17];
	private final boolean	strong[]	= new boolean[17];

	public Captured()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Captured(final int idAnimatureCapt, final int nickname,
	final int capturedTime, final Attack a1, final Attack a2, final Attack a3,
	final Attack a4, final int a1PP, final int a2PP, final int a3PP,
	final int a4PP, final int level, final int current_exp,
	final int experience, final int healthAct, final int status)
	{
		super();
		this.idAnimatureCapt = idAnimatureCapt;
		this.nickname = nickname;
		this.capturedTime = capturedTime;
		this.attacks[0] = a1;
		this.attacks[1] = a2;
		this.attacks[2] = a3;
		this.attacks[3] = a4;
		this.attacksPP[0] = a1PP;
		this.attacksPP[1] = a2PP;
		this.attacksPP[2] = a3PP;
		this.attacksPP[3] = a4PP;
		this.level = level;
		this.current_exp = current_exp;
		this.experience = experience;
		this.healthMax = (super.getHealth() + (2 * this.level));
		this.healthAct = healthAct;
		this.status = status;

		for (int i = 0; i < weak.length; i++)
		{
			weak[i] = false;
			strong[i] = false;
		}
		switch (super.type)
		{
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
		}

		switch (super.type)
		{
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
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

	public int getNickname()
	{
		return nickname;
	}

	public void setNickname(final int nickname)
	{
		this.nickname = nickname;
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

	public int[] getAttacksPP()
	{
		return attacksPP;
	}

	public void setAttacksPP(final int[] attacksPP)
	{
		this.attacksPP = attacksPP;
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

	public int getStatus()
	{
		return status;
	}

	public void setStatus(final int status)
	{
		this.status = status;
	}

	public boolean levelUp()
	{
		boolean levelUp = false;

		if (this.current_exp >= this.experience)
		{
			this.level += 1;
			this.current_exp -= this.experience;
			this.experience = (int) Math.pow(this.level, 3);
			levelUp = true;
		}
		return levelUp;
	}
}