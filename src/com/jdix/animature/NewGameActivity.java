package com.jdix.animature;

import java.util.Locale;
import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jdix.animature.entities.Capturable;
import com.jdix.animature.entities.Item;
import com.jdix.animature.entities.Player;
import com.jdix.animature.utils.Database;

/**
 * @author Jordan Aranda Tejada
 */
public class NewGameActivity extends Activity {

	private TextView	textViewNewGame;
	private EditText	editTextNewGame;
	private Button		btn1NewGame;
	private Button		btn2NewGame;

	private int			index;
	private String[]	strings;
	private Player		player;
	private String		playerName;
	private String		enemyName;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_game);

		player.setPlayer(getLastPlayerId() + 1, "", 0, "", 0, 0, 0, 0,
		new Capturable[6], 0, 0, 2, 0, 0, 0, null, new Vector<Item>());

		index = 0;

		// We get a reference to the interface controls
		editTextNewGame = (EditText) findViewById(R.id.editTextNewGame);

		textViewNewGame = (TextView) findViewById(R.id.textViewNewGame);
		textViewNewGame.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				if ( ! (index == 8 && editTextNewGame.getText().toString()
				.trim().equals(""))
				&& ! (index == 11 && editTextNewGame.getText().toString()
				.trim().equals("")))
				{
					changeDialog();
				}
			}
		});

		btn1NewGame = (Button) findViewById(R.id.btn1NewGame);
		btn1NewGame.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				btnYes();
			}
		});
		btn2NewGame = (Button) findViewById(R.id.btn2NewGame);
		btn2NewGame.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				btnNo();
			}
		});

		strings = getResources().getStringArray(R.array.new_game_strings);

		textViewNewGame.setText(strings[index]);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		return super.onCreateOptionsMenu(menu);
	}

	private void changeDialog()
	{
		index = index + 1;
		switch (index)
		{
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				textViewNewGame.setText(strings[index]);
			break;
			case 8:
				textViewNewGame.setText(strings[index]);
				editTextNewGame.setVisibility(View.VISIBLE);
			break;
			case 9:
				this.playerName = editTextNewGame.getText().toString().trim()
				.toUpperCase(Locale.getDefault());
				textViewNewGame.setText(strings[index].replace("%s",
				this.playerName));
				editTextNewGame.setVisibility(View.INVISIBLE);
				textViewNewGame.setCompoundDrawables(null, null, null, null);
				textViewNewGame.setClickable(false);
				btn1NewGame.setVisibility(View.VISIBLE);
				btn2NewGame.setVisibility(View.VISIBLE);
				btn1NewGame.setText(getString(R.string.yes_option));
				btn2NewGame.setText(getString(R.string.no_option));
			break;
			case 10:
				textViewNewGame.setText(strings[index]);
			break;
			case 11:
				textViewNewGame.setText(strings[index]);
				editTextNewGame.setText("");
				editTextNewGame.setVisibility(View.VISIBLE);
			break;
			case 12:
				this.enemyName = editTextNewGame.getText().toString().trim()
				.toUpperCase(Locale.getDefault());
				textViewNewGame.setText(strings[index].replace("%s",
				this.enemyName));
				editTextNewGame.setVisibility(View.INVISIBLE);
				textViewNewGame.setCompoundDrawables(null, null, null, null);
				textViewNewGame.setClickable(false);
				btn1NewGame.setVisibility(View.VISIBLE);
				btn2NewGame.setVisibility(View.VISIBLE);
				btn1NewGame.setText(getString(R.string.yes_option));
				btn2NewGame.setText(getString(R.string.no_option));
			break;
			case 13:
				textViewNewGame.setText(strings[index].replace("%s",
				this.playerName));
			break;
			case 14:
				textViewNewGame.setText(strings[index]);
			break;
			case 15:
				startActivity(new Intent(NewGameActivity.this,
				MapActivity.class));
				finish();
		}

	}

	private void btnYes()
	{
		if (index == 9)
		{
			player.getInstance().setName(playerName);
		}
		else if (index == 12)
		{
			player.getInstance().setNeighborName(enemyName);
		}
		btn1NewGame.setVisibility(View.INVISIBLE);
		btn2NewGame.setVisibility(View.INVISIBLE);
		changeDialog();
		textViewNewGame.setCompoundDrawablesWithIntrinsicBounds(null, null,
		getResources().getDrawable(R.drawable.flecha), null);
		textViewNewGame.setClickable(true);
	}

	private void btnNo()
	{
		if (index == 9)
		{
			this.index = 7;
		}
		else if (index == 12)
		{
			this.index = 10;
		}
		btn1NewGame.setVisibility(View.INVISIBLE);
		btn2NewGame.setVisibility(View.INVISIBLE);
		changeDialog();
		textViewNewGame.setCompoundDrawablesWithIntrinsicBounds(null, null,
		getResources().getDrawable(R.drawable.flecha), null);
		textViewNewGame.setClickable(true);
	}

	private int getLastPlayerId()
	{
		final SQLiteDatabase db = (new Database(this)).getReadableDatabase();
		final Cursor c = db.rawQuery("SELECT * FROM SAVE", null);
		final int id = c.getCount();
		c.close();
		db.close();
		return id;
	}
}