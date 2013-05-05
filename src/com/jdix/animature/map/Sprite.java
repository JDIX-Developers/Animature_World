package com.jdix.animature.map;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;

import com.jdix.animature.utils.MathUtils;

/**
 * @author Razican (Iban Eguia)
 */
public class Sprite {

	private final short	size;
	private byte		width;
	private byte		height;
	private Bitmap		bitmap;

	/**
	 * @param context - The context of the application
	 * @param sprite - The .spr file
	 * @param bitmap - The bitmap for the sprite
	 */
	public Sprite(final Context context, final int sprite, final int bitmap)
	{
		final InputStream s = context.getResources().openRawResource(sprite);

		byte[] array = null;

		try
		{
			array = new byte[s.available()];
			s.read(array);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}

		size = MathUtils.twoByteToShort(array[0], array[1]);
	}

	/**
	 * @return The real size of the squares
	 */
	public short getSize()
	{
		return this.size;
	}

	/**
	 * @return the width of the sprite
	 */
	public byte getWidth()
	{
		return width;
	}

	/**
	 * @return the height of the sprite
	 */
	public byte getHeight()
	{
		return height;
	}

	/**
	 * @return The Bitmap of the sprite
	 */
	public Bitmap getBitmap()
	{
		return this.bitmap;
	}
}