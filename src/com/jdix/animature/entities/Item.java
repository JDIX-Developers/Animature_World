package com.jdix.animature.entities;

import android.content.Context;

import com.jdix.animature.R;

/**
 * @author Jordan Aranda Tejada
 */
public class Item {

	private final int	id;
	private int			quantity;

	/**
	 * Constructor
	 * 
	 * @param id Item's id.
	 * @param quantity Item's quantity.
	 */
	public Item(final int id, final int quantity)
	{
		super();
		this.id = id;
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
	 * @param context - The context of the application
	 * @return Item's name.
	 */
	public String getName(final Context context)
	{
		return getName(id, context);
	}

	/**
	 * @param context - The context of the application
	 * @return The description of the item
	 */
	public String getDescription(final Context context)
	{
		return getDescription(id, context);
	}

	/**
	 * Method to get Item's type.
	 * 
	 * @param context - The context of the application
	 * @return Item's type.
	 */
	public String getType(final Context context)
	{
		return getType(id, context);
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

	/**
	 * Gets the name of the item
	 * 
	 * @param id - The id of the item
	 * @param context - The context of the application
	 * @return The name of the application
	 */
	public static String getName(final int id, final Context context)
	{
		return context.getResources().getStringArray(R.array.object_names)[id - 1];
	}

	/**
	 * Method to get Item's description.
	 * 
	 * @param id - The ID of the item
	 * @param context - The context of the application
	 * @return Item's description.
	 */
	public static String getDescription(final int id, final Context context)
	{
		return context.getResources().getStringArray(
		R.array.object_descriptions)[id - 1];
	}

	/**
	 * Gets the type of the given item
	 * 
	 * @param id - The ID of the item
	 * @param context - The context of the application
	 * @return The type of the given item
	 */
	public static String getType(final int id, final Context context)
	{
		return context.getResources().getStringArray(R.array.object_types)[id - 1];
	}
}
