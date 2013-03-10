package com.jdix.animature.map;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.content.Context;

import com.jdix.animature.exceptions.CompressionException;
import com.jdix.animature.exceptions.SpriteException;
import com.jdix.animature.utils.MathUtils;

/**
 * @author Razican (Iban Eguia)
 * 
 */
public class Map {

	private static Context			context;

	private HashMap<Square, Link>	links;
	private Square[][]				squares;
	private int						width, height;

	/**
	 * @param map the map resource to load
	 * @throws IOException if there is an IO exception when loading the map
	 */
	public Map(int map) throws IOException
	{
		InputStream s = context.getResources().openRawResource(map);
		load(s);
	}

	/**
	 * @param stream Map file's stream
	 * @throws IOException if there is an IO exception when loading the map
	 */
	private void load(InputStream stream) throws IOException
	{
		byte[] array = new byte[stream.available()];

		try
		{
			stream.read(array);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		calculateDimension(array);

		try
		{
			getLinks(array);
			generateData(array);
		}
		catch (CompressionException e)
		{
			System.err.println(e.getMessage());
		}
		catch (SpriteException e)
		{
			System.err.println(e.getMessage());
		}
	}

	private void calculateDimension(byte[] array)
	{
		this.width = MathUtils.uByteToInt(array[0]);
		this.height = MathUtils.uByteToInt(array[1]);
	}

	private void getLinks(byte[] array) throws SpriteException,
			CompressionException
	{
		for (int i = 2 + width * height * 2; i < array.length; i += 6)
		{
			Square sq = Square.load(array[i], array[i + 1]);

			int map = (MathUtils.uByteToInt(array[i + 2]) << 8)
					& MathUtils.uByteToInt(array[i + 3]);
			int mapId = context.getResources().getIdentifier("map_" + map,
					"id", context.getPackageName());

			Link l = new Link(mapId, array[i + 4], array[i + 5]);

			if (links == null)
			{
				links = new HashMap<Square, Link>();
			}

			links.put(sq, l);
		}
	}

	/**
	 * Generates the bidimensional square array
	 * 
	 * @throws CompressionException If there is a compression error
	 * @throws SpriteException If the sprite is undefined
	 */
	private void generateData(byte[] array) throws CompressionException,
			SpriteException
	{
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
	 * @param c Current context
	 */
	public static void setContext(Context c)
	{
		context = c;
	}
}