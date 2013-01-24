package configuración;

import java.io.IOException;
import java.util.Properties;

/**
 * Clase para obtener los valores de cada una de las propiedades de los idiomas.
 * 
 * @author Jordan Aranda Tejada
 * @since 24/01/2013
 */
public class Propiedades extends Properties {

	private static final long	serialVersionUID	= -1250219107751559457L;

	/**
	 * Constructor que recibe el nombre del idioma (ESPANOL o INGLES, de
	 * momento)
	 * 
	 * @param idioma
	 *            El idioma que recibe.
	 */
	public Propiedades(String idioma)
	{
		if (idioma.equals("ESPANOL"))
		{
			getPropiedades("Espanol.es_ES.properties");
		}
		else if (idioma.equals("INGLES"))
		{
			getPropiedades("Ingles.en_EN.properties");
		}
	}

	/**
	 * Metodo para leer las propiedades.
	 * 
	 * @param idioma
	 *            El idioma del que se van a leer las propiedades.
	 */
	private void getPropiedades(String idioma)
	{
		try
		{
			this.load(getClass().getResourceAsStream(idioma));
		}
		catch (IOException e)
		{
			System.out.println("Error: " + e.toString());
		}
	}
}