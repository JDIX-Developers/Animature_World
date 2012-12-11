package mapa;

import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

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

		//Prueba sin compresión
		byte[] b = {0x04, 0x05,
				0x03, 0x01, 0X00, 0x01, 0x01, 0x00, 0x02, 0x01,
				0x00, 0x01, 0x02, 0x01, 0x01, 0x01, 0x00, 0x00,
				0x00, 0x00, 0x00, 0x01, 0x00, 0x01, 0x03, 0x01,
				0x02, 0x01, 0x00, 0x00, 0x02, 0x01, 0x01, 0x00,
				0x02, 0x01, 0x02, 0x01, 0x03, 0x00, 0x00, 0x00
		};

		Mapa m = new Mapa(b);

		System.out.println(m.getAncho() + "x" + m.getAlto());

		m.guardar("mapas/prueba.map");

		Mapa m2 = new Mapa("mapas/prueba.map");
		System.out.println(m.getAncho() + "x" + m.getAlto());
		m2.imprimirArray();

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
		this.dimensión = new Dimension(this.array[0], this.array[1]);
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
	 * @throws SpriteException Si no está inicializado el sprite
	 */
	private void generarDatos() throws CompressionException, SpriteException
	{
		this.cuadrados = new Cuadrado[this.getAlto()][this.getAncho()];

		//OJO Cuidado con compresión
		for (int i = 0; i < this.getAlto(); i++)
		{
			for (int h = 0; h < this.getAncho(); h++)
			{
				this.cuadrados[i][h] = Cuadrado.cargar(this.array[2*h*i+2], this.array[2*h*i+3]);
			}
		}
	}

	/**
	 * Guarda el mapa actual
	 */
	public void guardar()
	{
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
		byte[] arr1 = new byte[2+2*getAlto()*getAncho()];
		arr1[0] = (byte) getAncho();
		arr1[1] = (byte) getAlto();
		int índice = 2;

		//Cargamos el array bidimensional sin comprimir, cuadrado por cuadrado
		for (int i = 0; i < this.cuadrados.length; i++)
		{
			for (int h = 0, j = 0; h < this.cuadrados[i].length; h++)
			{
				arr1[índice++] = arr2d[i][j++] = this.cuadrados[i][h].bytes()[0];
				arr1[índice++] = arr2d[i][j++] = this.cuadrados[i][h].bytes()[1];
			}
		}

		short borrados = 0;
		//Primero comprimimos en X TODO

		//Luego comprimimos en Y TODO

		//Creamos el array comprimido
		byte[] arr2 = new byte[arr1.length-borrados];
		arr2[0] = arr1[0];
		arr2[1] = arr1[1];
		índice = 2;

		for (int i = 2; i < arr1.length; i += 2)
		{
			//Solo guardamos el byte si no está borrado
			if (arr1[i] != 0xFF && arr1[i+1] != 0xFF)
			{
				arr2[índice++] = arr1[i];
				arr2[índice++] = arr1[i+1];
			}
		}

		this.array = arr2;
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