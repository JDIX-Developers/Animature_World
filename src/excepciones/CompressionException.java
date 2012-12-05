package excepciones;

/**
 * Excepción de compresión
 * @author Razican (Iban Eguia)
 *
 */
public class CompressionException extends Exception {

	private static final long	serialVersionUID	= -5870341667159267696L;

	/**
	 * Constructor por defecto. Usa como mensaje:
	 * 'Error de compresión'
	 */
	public CompressionException()
	{
		super("Error de compresión");
	}

	/**
	 * @param message Mensaje
	 */
	public CompressionException(String message)
	{
		super(message);
	}
}