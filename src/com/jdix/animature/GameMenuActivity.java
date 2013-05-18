package com.jdix.animature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
 * @author Jordan Aranda Tejada
 */
public class GameMenuActivity extends Activity {

	private Button	btnAnimax;
	private Button	btnAnimatures;
	private Button	btnObjects;
	private Button	btnPlayer;
	private Button	btnSave;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_menu);

		// We get a reference to the interface controls
		btnAnimax = (Button) findViewById(R.id.btn_game_menu_animax);
		btnAnimax.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				final Intent intent = new Intent(GameMenuActivity.this,
				AnimaxMenuActivity.class);
				startActivity(intent);
			}
		});
		btnAnimatures = (Button) findViewById(R.id.btn_game_menu_animatures);
		btnAnimatures.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{

			}
		});
		btnObjects = (Button) findViewById(R.id.btn_game_menu_objects);
		btnObjects.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{

			}
		});
		btnPlayer = (Button) findViewById(R.id.btn_game_menu_player);
		btnPlayer.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{

			}
		});
		btnSave = (Button) findViewById(R.id.btn_game_menu_save);
		btnSave.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{

			}
		});

		changePlayerButtonText("JORDAN"); // Hay que cambiar por el nombre del
											// jugador

	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		finish();
		return true;
	}

	/**
	 * Change the text of the button with the player name.
	 * 
	 * @param playerName
	 */
	void changePlayerButtonText(final String playerName)
	{
		final String s = this.getString(R.string.player_game_menu).replace(
		"*PlayerName*", playerName);
		btnPlayer.setText(s);
	}
}
