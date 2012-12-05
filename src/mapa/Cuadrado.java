package mapa;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import excepciones.CompressionException;
import excepciones.SpriteException;

/**
 * @author Razican (Iban Eguia)
 *
 */
public class Cuadrado extends JComponent {

	private static final long	serialVersionUID	= -2876245044423559884L;
	private static Cuadrado[][] cuadrados;

	/**
	 * Tamaño en pixels (de alto y ancho) del cuadrado
	 */
	public static final byte TAMAÑO = 16;
	private static BufferedImage sprite;

	private byte x;
	private byte y;

	/**
	 * @param x Coordenada X
	 * @param y Coordenada Y
	 * @throws CompressionException Si ocurre un error de compresión
	 */
	private Cuadrado(byte x, byte y) throws CompressionException
	{
		if (x == 0xFF || y == 0xFF)
		{
			throw new CompressionException();
		}

		this.x = x;
		this.y = y;
	}

	/**
	 * @return Devuelve un array con los dos bytes representando al dato
	 */
	public byte[] bytes()
	{
		byte[] a = {this.x, this.y};
		return a;
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);

		//TODO Pintar el cuadrado
	}

	/**
	 * @return La imagen de Sprite
	 */
	public static Image getSprite()
	{
		return sprite;
	}

	/**
	 * @param sprite El nuevo Sprite
	 */
	private static void setSprite(BufferedImage sprite)
	{
		Cuadrado.sprite = sprite;
	}

	/**
	 * @param archivo El nuevo Sprite
	 * @throws IOException Si ocurre un error de IO
	 */
	public static void setSprite(String archivo) throws IOException
	{
		setSprite(ImageIO.read(new File(archivo)));
	}

	/**
	 * @param x Coordenada X
	 * @param y Coordenada Y
	 * @return Cuadrado en esa posición
	 * @throws SpriteException Si el sprite no ha sido inicializado
	 * @throws CompressionException Si ocurre un erro de compresión
	 */
	public static Cuadrado cargar(byte x, byte y) throws SpriteException, CompressionException
	{
		if (sprite == null)
		{
			throw new SpriteException();
		}
		if (cuadrados == null)
		{
			cuadrados = new Cuadrado[sprite.getWidth()/TAMAÑO][sprite.getHeight()/TAMAÑO];
		}
		if (x > sprite.getWidth()/TAMAÑO-1 || y > sprite.getHeight()/TAMAÑO-1)
		{
			throw new SpriteException("No hay una imagen para esas coordenadas");
		}
		if (cuadrados[x][y] == null)
		{
			cuadrados[x][y] = new Cuadrado(x, y);
		}

		return cuadrados[x][y];
	}
}