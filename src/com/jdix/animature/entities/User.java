package com.jdix.animature.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jdix.animature.utils.Database;

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
	 * @param email - The email of the user
	 * @param password - The password of the user
	 * @param username - The username
	 * @param context - The context of the application
	 * @return The user created
	 */
	public static User login(final String email, final String password,
	final String username, final Context context)
	{
		return login(email, password, username, false, context);
	}

	/**
	 * @param email - The email for the new user
	 * @param password - The password of the new user
	 * @param username - The username
	 * @param remember - Wether to remember the user
	 * @param context - The context of the application
	 * @return The user created
	 */
	public static User login(final String email, final String password,
	final String username, final boolean remember, final Context context)
	{
		if ( ! existsUser(email, context))
		{
			final SQLiteDatabase db = (new Database(context))
			.getWritableDatabase();

			final ContentValues values = new ContentValues(5);
			values.put("email", email);
			values.put("password", password);
			values.put("username", username);
			values.put("is_current", true);
			values.put("remember", remember);

			db.insert("USER", null, values);
		}

		load(email, remember, context);

		return user;
	}

	private static void load(final String email, final boolean remember,
	final Context context)
	{
		final SQLiteDatabase db = (new Database(context)).getWritableDatabase();
		// TODO Auto-generated method stub
	}

	private static boolean existsUser(final String email, final Context context)
	{
		final SQLiteDatabase db = (new Database(context)).getReadableDatabase();
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return The last user used if remembered
	 */
	public static User remember()
	{
		return null;
	}

	/**
	 * @return The current user
	 */
	public static User getCurrent()
	{
		return user;
	}
}