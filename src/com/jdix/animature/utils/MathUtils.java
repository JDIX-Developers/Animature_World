package com.jdix.animature.utils;

/**
 * Math Utilities
 * 
 * @author Razican (Iban Eguia)
 */
public class MathUtils {

	/**
	 * @param b Unsigned byte
	 * @return int representing the byte, so for example, 0xFF would be 255, and
	 *         not -1
	 */
	public static int uByteToInt(final byte b)
	{
		return b & 0x000000FF;
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