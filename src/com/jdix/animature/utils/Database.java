package com.jdix.animature.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import android.content.Context;
import android.database.Cursor;
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
		create_tables();
	}

	@Override
	public void onCreate(final SQLiteDatabase db)
	{
		Log.e("INICIO2", "INICIO2");
		this.update("PRAGMA foreign_keys=ON;");
		this.update("PRAGMA encoding = \"UTF-8\";");
		Log.e("INFORMATION", "NUMERO DE TABLAS: " + numberOfTables());
		if (numberOfTables() == 0)
		{
			create_tables();
		}
	}

	private void create_tables()
	{
		try
		{

			FileInputStream stream = null;
			try
			{
				stream = new FileInputStream(new File("createDB.sql"));
			}
			catch (final FileNotFoundException e)
			{
				Log.e("ERROR", "PROBLEMA AL LEER FICHERO");
			}

			final FileChannel fc = stream.getChannel();
			final MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY,
			0, fc.size());

			final String content = Charset.defaultCharset().decode(bb)
			.toString();

			stream.close();
			this.update(content);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		(new File("createDB.sql")).delete();
	}

	public Cursor consult(final String consult)
	{
		final Cursor c = this.getReadableDatabase().rawQuery(consult, null);
		return c;
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

	public int count(final String table, final String condition)
	{
		int number;
		final String where = condition == null ? "" : " WHERE " + condition;
		final String consult = "SELECT COUNT(*) as number FROM " + table
		+ where + ";";
		final Cursor cursor = consult(consult);
		number = cursor.getCount();
		cursor.close();
		return number;
	}

	private int numberOfTables()
	{
		return count("sqlite_master", "type='table'");
	}

	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
	final int newVersion)
	{
		// TODO Upgrade
	}
}