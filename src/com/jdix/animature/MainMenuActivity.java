package com.jdix.animature;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.jdix.animature.entities.User;
import com.jdix.animature.utils.Database;

/**
 * @author Jordan Aranda Tejada
 */
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
				finish();
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
		startActivity(new Intent(MainMenuActivity.this, NewGameActivity.class));
	}

	/**
	 * Method pressing the load game button
	 */
	public void loadGame()
	{
		final SQLiteDatabase db = (new Database(this)).getReadableDatabase();
		final Cursor c = db.rawQuery("SELECT * FROM SAVE WHERE user = "
		+ User.getCurrent().getId(), null);
		final int count = c.getCount();
		c.close();
		db.close();

		if (count == 0)
		{
			final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setTitle(getResources().getString(R.string.no_save_game));
			dialog.setMessage(getResources().getString(
			R.string.no_save_game_want_to_create));

			dialog.setCancelable(false);
			dialog.setPositiveButton(
			getResources().getString(R.string.yes_option),
			new DialogInterface.OnClickListener()
			{

				@Override
				public void onClick(final DialogInterface dialog, final int id)
				{
					startActivity(new Intent(MainMenuActivity.this,
					NewGameActivity.class));
					finish();
				}
			});
			dialog.setNegativeButton(
			getResources().getString(R.string.no_option),
			new DialogInterface.OnClickListener()
			{

				@Override
				public void onClick(final DialogInterface dialog, final int id)
				{
					dialog.cancel();
				}
			});
			dialog.show();
		}
		else
		{
			// Player.load(id, context)
			// Carga el juego
		}
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