package com.jdix.animature.utils;

/**
 * @author Razican (Iban Eguia)
 * 
 */
public class MathUtils {

	/**
	 * @param b Unsigned byte
	 * @return int representing the byte, so for example, 0xFF would be 255, and
	 *         not -1
	 */
	public static int uByteToInt(byte b)
	{
		return b & 0x000000FF;
	}

	/**
	 * @param b byte to convert
	 * @return String with hexadecimal representation of the byte
	 */
	public static String toHex(byte b)
	{
		return String.format("%02X", b);
	}
}