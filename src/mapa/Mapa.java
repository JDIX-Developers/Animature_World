package mapa;

import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import utilities.MathUtils;
import excepciones.CompressionException;
import excepciones.SpriteException;

/**
 * Clase Mapa
 * @author Razican (Iban Eguia)
 *
 */
public class Mapa extends Container {

	private static final long	serialVersionUID	= -4181659643370964190L;

	private File archivo;
	private FileInputStream stream;
	private Cuadrado[][] cuadrados;
	private byte[] array;
	private Dimension dimensión;

	/**
	 * @param args Argumentos
	 * @throws IOException Si hay un error de IO
	 */
	public static void main(String[] args) throws IOException
	{
		Cuadrado.setSprite("img/sprites/prueba.png");

		byte[] b = {0x05, 0x06,
				0x03, 0x01, 0X03, 0x01, 0x03, 0x01, 0x03, 0x01, 0x02, 0x01,
				0x03, 0x01, 0x03, 0x01, 0x01, 0x01, 0x00, 0x00, 0x00, 0x00,
				0x00, 0x01, 0x03, 0x01, 0x00, 0x01, 0x03, 0x01, 0x00, 0x00,
				0x00, 0x01, 0x03, 0x01, 0x00, 0x01, 0x03, 0x01, 0x00, 0x01,
				0x03, 0x01, 0x03, 0x01, 0x02, 0x01, 0x01, 0x00, 0x00, 0x00,
				0x02, 0x01, 0x02, 0x01, 0x03, 0x00, 0x00, 0x00, 0x00, 0x00
		};

		Mapa m = new Mapa(b);

		System.out.println(m.getAncho() + "x" + m.getAlto());
		m.imprimirArray();
		m.comprimir();
		m.imprimirArray();

//		m.guardar("mapas/prueba.map");
//
//		Mapa m2 = new Mapa("mapas/prueba.map");
//		System.out.println(m.getAncho() + "x" + m.getAlto());
//		m2.imprimirArray();

		//TODO Prueba con compresión
	}

	/**
	 * @param datos Array de datos del mapa
	 */
	public Mapa(Cuadrado[][] datos)
	{
		this.cuadrados = datos;
		generarArray();
	}

