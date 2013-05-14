package com.jdix.animature;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.jdix.animature.entities.Animature;
import com.jdix.animature.utils.AdapterAnimatures;
import com.jdix.animature.utils.Database;

/**
 * @author Jordan Aranda Tejada
 */
public class AnimaxGameMenuActivity extends Activity {

	private ListView				list;
	public SQLiteDatabase			db;
	private ArrayList<Animature>	animatures;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animax_game_menu);

		db = (new Database(this, "AnimatureWorldDB", null, 1))
		.getReadableDatabase();

		// We get a reference to the interface controls
		list = (ListView) findViewById(R.id.list_Animatures);
		animatures = new ArrayList<Animature>();

		// Example data
		final Animature a1 = new Animature(1, "BULBASUR", 100, 50, 3, 7, 80,
		80, 70, 75, 85, 120, 16, 12000, new Drawable[2], null);
		final Animature a2 = new Animature(2, "IVYSAUR", 150, 75, 3, 7, 80, 80,
		70, 75, 85, 240, 36, 100000, new Drawable[2], null);
		final Animature a3 = new Animature(3, "VENUSAUR", 250, 125, 3, 7, 80,
		80, 70, 75, 85, 380, - 1, 200000, new Drawable[2], null);
		animatures.add(a1);
		animatures.add(a2);
		animatures.add(a3);

		final Animature a4 = new Animature(4, "CHARMANDER", 250, 125, 1, - 1,
		80, 80, 70, 75, 85, 380, - 1, 200000, new Drawable[2], null);
		final Animature a5 = new Animature(5, "CHARMELEON", 250, 125, 1, - 1,
		80, 80, 70, 75, 85, 380, - 1, 200000, new Drawable[2], null);
		final Animature a6 = new Animature(6, "CHARIZARD", 250, 125, 1, 9, 80,
		80, 70, 75, 85, 380, - 1, 200000, new Drawable[2], null);
		animatures.add(a4);
		animatures.add(a5);
		animatures.add(a6);

		final Animature a7 = new Animature(7, "SQUIRTLE", 250, 125, 2, - 1, 80,
		80, 70, 75, 85, 380, - 1, 200000, new Drawable[2], null);
		final Animature a8 = new Animature(8, "WARTORTLE", 250, 125, 2, - 1,
		80, 80, 70, 75, 85, 380, - 1, 200000, new Drawable[2], null);
		final Animature a9 = new Animature(9, "BLASTOISE", 250, 125, 16, - 1,
		80, 80, 70, 75, 85, 380, - 1, 200000, new Drawable[2], null);
		animatures.add(a7);
		animatures.add(a8);
		animatures.add(a9);

		loadAnimatureArrayList();

		final AdapterAnimatures adapter = new AdapterAnimatures(this,
		animatures);

		list.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.animax_game_menu, menu);
		return true;
	}

	private void loadAnimatureArrayList()
	{
		final Cursor c = db.rawQuery("SELECT * FROM ANIMATURE", null);
		if (c.getCount() > 0)
		{
			c.moveToFirst();
			while ( ! c.isAfterLast())
			{
				animatures.add(new Animature(c.getInt(0), c.getString(1), c
				.getDouble(2), c.getDouble(3), c.getInt(4), c.getInt(5), c
				.getInt(6), c.getInt(7), c.getInt(8), c.getInt(9),
				c.getInt(10), c.getInt(11), c.getInt(12), c.getInt(13), null,
				null));
				c.moveToNext();
			}
		}
		c.close();
		db.close();
	}
}