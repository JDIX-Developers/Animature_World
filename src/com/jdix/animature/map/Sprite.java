package com.jdix.animature.map;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.jdix.animature.utils.MathUtils;
import com.jdix.animature.utils.PosEntry;

/**
 * @author Razican (Iban Eguia)
 */
public class Sprite {

	private final short							size;
	private final byte							width;
	private final byte							height;
	private final Bitmap						bitmap;
	private HashMap<Entry<Byte, Byte>, Byte>	types;

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
		this.bitmap = BitmapFactory.decodeResource(context.getResources(),
		bitmap);

		width = (byte) (this.bitmap.getWidth() / size);
		height = (byte) (this.bitmap.getHeight() / size);

		// TODO set types
		for (int i = 2; i < array.length; i += 3)
		{
			types.put(new PosEntry<Byte, Byte>(array[i], array[i + 1]),
			array[i + 2]);
		}
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

	/**
	 * @param x - The X coordinate of the square
	 * @param y - The Y coordinate of the square
	 * @return the type of the square
	 */
	public byte getType(final byte x, final byte y)
	{
		return types.get(new PosEntry<Byte, Byte>(x, y));
	}
}