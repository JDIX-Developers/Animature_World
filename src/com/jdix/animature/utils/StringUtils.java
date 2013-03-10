package com.jdix.animature.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * String Utilities
 * 
 * @author Razican (Iban Eguia)
 * 
 */
public final class StringUtils {

	private StringUtils()
	{
	}

	/**
	 * @param data Data to convert to hexadecimal
	 * @return String in hexadecimal
	 */
	private static String toHex(byte[] data)
	{
		StringBuffer buf = new StringBuffer();
		for (byte element : data)
		{
			int halfbyte = (element >>> 4) & 0x0F;
			int two_halfs = 0;
			do
			{
				if ((0 <= halfbyte) && (halfbyte <= 9))
				{
					buf.append((char) ('0' + halfbyte));
				}
				else
				{
					buf.append((char) ('a' + (halfbyte - 10)));
				}
				halfbyte = element & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}

	/**
	 * @return empty string coded in sha1
	 */
	public static String sha1()
	{
		return sha1("");
	}

	/**
	 * @param str Text to crypt in sha1
	 * @return Generated sha1 hash
	 */
	public static String sha1(String str)
	{
		byte[] sha1hash = new byte[40];
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes("UTF-8"), 0, str.length());
			sha1hash = md.digest();
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}

		return toHex(sha1hash);
	}
}