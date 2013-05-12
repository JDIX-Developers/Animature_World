package com.jdix.animature.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

	/**
	 * @param context - Context of the application
	 * @param name - Name of the database
	 * @param factory - Current CursorFactory
	 * @param version - Database version
	 */
	public Database(final Context context, final String name,
	final CursorFactory factory, final int version)
	{
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(final SQLiteDatabase db)
	{
		this.update("PRAGMA foreign_keys=ON;");
		this.update("PRAGMA encoding = \"UTF-8\";");
		create_tables(db);

	}

	private void create_tables(final SQLiteDatabase db)
	{
		try
		{
			FileInputStream stream = null;
			try
			{
				stream = new FileInputStream(new File("createDB.sql"));
				final FileChannel fc = stream.getChannel();
				final MappedByteBuffer bb = fc.map(
				FileChannel.MapMode.READ_ONLY, 0, fc.size());

				final String content = Charset.defaultCharset().decode(bb)
				.toString();

				stream.close();
				update(content);
			}
			catch (final FileNotFoundException e)
			{
				Log.e("ERROR", "PROBLEMA AL LEER FICHERO");
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		(new File("createDB.sql")).delete();
	}

	public int update(final String consult)
	{
		try
		{
			this.getWritableDatabase().execSQL(consult);
			return 1;
		}
		catch (final Exception e)
		{
			return 0;
		}
	}

	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
	final int newVersion)
	{
		// TODO Upgrade
	}
}