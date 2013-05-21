package com.jdix.animature.map;

import java.util.Arrays;

import android.graphics.Bitmap;

import com.jdix.animature.exceptions.CompressionException;
import com.jdix.animature.exceptions.SpriteException;
import com.jdix.animature.utils.MathUtils;

/**
 * @author Razican (Iban Eguia)
 */
public class Square {

	public static final byte	NONWALKABLE		= 0;
	public static final byte	WALKABLE		= 1;
	public static final byte	CHANGEABLE		= 2;
	public static final byte	JUMPABLE		= 4;
	public static final byte	TRANSPORTABLE	= 8;
	public static final byte	READABLE		= 16;
	public static final byte	GRASSANIM		= 32;

	private static Sprite		sprite;
	private static Square[][]	squares;
	private final Bitmap		bitmap;
	private final byte			x, y;
	private final Byte			type;

	/**
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 * @throws CompressionException Si ocurre un error de compresión
	 */
	private Square(final byte x, final byte y, final Byte type)
	throws CompressionException
	{
		if (x == 0xFF || y == 0xFF)
		{
			throw new CompressionException();
		}

		this.x = x;
		this.y = y;
		this.type = type;

		this.bitmap = Bitmap.createBitmap(sprite.getBitmap(),
		x * sprite.getSize(), y * sprite.getSize(), sprite.getSize(),
		sprite.getSize());
	}

	/**
	 * @return array with the bytes of the square
	 */
	public byte[] bytes()
	{
		final byte[] a = {this.x, this.y};
		return a;
	}

	@Override
	public String toString()
	{
		return "(" + MathUtils.toHex(x) + ", " + MathUtils.toHex(y) + ")";
	}

	@Override
	public boolean equals(final Object sq)
	{
		return (sq instanceof Square && Arrays.equals(((Square) sq).bytes(),
		bytes()));
	}

	/**
	 * @param s - Sprite
	 */
	public static void setSprite(final Sprite s)
	{
		sprite = s;
	}

	/**
	 * @return The current sprite
	 */
	public static Sprite getSprite()
	{
		return sprite;
	}

	/**
	 * @return Square's bitmap
	 */
	public Bitmap getBitmap()
	{
		return this.bitmap;
	}

	/**
	 * @param type - The type to check
	 * @return if the square is of the given type
	 */
	public boolean isOfType(final byte type)
	{
		return (this.type != null) && (this.type & type) == type;
	}

	/**
	 * @param x - Coordinate X
	 * @param y - Coordinate Y
	 * @param type - The type of the square
	 * @return The square in this position
	 * @throws SpriteException - If the sprite has not been initialized
	 * @throws CompressionException - If there is a compression error
	 */
	public static Square load(final byte x, final byte y, final Byte type)
	throws SpriteException, CompressionException
	{
		final int xi = MathUtils.uByteToInt(x), yi = MathUtils.uByteToInt(y);

		if (sprite == null)
		{
			throw new SpriteException();
		}
		if (squares == null)
		{
			squares = new Square[sprite.getWidth()][sprite.getHeight()];
		}
		if (xi > sprite.getWidth() - 1 || yi > sprite.getHeight() - 1)
		{
			throw new SpriteException("There is no image for coordinates ("
			+ MathUtils.toHex(x) + ", " + MathUtils.toHex(y) + ")");
		}
		if (squares[xi][yi] == null)
		{
			squares[xi][yi] = new Square(x, y, type);
		}

		return squares[xi][yi];
	}
}