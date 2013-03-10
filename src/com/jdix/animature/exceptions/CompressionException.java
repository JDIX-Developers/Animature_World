package com.jdix.animature.exceptions;

/**
 * @author Razican (Iban Eguia)
 * 
 */
public class CompressionException extends Exception {

	private static final long	serialVersionUID	= 1918059690724299917L;

	/**
	 * Default constructor, with message 'Compression error'
	 */
	public CompressionException()
	{
		super("Compression error");
	}

	/**
	 * @param detailMessage Error message
	 */
	public CompressionException(String detailMessage)
	{
		super(detailMessage);
	}
}