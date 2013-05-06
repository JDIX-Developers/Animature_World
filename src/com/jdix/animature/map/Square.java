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

	private static Sprite		sprite;
	private static Square[][]	squares;
	private final Bitmap		bitmap;
	private final byte			x, y;

	/**
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 * @throws CompressionException Si ocurre un error de compresión
	 */
	private Square(final byte x, final byte y) throws CompressionException
	{
		if (x == 0xFF || y == 0xFF)
		{
			throw new CompressionException();
		}

		this.x = x;
		this.y = y;

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
	 * @param x Coordenada X
	 * @param y Coordenada Y
	 * @return Cuadrado en esa posición
	 * @throws SpriteException Si el sprite no ha sido inicializado
	 * @throws CompressionException Si ocurre un erro de compresión
	 */
	public static Square load(final byte x, final byte y)
	throws SpriteException, CompressionException
	{
		final int xi = MathUtils.uByteToInt(x), yi = MathUtils.uByteToInt(y);

		if (sprite == null)
		{
			throw new SpriteException();
		}
		if (squares == null)
		{
			squares = new Square[sprite.getWidth() / sprite.getSize()][sprite
			.getHeight() / sprite.getSize()];
		}
		if (xi > sprite.getWidth() / sprite.getSize() - 1
		|| yi > sprite.getHeight() / sprite.getSize() - 1)
		{
			throw new SpriteException("There is no image for coordinates (0x"
			+ MathUtils.toHex(x) + ", 0x" + MathUtils.toHex(y) + ")");
		}
		if (squares[xi][yi] == null)
		{
			squares[xi][yi] = new Square(x, y);
		}

		return squares[xi][yi];
	}
}