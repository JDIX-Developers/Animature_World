package com.jdix.animature.entities;

/**
 * @author Razican (Iban Eguia)
 */
public class User {

	private static User	user;
	private int			id;
	private String		username;

	private User()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return The ID of the user
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @return The username of the user
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * Method to load user data.
	 */
	public static void loadUser()
	{
		// TODO
	}

	/**
	 * @return The current user
	 */
	public static User getCurrent()
	{
		return user;
	}
}