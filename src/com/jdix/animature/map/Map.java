package com.jdix.animature.map;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.jdix.animature.exceptions.CompressionException;
import com.jdix.animature.exceptions.SpriteException;
import com.jdix.animature.utils.MathUtils;
import com.jdix.animature.utils.PosEntry;

/**
 * @author Razican (Iban Eguia)
 */
public class Map {

	private final int							id;
	private HashMap<PosEntry<Byte, Byte>, Link>	links;
	private Square[][]							squares;
	private int									width, height;
	private Bitmap								bitmap;

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
			generateData(array);
		}
		catch (final CompressionException e)
		{
			Log.e("MAP", e.getMessage());
		}
		catch (final SpriteException e)
		{
			Log.e("MAP", e.getMessage());
		}

		generateBitmap();
	}

	private void calculateDimension(final byte[] array)
	{
		this.width = MathUtils.uByteToInt(array[0]);
		this.height = MathUtils.uByteToInt(array[1]);
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
		this.squares = new Square[height][width];

		int pointer = 2;

		for (int i = 0; pointer < array.length && i < height; i++)
		{
			for (int h = 0; pointer < array.length && h < width; h++)
			{
				while (h < width && squares[i][h] != null)
				{
					h++;
				}

				if (h == width)
				{
					break;
				}

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
						if (i - 1 < 0)
						{
							throw new CompressionException();
						}
						this.squares[i + r][h] = this.squares[i - 1][h];
					}
					pointer += 2;
				}
				else if (array[pointer + 1] == (byte) 0xFF)
				{
					// Repetition in X coordinate
					for (int r = 0; r < MathUtils.uByteToInt(array[pointer]); r++)
					{
						if (h - 1 < 0)
						{
							throw new CompressionException();
						}
						this.squares[i][h + r] = this.squares[i][h - 1];
					}

					h += MathUtils.uByteToInt(array[pointer]) - 1;
					pointer += 2;
				}
			}
		}

		getLinks(array, pointer);
	}

	private void getLinks(final byte[] array, final int pointer)
	throws SpriteException, CompressionException
	{
		links = new HashMap<PosEntry<Byte, Byte>, Link>();

		int p = pointer;
		if (array.length > pointer + 1 && array[pointer] == array[pointer + 1]
		&& array[pointer] == (byte) 0xFF)
		{
			p += 2;
			while (array.length > p + 7)
			{
				links.put(
				new PosEntry<Byte, Byte>(array[p++], array[p++]),
				new Link(MathUtils.fourByteToInt(array[p++], array[p++],
				array[p++], array[p++]), array[p++], array[p++]));
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
		if (x >= 0 && y >= 0 && y < squares.length && x < squares[y].length)
		{
			return squares[y][x];
		}
		else
		{
			return null;
		}
	}

	/**
	 * @param x - The X coordinate of the square
	 * @param y - The Y coordinate of the square
	 * @return The link in the square
	 */
	public Link getLinkAt(final byte x, final byte y)
	{
		return links.get(new PosEntry<Byte, Byte>(x, y));
	}
}