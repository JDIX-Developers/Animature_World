package com.jdix.animature.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jdix.animature.utils.Database;

/**
 * @author Razican (Iban Eguia)
 */
public class User {

	private static User		user;
	private final int		id;
	private final String	email;
	private final String	password;
	private final String	username;

	private User(final int id, final String email, final String password,
	final String username)
	{
		this.id = id;
		this.email = email;
		this.password = password;
		this.username = username;
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

		final ContentValues exit = new ContentValues(2);
		exit.put("is_current", 0);
		exit.put("remember", 0);

		db.update("USER", exit, null, null);

		final ContentValues update = new ContentValues(2);
		update.put("is_current", 1);
		if (remember)
		{
			update.put("remember", 1);
		}
		db.update("USER", update, "email = '" + email + "'", null);

		final Cursor c = db
		.rawQuery("SELECT id, password, username FROM USER WHERE email = '"
		+ email + "'", null);

		c.moveToFirst();

		final int id = c.getInt(0);
		final String password = c.getString(1);
		final String username = c.getString(2);

		user = new User(id, email, password, username);

		c.close();
		db.close();
	}

	private static boolean existsUser(final String email, final Context context)
	{
		final SQLiteDatabase db = (new Database(context)).getReadableDatabase();

		final Cursor c = db.rawQuery(
		"SELECT COUNT(*) FROM USER WHERE email = '" + email + "'", null);

		boolean exists = false;
		if (c.moveToFirst())
		{
			exists = (c.getInt(0) == 1);
		}

		c.close();
		db.close();

		return exists;
	}

	/**
	 * @return The current user
	 */
	public static User getCurrent()
	{
		return user;
	}

	/**
	 * Loads the remembered user, if exists, and returns it. It will return null
	 * if none was remembered
	 * 
	 * @param context - The context of the application
	 * @return The remembered user
	 */
	public static User loadRemembered(final Context context)
	{
		final SQLiteDatabase db = (new Database(context)).getWritableDatabase();

		final Cursor c = db.rawQuery(
		"SELECT id, email, password, username FROM USER WHERE remember = 1",
		null);

		if (c.moveToFirst())
		{

			final int id = c.getInt(0);
			final String email = c.getString(1);
			final String password = c.getString(2);
			final String username = c.getString(3);

			user = new User(id, email, password, username);
		}

		c.close();
		db.close();

		return user;
	}
}