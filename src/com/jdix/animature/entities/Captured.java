package com.jdix.animature.entities;

public class Captured extends Animature {

	private int			idAnimatureCapt;
	private int			nickname;
	private int			capturedTime;
	private Attack[]	attacks;
	private int[]		attacksPP;
	private int			experience;
	private int			level;
	private int			healthMax;
	private int			healthAct;
	private int			status;

	public Captured()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Captured(final int idAnimatureCapt, final int nickname,
	final int capturedTime, final Attack[] attacks, final int[] attacksPP,
	final int experience, final int level, final int healthMax,
	final int healthAct, final int status)
	{
		super();
		this.idAnimatureCapt = idAnimatureCapt;
		this.nickname = nickname;
		this.capturedTime = capturedTime;
		this.attacks = attacks;
		this.attacksPP = attacksPP;
		this.experience = experience;
		this.level = level;
		this.healthMax = healthMax;
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

	public int getExperience()
	{
		return experience;
	}

	public void setExperience(final int experience)
	{
		this.experience = experience;
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(final int level)
	{
		this.level = level;
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

}