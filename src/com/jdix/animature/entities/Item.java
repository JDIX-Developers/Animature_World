package com.jdix.animature.entities;

public class Item {

	private int		id;
	private String	name;
	private String	description;
	private int		quantity;

	public Item()
	{

	}

	public Item(final int id, final String name, final String description,
	final int quantity)
	{
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(final String description)
	{
		this.description = description;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(final int quantity)
	{
		this.quantity = quantity;
	}
}
