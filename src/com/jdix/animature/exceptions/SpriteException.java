package com.jdix.animature.exceptions;

/**
 * @author Razican (Iban Eguia)
 */
public class SpriteException extends Exception {

	private static final long	serialVersionUID	= - 4637376104740654146L;

	/**
	 * Default constructor, with message 'Sprite has not been initialized'
	 */
	public SpriteException()
	{
		super("Sprite has not been initialized");
	}

	/**
	 * @param detailMessage Error message
	 */
	public SpriteException(final String detailMessage)
	{
		super(detailMessage);
	}
}