	/**
	 * @param array Array de bytes del MapFile
	 */
	public Mapa(byte[] array)
	{
		this.array = array;
		calcularDimensión();

		try
		{
			generarDatos();
		}
		catch (CompressionException | SpriteException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * @param archivo Archivo MapFile
	 * @throws IOException Si ocurre un error de IO
	 */
	public Mapa(File archivo) throws IOException
	{
		this.archivo = archivo;
		try (FileInputStream s = new FileInputStream(archivo))
		{
			cargar(s);
		}
	}

	/**
	 * @param archivo Archivo MapFile
	 * @throws IOException Si ocurre un error de IO
	 */
	public Mapa(String archivo) throws IOException
	{
		this.archivo = new File(archivo);
		try (FileInputStream s = new FileInputStream(archivo))
		{
			cargar(s);
		}
	}

	/**
	 * @param flujo Stream del archivo MapFile
	 */
	private void cargar(FileInputStream flujo)
	{
		this.stream = flujo;
		this.array = new byte[(int) this.archivo.length()];

		try
		{
			this.stream.read(this.array);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		calcularDimensión();

		try
		{
			generarDatos();
		}
		catch (CompressionException | SpriteException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void calcularDimensión()
	{
		this.dimensión = new Dimension(MathUtils.uByteToInt(this.array[0]), MathUtils.uByteToInt(this.array[1]));
	}

	/**
	 * Genera el array de bytes del mapa a partir del array bidimensional de datos.
	 */
	private void generarArray()
	{
		//TODO compresión
		this.array = new byte[2*this.getAncho()*this.getAlto()+2];

		this.array[0] = (byte) this.getAncho();
		this.array[1] = (byte) this.getAlto();

		int índice = 2;
		byte[] bytes;

		for (int i = 0; i < this.getAlto(); i++)
		{
			for (int h = 0; h < this.getAncho(); h++)
			{
				bytes = this.cuadrados[i][h].bytes();

				this.array[índice] = bytes[0];
				this.array[índice+1] = bytes[1];

				índice +=2;
			}
		}
	}

	/**
	 * Genera el array bidimensional de datos para los bytes del mapa
	 * @throws CompressionException Si hay un error de compresión
	 * @throws SpriteException Si el sprite no está definido
	 */
	private void generarDatos() throws CompressionException, SpriteException
	{
		this.cuadrados = new Cuadrado[this.getAlto()][this.getAncho()];
		descomprimir();

		for (int i = 0; i < this.getAlto(); i++)
		{
			for (int h = 0; h < this.getAncho(); h++)
			{
				this.cuadrados[i][h] = Cuadrado.cargar(this.array[2*h+this.getAncho()*2*i+2], this.array[2*h+this.getAncho()*2*i+3]);
			}
		}
	}

	/**
	 * Guarda el mapa actual
	 */
	public void guardar()
	{
		comprimir();
		try
		{
			(new FileOutputStream(this.archivo)).write(this.array);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Guarda el mapa actual en el archivo especificado
	 * @param a Archivo donde guardar
	 */
	public void guardar(String a)
	{
		this.archivo = new File(a);
		guardar();
	}

	/**
	 * Guarda el mapa actual en el archivo especificado
	 * @param a Archivo donde guardar
	 */
	public void guardar(File a)
	{
		this.archivo = a;
		guardar();
	}

	/**
	 * @return Dimensiones del mapa, en cuadrados
	 */
	public Dimension getDimensión()
	{
		return this.dimensión;
	}

	/**
	 * @return Alto del mapa, en cuadrados
	 */
	public int getAlto()
	{
		return (int) this.dimensión.getHeight();
	}

	/**
	 * @return Ancho del mapa, en cuadrados
	 */
	public int getAncho()
	{
		return (int) this.dimensión.getWidth();
	}

	/**
	 * @return Array bidimensional de cuadrados
	 */
	public Cuadrado[][] getCuadrados()
	{
		return this.cuadrados;
	}

	/**
	 * Imprime el array de bytes en la consola
	 */
	public void imprimirArray()
	{
		System.out.println(Arrays.toString(this.array));
	}

	/**
	 * Comprime el mapa actual
	 */
	public void comprimir()
	{
		byte[][] arr2d = new byte[getAlto()][2*getAncho()];
		byte[][] arr2dc = new byte[getAlto()][2*getAncho()];
		int índice = 2;

		//Cargamos el array bidimensional sin comprimir, cuadrado por cuadrado
		for (int i = 0; i < this.cuadrados.length; i++)
		{
			for (int h = 0, j = 0; h < this.cuadrados[i].length; h++)
			{
				arr2d[i][j++] = this.cuadrados[i][h].bytes()[0];
				arr2d[i][j++] = this.cuadrados[i][h].bytes()[1];
			}
		}

		short borrados = 0;

		//Comprimimos primero en coordenada X
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

		//Luego comprimimos en Y
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

		for (int i = 0; i < arr2dc.length; i++)
		{
			System.out.println(Arrays.toString(arr2dc[i]));
		}

		//Creamos el array comprimido
		byte[] arr = new byte[2+2*getAlto()*getAncho()-borrados*2];
		arr[0] = (byte) getAncho();
		arr[1] = (byte) getAlto();
		índice = 2;

		for (int i = 0; i < arr2dc.length; i++)
		{
			for (int h = 0; h < arr2dc[i].length; h += 2)
			{
				//Solo guardamos el byte si no está borrado
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
	 * Descomprime el archivo actual
	 * Puede que no sea necesario, ya que el algoritmo es muy simple
	 */
	private void descomprimir()
	{
		//byte[][] arr2d = new byte[getAlto()][2*getAncho()];
	}
}