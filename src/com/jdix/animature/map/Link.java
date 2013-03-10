package com.jdix.animature.map;

/**
 * @author Razican (Iban Eguia)
 * 
 */
public class Link {

	private int		map;
	private byte	x;
	private byte	y;

	/**
	 * @param map Map's resource ID to where the link points
	 * @param x X coordinate to where the link points
	 * @param y Y coordinate to where the link points
	 */
	public Link(int map, byte x, byte y)
	{
		this.map = map;
		this.x = x;
		this.y = y;
	}

	/**
	 * @return X coordinate of the link
	 */
	public byte getX()
	{
		return x;
	}

	/**
	 * @return Y coordinate of the link
	 */
	public byte getY()
	{
		return y;
	}

	/**
	 * @return Map for the link
	 */
	public int getMap()
	{
		return map;
	}
}