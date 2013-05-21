package com.jdix.animature.entities;

public class Attack {

	private int		id_Attack;
	private String	name_Attack;
	private int		type_Attack;
	private int		max_pp;
	private boolean	active;
	private int		attribute;
	private int		power;
	private int		probability;	// 0 - 100 (Example: 60% - 60)
	private boolean	isFirst;

	public Attack()
	{

	}

	public Attack(final int id_Attack, final String name_Attack,
	final int type_Attack, final int max_pp, final boolean active,
	final int attribute, final int power, final int probability,
	final boolean isFirst)
	{
		this.id_Attack = id_Attack;
		this.name_Attack = name_Attack;
		this.type_Attack = type_Attack;
		this.max_pp = max_pp;
		this.active = active;
		this.attribute = attribute;
		this.power = power;
		this.probability = probability;
		this.isFirst = isFirst;
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

}