package com.jdix.animature.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jdix.animature.R;

public class Database extends SQLiteOpenHelper {

	private final Context	context;

	/**
	 * @param context - Context of the application
	 */
	public Database(final Context context)
	{
		super(context, "AnimatureWorldDB", null, 1);
		this.context = context;
	}

	@Override
	public void onCreate(final SQLiteDatabase db)
	{
		db.execSQL("PRAGMA foreign_keys=ON;");
		db.execSQL("PRAGMA encoding = \"UTF-8\";");
		create_tables(db);
	}

	private void create_tables(final SQLiteDatabase db)
	{
		StringBuilder contents = new StringBuilder();
		final String sep = System.getProperty("line.separator");

		try
		{
			final InputStream is = context.getResources().openRawResource(
			R.raw.createdb);

			final BufferedReader input = new BufferedReader(
			new InputStreamReader(is), is.available());

			try
			{
				String line = null;
				while ((line = input.readLine()) != null)
				{
					contents.append(line);
					contents.append(sep);
					if (line.contains(";")
					&& ! line.substring(0, 2).equals("--"))
					{
						db.execSQL(contents.toString());
						contents = new StringBuilder();
					}
				}
			}
			finally
			{
				input.close();
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public SQLiteDatabase getWritableDatabase()
	{
		final SQLiteDatabase db = super.getWritableDatabase();
		db.execSQL("PRAGMA foreign_keys=ON;");
		db.execSQL("PRAGMA encoding = \"UTF-8\";");

		return db;
	}

	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
	final int newVersion)
	{
		// TODO upgrade
	}
}