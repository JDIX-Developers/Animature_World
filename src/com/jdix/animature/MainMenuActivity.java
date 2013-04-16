package com.jdix.animature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends Activity {

	private Button	btnNewGame;
	private Button	btnLoadGame;
	private Button	btnMultiplayer;
	private Button	btnAnimatureShop;
	private Button	btnOptions;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		// We get a reference to the interface controls
		btnNewGame = (Button) findViewById(R.id.btnNewGame);
		btnNewGame.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				newGame();
			}
		});
		btnLoadGame = (Button) findViewById(R.id.btnLoadGame);
		btnLoadGame.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				loadGame();
			}
		});
		btnMultiplayer = (Button) findViewById(R.id.btnMultiplayer);
		btnMultiplayer.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				multiplayer();
			}
		});
		btnAnimatureShop = (Button) findViewById(R.id.btnAnimatureShop);
		btnAnimatureShop.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				animatureShop();
			}
		});
		btnOptions = (Button) findViewById(R.id.btnOptions);
		btnOptions.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				options();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * Method pressing the new game button
	 */
	public void newGame()
	{
		// We create the attempt
		final Intent intent = new Intent(MainMenuActivity.this,
		NewGameActivity.class);
		// We start the new activity
		startActivity(intent);
	}

	/**
	 * Method pressing the load game button
	 */
	public void loadGame()
	{

	}

	/**
	 * Method pressing the multiplayer button
	 */
	public void multiplayer()
	{

	}

	/**
	 * Method pressing the animature shop button
	 */
	public void animatureShop()
	{

	}

	/**
	 * Method pressing the options button
	 */
	public void options()
	{

	}
}