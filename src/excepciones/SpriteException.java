package excepciones;

/**
 * Excepción de sprite
 * @author Razican (Iban Eguia)
 *
 */
public class SpriteException extends Exception {

	private static final long	serialVersionUID	= 7597018268282198830L;

	/**
	 * Constructor por defecto. Usa como mensaje:
	 * 'El sprite no está inicializado'
	 */
	public SpriteException()
	{
		super("El sprite no está inicializado");
	}

	/**
	 * @param message Mensaje
	 */
	public SpriteException(String message)
	{
		super(message);
	}
}