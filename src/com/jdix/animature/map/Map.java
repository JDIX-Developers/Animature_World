package com.jdix.animature.map;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.jdix.animature.exceptions.CompressionException;
import com.jdix.animature.exceptions.SpriteException;
import com.jdix.animature.utils.MathUtils;

/**
 * @author Razican (Iban Eguia)
 */
public class Map {

	private final int				id;
	private HashMap<Square, Link>	links;
	private Square[][]				squares;
	private int						width, height;
	private Bitmap					bitmap;

	/**
	 * @param context - The context of the application
	 * @param map - The map resource to load
	 */
	public Map(final int map, final Context context)
	{
		this.id = map;
		final InputStream s = context.getResources().openRawResource(map);
		try
		{
			load(s);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @param stream Map file's stream
	 * @throws IOException if there is an IO exception when loading the map
	 */
	private void load(final InputStream stream) throws IOException
	{
		final byte[] array = new byte[stream.available()];

		try
		{
			stream.read(array);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}

		calculateDimension(array);

		try
		{
			getLinks(array);
			generateData(array);
		}
		catch (final CompressionException e)
		{
			System.err.println(e.getMessage());
		}
		catch (final SpriteException e)
		{
			System.err.println(e.getMessage());
		}

		generateBitmap();
	}

	private void calculateDimension(final byte[] array)
	{
		this.width = MathUtils.uByteToInt(array[0]);
		this.height = MathUtils.uByteToInt(array[1]);
	}

	private void getLinks(final byte[] array) throws SpriteException,
	CompressionException
	{
		// TODO
	}

	/**
	 * Generates the bidimensional square array
	 * 
	 * @throws CompressionException If there is a compression error
	 * @throws SpriteException If the sprite is undefined
	 */
	private void generateData(final byte[] array) throws CompressionException,
	SpriteException
	{
		// TODO errors on double X, Y compression
		this.squares = new Square[height][width];

		int pointer = 2;

		for (int i = 0; pointer < array.length && i < height; i++)
		{
			for (int h = 0; pointer < array.length && h < width; h++)
			{
				// We test if there has been a Y coordinate repetition
				if (this.squares[i][h] == null && array[pointer] != (byte) 0xFF
				&& array[pointer + 1] != (byte) 0xFF)
				{
					// We write the current square
					this.squares[i][h] = Square.load(array[pointer],
					array[pointer + 1]);
					pointer += 2;
				}
				else if (array[pointer] == (byte) 0xFF)
				{
					// Repetition in Y coordinate
					for (int r = 0; r < MathUtils
					.uByteToInt(array[pointer + 1]); r++)
					{
						this.squares[i + r][h] = this.squares[i - 1][h];
					}
					pointer += 2;
				}
				else if (array[pointer + 1] == (byte) 0xFF)
				{
					// Repetition in X coordinate
					for (int r = 0; r < MathUtils.uByteToInt(array[pointer]); r++)
					{
						this.squares[i][h + r] = this.squares[i][h - 1];
					}

					h += MathUtils.uByteToInt(array[pointer]) - 1;
					pointer += 2;
				}
			}
		}
	}

	private void generateBitmap()
	{
		final short size = Square.getSprite().getSize();
		bitmap = Bitmap.createBitmap(width * size, height * size,
		Bitmap.Config.ARGB_8888);

		final Canvas c = new Canvas(bitmap);

		for (int i = 0; i < height; i++)
		{
			for (int h = 0; h < width; h++)
			{
				c.drawBitmap(squares[i][h].getBitmap(), size * h, size * i,
				null);
			}
		}
	}

	/**
	 * @return Map's height, in squares
	 */
	public int getHeight()
	{
		return this.height;
	}

	/**
	 * @return Map's width, in squares
	 */
	public int getWidth()
	{
		return this.width;
	}

	/**
	 * @return Bitmap of the map
	 */
	public Bitmap getBitmap()
	{
		// TODO calculate which portion of the bitmap to show
		return bitmap;
	}

	/**
	 * @return The id of the map
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @param x - The X coordinate of the square
	 * @param y - The Y coordinate of the square
	 * @return The square in the given position
	 */
	public Square getSquareAt(final byte x, final byte y)
	{
		if (y < squares.length && x < squares[y].length)
		{
			return squares[y][x];
		}
		else
		{
			return null;
		}
	}
}