package com.jdix.animature.map;

import android.graphics.Bitmap;

import com.jdix.animature.exceptions.CompressionException;
import com.jdix.animature.exceptions.SpriteException;
import com.jdix.animature.utils.MathUtils;

/**
 * @author Razican (Iban Eguia)
 * 
 */
public class Square {

	private static Bitmap		sprite;
	private static short		size;
	private static Square[][]	squares;
	private Bitmap				bitmap;
	private byte				x, y;

	/**
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 * @throws CompressionException Si ocurre un error de compresión
	 */
	private Square(byte x, byte y) throws CompressionException
	{
		if (x == 0xFF || y == 0xFF)
		{
			throw new CompressionException();
		}

		this.x = x;
		this.y = y;

		this.bitmap = Bitmap.createBitmap(sprite, x * size, y * size, size,
				size);
	}

	/**
	 * @return array with the bytes of the square
	 */
	public byte[] bytes()
	{
		byte[] a = { this.x, this.y };
		return a;
	}

	@Override
	public String toString()
	{
		return "(0x" + MathUtils.toHex(x) + ", 0x" + MathUtils.toHex(y) + ")";
	}

	@Override
	public boolean equals(Object sq)
	{
		return (sq instanceof Square && ((Square) sq).bytes().equals(bytes()));
	}

	/**
	 * @param b Sprite's bitmap
	 */
	public static void setSprite(Bitmap b)
	{
		sprite = b;
		// TODO queda por comprobar la nueva especificación de sprites
	}

	/**
	 * @param s Size of the squares
	 */
	public static void setSize(short s)
	{
		size = s;
	}

	/**
	 * @return Size of the sprite's squares
	 */
	public static short getSize()
	{
		return size;
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
	public static Square load(byte x, byte y) throws SpriteException,
			CompressionException
	{
		int xi = MathUtils.uByteToInt(x), yi = MathUtils.uByteToInt(y);

		if (sprite == null)
		{
			throw new SpriteException();
		}
		if (squares == null)
		{
			squares = new Square[sprite.getWidth() / size][sprite.getHeight()
					/ size];
		}
		if (xi > sprite.getWidth() / size - 1
				|| yi > sprite.getHeight() / size - 1)
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