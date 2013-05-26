package com.jdix.animature;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jdix.animature.entities.Player;
import com.jdix.animature.entities.User;
import com.jdix.animature.map.Sprite;
import com.jdix.animature.map.Square;
import com.jdix.animature.utils.Database;

/**
 * @author Jordan Aranda Tejada
 */
public class MainMenuActivity extends Activity {

	private TextView	textViewUser;
	private Button		btnNewGame;
	private Button		btnLoadGame;
	private Button		btnMultiplayer;
	private Button		btnAnimatureShop;
	private Button		btnOptions;
	private Button		btnExit;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		// We get a reference to the interface controls
		textViewUser = (TextView) findViewById(R.id.user_textView);
		textViewUser.setText("User: " + User.getCurrent().getUsername());

		btnNewGame = (Button) findViewById(R.id.btnNewGame);
		btnNewGame.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				startActivity(new Intent(MainMenuActivity.this,
				NewGameActivity.class));
			}
		});
		btnLoadGame = (Button) findViewById(R.id.btnLoadGame);
		btnLoadGame.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				final SQLiteDatabase db = (new Database(MainMenuActivity.this))
				.getReadableDatabase();
				final Cursor c = db.rawQuery("SELECT * FROM SAVE WHERE user = "
				+ User.getCurrent().getId(), null);
				final int count = c.getCount();
				c.close();
				db.close();

				if (count == 0)
				{
					final AlertDialog.Builder dialog = new AlertDialog.Builder(
					MainMenuActivity.this);
					dialog.setTitle(getResources().getString(
					R.string.no_save_game));
					dialog.setMessage(getResources().getString(
					R.string.no_save_game_want_to_create));

					dialog.setCancelable(false);
					dialog.setPositiveButton(
					getResources().getString(R.string.yes_option),
					new DialogInterface.OnClickListener()
					{

						@Override
						public void onClick(final DialogInterface dialog,
						final int id)
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
						public void onClick(final DialogInterface dialog,
						final int id)
						{
							dialog.cancel();
						}
					});
					dialog.show();
				}
				else
				{
					// TODO adapter y lista de partidas

					final DisplayMetrics metrics = new DisplayMetrics();
					getWindowManager().getDefaultDisplay().getMetrics(metrics);
					switch (metrics.densityDpi)
					{
						case DisplayMetrics.DENSITY_LOW:
							Square.setSprite(new Sprite(MainMenuActivity.this,
							R.raw.sprite24, R.drawable.sprite));
						break;
						case DisplayMetrics.DENSITY_MEDIUM:
							Square.setSprite(new Sprite(MainMenuActivity.this,
							R.raw.sprite32, R.drawable.sprite));
						break;
						case DisplayMetrics.DENSITY_HIGH:
							Square.setSprite(new Sprite(MainMenuActivity.this,
							R.raw.sprite48, R.drawable.sprite));
						break;
						case DisplayMetrics.DENSITY_XHIGH:
							Square.setSprite(new Sprite(MainMenuActivity.this,
							R.raw.sprite64, R.drawable.sprite));
						break;
					}

					Player.load(1, MainMenuActivity.this);
					startActivity(new Intent(MainMenuActivity.this,
					MapActivity.class));
				}
			}
		});
		btnMultiplayer = (Button) findViewById(R.id.btnMultiplayer);
		btnMultiplayer.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				showUpdateMessage();
			}
		});
		btnAnimatureShop = (Button) findViewById(R.id.btnAnimatureShop);
		btnAnimatureShop.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				showUpdateMessage();
			}
		});
		btnOptions = (Button) findViewById(R.id.btnOptions);
		btnOptions.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				showUpdateMessage();
			}
		});
		btnExit = (Button) findViewById(R.id.btnExitMainMenu);
		btnExit.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				// Player.getInstance().exit();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		return super.onCreateOptionsMenu(menu);
	}

	private void showUpdateMessage()
	{
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("No disponible");
		builder.setMessage("¡Espere nuevas actualizaciones!");
		builder.setPositiveButton("Aceptar", null);
		builder.create();
		builder.show();
	}
}