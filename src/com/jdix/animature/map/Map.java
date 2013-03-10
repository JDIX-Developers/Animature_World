package com.jdix.animature.map;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import com.jdix.animature.exceptions.CompressionException;
import com.jdix.animature.exceptions.SpriteException;

/**
 * @author Razican (Iban Eguia)
 *
 */
public class Map {
	
	private File file;
	private FileInputStream stream;
	private Square[][] squares;
	private byte[] array;
	private int width, height;

	/**
	 * @param args Arguments
	 * @throws IOException if there is an IO error
	 */
	public static void main(String[] args) throws IOException
	{
	//	Square.setSprite("img/sprites/prueba.png");

		byte[] b = {0x05, 0x06,
				0x03, 0x01, 0X03, 0x01, 0x03, 0x01, 0x03, 0x01, 0x02, 0x01,
				0x03, 0x01, 0x03, 0x01, 0x01, 0x01, 0x00, 0x00, 0x00, 0x00,
				0x00, 0x01, 0x03, 0x01, 0x00, 0x01, 0x03, 0x01, 0x00, 0x00,
				0x00, 0x01, 0x03, 0x01, 0x00, 0x01, 0x03, 0x01, 0x00, 0x01,
				0x03, 0x01, 0x03, 0x01, 0x02, 0x01, 0x01, 0x00, 0x00, 0x00,
				0x02, 0x01, 0x02, 0x01, 0x03, 0x00, 0x00, 0x00, 0x00, 0x00
		};

		Map m = new Map(b);

		System.out.println(m.getWidth() + "x" + m.getHeight() + " Cuadrados");
		m.printArray();
		m.compress();
		m.printArray();

//		m.save("mapas/prueba.map");
//
//		Map m2 = new Mapa("mapas/prueba.map");
//		System.out.println(m.getAncho() + "x" + m.getAlto());
//		m2.imprimirArray();
	}

