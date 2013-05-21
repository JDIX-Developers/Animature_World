package com.jdix.animature.entities;

/**
 * @author Razican (Iban Eguia)
 */
public class User {

	private static User	user;
	private int			id;
	private String		username;
	private String		email;
	private String		password;

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
	 * @return The email for the user
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @return The password for the user, a 40 character sha1 encoded string
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param email - The email for the new user
	 * @param password - The password of the new user
	 * @return The user created
	 */
	public static User login(final String email, final String password)
	{
		load(email, password);
		return user;
	}

	/**
	 * @return The current user
	 */
	public static User getCurrent()
	{
		return user;
	}
}