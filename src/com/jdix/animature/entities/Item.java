package com.jdix.animature.entities;

import android.content.Context;

import com.jdix.animature.R;

/**
 * @author Jordan Aranda Tejada
 */
public class Item {

	private int	id;
	private int	type;
	private int	quantity;

	/**
	 * Constructor
	 */
	public Item()
	{

	}

	/**
	 * Constructor
	 * 
	 * @param id Item's id.
	 * @param type Item's type.
	 * @param quantity Item's quantity.
	 */
	public Item(final int id, final int type, final int quantity)
	{
		super();
		this.id = id;
		this.type = type;
		this.quantity = quantity;
	}

	/**
	 * Method to get Item's id.
	 * 
	 * @return Item's id.
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Method to get Item's name.
	 * 
	 * @return Item's name.
	 */
	public String getName(final Context context)
	{
		return context.getResources().getStringArray(R.array.objects_names)[id - 1];
	}

	/**
	 * Method to get Item's type.
	 * 
	 * @return Item's type.
	 */
	public int getType()
	{
		return type;
	}

	/**
	 * Method to get Item's description.
	 * 
	 * @return Item's description.
	 */
	public String getDescription(final Context context)
	{
		return context.getResources().getStringArray(
		R.array.objects_descriptions)[id - 1];
	}

	/**
	 * Method to get Item's quantity.
	 * 
	 * @return Item's quantity.
	 */
	public int getQuantity()
	{
		return quantity;
	}

	/**
	 * Method to set Item's quantity.
	 * 
	 * @param quantity Item's new quantity.
	 */
	public void setQuantity(final int quantity)
	{
		this.quantity = quantity;
	}
}
