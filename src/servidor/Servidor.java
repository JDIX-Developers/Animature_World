package servidor;


/**
 * @author Razican (Iban Eguia)
 *
 */
public class Servidor {
	
	private static Servidor instance;
	
	private Servidor()//TODO URL u)
	{
		//TODO Crear servidor
	}
	
	/**
	 * @param usuario Usuario con el que conectar
	 * @param contraseña Contraseña a usar al conectarse
	 * @return Resultado de la conexión
	 * 
	 * 0 -> sin fallos
	 */
	public int conectar(String usuario, String contraseña)
	{
		return 0;
	}
	
	/**
	 * @return El servidor del juego.
	 */
	public static Servidor getInstance()
	{
		if (instance == null)
		{
			instance = new Servidor();//TODO null);
		}
		
		return instance;
	}
	
}