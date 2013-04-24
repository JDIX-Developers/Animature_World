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

	public Captured()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Captured(final int idAnimatureCapt, final int nickname,
	final int capturedTime, final Attack[] attacks, final int[] attacksPP,
	final int level, final int current_exp, final int experience,
	final int healthAct, final int status)
	{
		super();
		this.idAnimatureCapt = idAnimatureCapt;
		this.nickname = nickname;
		this.capturedTime = capturedTime;
		this.attacks = attacks;
		this.attacksPP = attacksPP;
		this.level = level;
		this.current_exp = current_exp;
		this.experience = experience;
		this.healthMax = (int) (super.getHealth() * (1.1 * this.level));
		this.healthAct = healthAct;
		this.status = status;
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