	/**
	 * @param array Map file's byte array
	 */
	public Map(byte[] array)
	{
		this.array = array;
		calculateDimension();

		try
		{
			generateData();
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

	/**
	 * @param file Map file
	 * @throws IOException if there is any IO error
	 */
	public Map(File file) throws IOException
	{
		this.file = file;
		FileInputStream s = new FileInputStream(file);
		load(s);
	}

	/**
	 * @param file Map file
	 * @throws IOException if there is any IO error
	 */
	public Map(String file) throws IOException
	{
		this(new File(file));
	}

	/**
	 * @param stream Map file's stream
	 */
	private void load(FileInputStream stream)
	{
		this.stream = stream;
		this.array = new byte[(int) this.file.length()];

		try
		{
			this.stream.read(this.array);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		calculateDimension();

		try
		{
			generateData();
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

	private void calculateDimension()
	{
		this.width = MathUtils.uByteToInt(this.array[0]);
		this.height = MathUtils.uByteToInt(this.array[1]);
	}

	/**
	 * Generates map's byte array from the data.
	 */
	private void generateArray()
	{
		this.array = new byte[2*width*height+2];

		this.array[0] = (byte) width;
		this.array[1] = (byte) height;

		int index = 2;
		byte[] bytes;

		for (int i = 0; i < height; i++)
		{
			for (int h = 0; h < width; h++)
			{
				bytes = this.squares[i][h].bytes();

				this.array[index] = bytes[0];
				this.array[index+1] = bytes[1];

				index +=2;
			}
		}
	}

	/**
	 * Generates the bidimensional square array
	 * @throws CompressionException If there is a compression error
	 * @throws SpriteException If the sprite is undefined
	 */
	private void generateData() throws CompressionException, SpriteException
	{
		this.squares = new Square[height][width];
		decompress();

		for (int i = 0; i < height; i++)
		{
			for (int h = 0; h < width; h++)
			{
				this.squares[i][h] = Square.load(this.array[2*h+width*2*i+2], this.array[2*h+height*2*i+3]);
			}
		}
	}

	/**
	 * Saves current map
	 */
	public void save()
	{
		compress();
		try
		{
			(new FileOutputStream(this.file)).write(this.array);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Saves current map into specified file
	 * @param f File to save in
	 */
	public void save(String f)
	{
		this.file = new File(f);
		save();
	}

	/**
	 * Saves current map into specified file
	 * @param f File to save in
	 */
	public void save(File f)
	{
		this.file = f;
		save();
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
	 * @return Bidimensional square array
	 */
	public Square[][] getSquares()
	{
		return this.squares;
	}

	/**
	 * Prints the array in console
	 */
	public void printArray()
	{
		System.out.println(Arrays.toString(this.array));
	}

	/**
	 * Compresses current map
	 */
	public void compress()
	{
		byte[][] arr2d = new byte[height][2*width];
		byte[][] arr2dc = new byte[height][2*width];

		//We load the bidimensional array, square by square
		for (int i = 0; i < this.squares.length; i++)
		{
			for (int h = 0, j = 0; h < this.squares[i].length; h++)
			{
				arr2d[i][j++] = this.squares[i][h].bytes()[0];
				arr2d[i][j++] = this.squares[i][h].bytes()[1];
			}
		}

		short borrados = 0;

		//We compress in X coordinate
		for (int i = 0; i < arr2d.length; i++)
		{
			for (int h = 0; h < arr2d[i].length; h += 2)
			{
				arr2dc[i][h] = arr2d[i][h];
				arr2dc[i][h+1] = arr2d[i][h+1];
				int r = 1;

				while ((h+r*2) < arr2d[i].length && arr2d[i][h] == arr2d[i][h+r*2] && arr2d[i][h+1] == arr2d[i][h+r*2+1])
				{
					r++;
				}

				if (r > 2)
				{
					arr2dc[i][h+2] = (byte) (r-1);
					arr2dc[i][h+3] = (byte) 0xFF;
					for (int j = 4; j < r*2; j += 2)
					{
						arr2dc[i][h+j+1] = arr2dc[i][h+j] = (byte) 0xFF;
						borrados++;
					}

					h += r*2-2;
				}
			}
		}

		//We compress in Y
		for (int h = 0; h < arr2d[0].length; h +=2)
		{
			for (int i = 0; i < arr2d.length; i++)
			{
				int r = 1;

				while (i+r < arr2d.length && arr2d[i][h] == arr2d[i+r][h] && arr2d[i][h+1] == arr2d[i+r][h+1])
				{
					r++;
				}

				if (r > 2)
				{
					arr2dc[i+1][h] = (byte) 0xFF;
					arr2dc[i+1][h+1] = (byte) (r-1);
					for (int j = 2; j < r; j++)
					{
						arr2dc[i+j][h+1] = arr2dc[i+j][h] = (byte) 0xFF;
						borrados++;
					}

					i += r-1;
				}
			}
		}

		//We create the compressed array
		byte[] arr = new byte[2+2*height*width-borrados*2];
		arr[0] = (byte) width;
		arr[1] = (byte) height;
		int índice = 2;

		for (int i = 0; i < arr2dc.length; i++)
		{
			for (int h = 0; h < arr2dc[i].length; h += 2)
			{
				//We only save the byte if it's not deleted
				if (arr2dc[i][h] != (byte) 0xFF || arr2dc[i][h+1] != (byte) 0xFF)
				{
					arr[índice++] = arr2dc[i][h];
					arr[índice++] = arr2dc[i][h+1];
				}
			}
		}

		this.array = arr;
	}

	/**
	 * Decompresses current array
	 *
	 * Compression and decompression should be avoided if posible, since they are
	 * really expensive actions.
	 */
	private void decompress()
	{
//		byte[][] arr2d = new byte[getAlto()][getAncho()];
//		this.cuadrados = new Cuadrado[getAlto()][getAncho()];
//
//		//TODO Descomprimir
//
//		this.array = new byte[2+2*getAlto()*getAncho()];
//		this.array[0] = (byte) getAncho();
//		this.array[1] = (byte) getAlto();
//		int índice = 2;
//
//		for (int i = 0; i < arr2d.length; i++)
//		{
//			for (int h = 0; h < arr2d[i].length; h++)
//			{
//				this.array[índice++] = arr2d[i][h];
//			}
//		}
	}
}