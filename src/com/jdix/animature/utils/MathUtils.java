package com.jdix.animature.utils;

/**
 * Math Utilities
 * 
 * @author Razican (Iban Eguia)
 */
public class MathUtils {

	/**
	 * @param b - Unsigned byte
	 * @return int representing the byte, so for example, 0xFF would be 255, and
	 *         not -1
	 */
	public static int uByteToInt(final byte b)
	{
		return b & 0x000000FF;
	}

	/**
	 * @param b - Unsigned byte
	 * @return short representing the byte, so for example, 0xFF would be 255,
	 *         and not -1
	 */
	public static short uByteToShort(final byte b)
	{
		return (short) (b & 0x00FF);
	}

	/**
	 * Gets a short from 2 bytes
	 * 
	 * @param b1 - left byte for the short
	 * @param b2 - right byte for the short
	 * @return Short representing the unsigned number contained by the two
	 *         bytes.
	 */
	public static short twoByteToShort(final byte b1, final byte b2)
	{
		return (short) ((uByteToShort(b1) << 8) + uByteToShort(b2));
	}

	/**
	 * Gets an int from four bytes
	 * 
	 * @param b - The first byte, containing the top value
	 * @param c - The second byte
	 * @param d - The third byte
	 * @param e - the fourth byte
	 * @return The int represented by the four bytes
	 */
	public static int fourByteToInt(final byte b, final byte c, final byte d,
	final byte e)
	{
		return 0; // TODO
	}

	/**
	 * @param b byte to convert
	 * @return String with hexadecimal representation of the byte
	 */
	public static String toHex(final byte b)
	{
		return "0x" + String.format("%02X", b);
	}

}