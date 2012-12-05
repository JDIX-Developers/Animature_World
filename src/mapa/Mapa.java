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

		this.array[1] = (byte) (this.getAncho() & 0x00FF);
		this.array[0] = (byte) ((this.getAncho() & 0xFF00) >> 8);
		this.array[3] = (byte) (this.getAlto() & 0x00FF);
		this.array[2] = (byte) ((this.getAlto() & 0xFF00) >> 8);

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

		//TODO Cuidado con compresión
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
	 * Imprime el array de bytes en la consola
	 */
	public void imprimirArray()
	{
		System.out.println(Arrays.toString(this.array));
	}

	/**
	 * @param args Argumentos
	 * @throws IOException Si hay un error de IO
	 */
	public static void main(String[] args) throws IOException
	{
		//Prueba sin compresión
		byte[] b = {0x04, 0x05,
				0x05, 0x0F, 0X00, 0x02, 0x01, 0x08, 0x01, 0x03,
				0x0B, 0x0B, 0x03, 0x0C, 0x0D, 0x05, 0x07, 0x0A,
				0x03, 0x08, 0x08, 0x03, 0x05, 0x0E, 0x04, 0x03,
				0x0F, 0x01, 0x00, 0x00, 0x00, 0x03, 0x0F, 0x06,
				0x06, 0x09, 0x08, 0x02, 0x0A, 0x0C, 0x04, 0x06
		};

		Mapa m = new Mapa(b);

		System.out.println(m.getAncho() + "x" + m.getAlto());

		m.guardar("mapa.map");

		Mapa m2 = new Mapa("mapa.map");
		System.out.println(m.getAncho() + "x" + m.getAlto());
		m2.imprimirArray();

		//TODO Prueba con compresión
	}
}