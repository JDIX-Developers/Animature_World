package com.jdix.animature.map;

/**
 * @author Razican (Iban Eguia)
 */
public class Link {

	private final int	map;
	private final byte	x;
	private final byte	y;

	/**
	 * @param map Map's resource ID to where the link points
	 * @param x X coordinate to where the link points
	 * @param y Y coordinate to where the link points
	 */
	public Link(final int map, final byte x, final byte y)
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