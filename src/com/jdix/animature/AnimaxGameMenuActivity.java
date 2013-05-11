package com.jdix.animature;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.jdix.animature.entities.Animature;
import com.jdix.animature.utils.AdapterAnimatures;

/**
 * @author Jordan Aranda Tejada
 */
public class AnimaxGameMenuActivity extends Activity {

	private ListView				list;
	private ArrayList<Animature>	animatures;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animax_game_menu);

		// We get a reference to the interface controls
		list = (ListView) findViewById(R.id.list_Animax);
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

		final Animature a4 = new Animature(4, "CHARMANDER", 250, 125, 3, 7, 80,
		80, 70, 75, 85, 380, - 1, 200000, new Drawable[2], null);
		final Animature a5 = new Animature(5, "CHARMELEON", 250, 125, 3, 7, 80,
		80, 70, 75, 85, 380, - 1, 200000, new Drawable[2], null);
		final Animature a6 = new Animature(6, "CHARIZARD", 250, 125, 3, 7, 80,
		80, 70, 75, 85, 380, - 1, 200000, new Drawable[2], null);
		animatures.add(a4);
		animatures.add(a5);
		animatures.add(a6);

		final Animature a7 = new Animature(7, "SQUIRTLE", 250, 125, 3, 7, 80,
		80, 70, 75, 85, 380, - 1, 200000, new Drawable[2], null);
		final Animature a8 = new Animature(8, "WARTORTLE", 250, 125, 3, 7, 80,
		80, 70, 75, 85, 380, - 1, 200000, new Drawable[2], null);
		final Animature a9 = new Animature(9, "BLASTOISE", 250, 125, 3, 7, 80,
		80, 70, 75, 85, 380, - 1, 200000, new Drawable[2], null);
		animatures.add(a7);
		animatures.add(a8);
		animatures.add(a9);

		// Creo el adapter personalizado
		final AdapterAnimatures adapter = new AdapterAnimatures(this,
		animatures);

		// Lo aplico
		list.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.animax_game_menu, menu);
		return true;
	}

}
