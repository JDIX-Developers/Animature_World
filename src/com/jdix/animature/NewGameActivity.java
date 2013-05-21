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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdix.animature.entities.Capturable;
import com.jdix.animature.entities.Item;
import com.jdix.animature.entities.Player;
import com.jdix.animature.utils.Database;

/**
 * @author Jordan Aranda Tejada
 */
public class NewGameActivity extends Activity {

	private TextView		textViewNewGame;
	private EditText		editTextNewGame;
	private Button			btn1NewGame;
	private Button			btn2NewGame;
	private ImageButton		animOption1;
	private ImageButton		animOption2;
	private ImageButton		animOption3;
	private LinearLayout	layoutOak;
	private LinearLayout	layoutAnimOptions;

	private int				index;
	private String[]		strings;
	private String			playerName;
	private String			enemyName;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_game);

		Player.setPlayer(getLastPlayerId() + 1, "", 0, "", 0, 0, 0, 0,
		new Capturable[6], 0, 0, 2, 0, 0, 0, null, new Vector<Item>());

		index = 0;

		// We get a reference to the interface controls
		editTextNewGame = (EditText) findViewById(R.id.editTextNewGame);
		layoutOak = (LinearLayout) findViewById(R.id.newGameOakLayout);
		layoutAnimOptions = (LinearLayout) findViewById(R.id.newGameAnimatureOptLayout);

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

		animOption1 = (ImageButton) findViewById(R.id.btnAnimatureOpt1);
		animOption1.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				animatureChosen(1);
			}
		});

		animOption2 = (ImageButton) findViewById(R.id.btnAnimatureOpt2);
		animOption2.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				animatureChosen(4);
			}
		});

		animOption3 = (ImageButton) findViewById(R.id.btnAnimatureOpt3);
		animOption3.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				animatureChosen(7);
			}
		});

		strings = getResources().getStringArray(R.array.new_game_strings);

		textViewNewGame.setText(strings[index]);
		btn1NewGame.setText(getString(R.string.yes_option));
		btn2NewGame.setText(getString(R.string.no_option));
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
				makeDialog(strings[index]);
			break;
			case 8:
				makeNameQuestion(strings[index]);
			break;
			case 9:
				this.playerName = editTextNewGame.getText().toString().trim()
				.toUpperCase(Locale.getDefault());
				makeYesNoQuestion(strings[index].replace("%s", this.playerName));
			break;
			case 10:
				makeDialog(strings[index]);
			break;
			case 11:
				makeNameQuestion(strings[index]);
			break;
			case 12:
				this.enemyName = editTextNewGame.getText().toString().trim()
				.toUpperCase(Locale.getDefault());
				makeYesNoQuestion(strings[index].replace("%s", this.enemyName));
			break;
			case 13:
				makeDialog(strings[index].replace("%s", this.playerName));
			break;
			case 14:
				makeDialog(strings[index]);
			break;
			case 15:
				makeDialog(strings[index]);
			break;
			case 16:
				makeAnimatureSelector(strings[index]);
			break;
			case 17:
				layoutAnimOptions.setVisibility(View.GONE);
				layoutOak.setVisibility(View.VISIBLE);
				makeYesNoQuestion(strings[index]);
			break;
			case 18:
				startActivity(new Intent(NewGameActivity.this,
				MapActivity.class));
				finish();
		}

	}

	private void makeDialog(final String text)
	{
		textViewNewGame.setText(text);
		btn1NewGame.setVisibility(View.GONE);
		btn2NewGame.setVisibility(View.GONE);
		editTextNewGame.setVisibility(View.GONE);
		textViewNewGame.setCompoundDrawablesWithIntrinsicBounds(null, null,
		getResources().getDrawable(R.drawable.flecha), null);
		textViewNewGame.setClickable(true);
	}

	private void makeNameQuestion(final String text)
	{
		textViewNewGame.setText(text);
		editTextNewGame.setText("");
		btn1NewGame.setVisibility(View.GONE);
		btn2NewGame.setVisibility(View.GONE);
		editTextNewGame.setVisibility(View.VISIBLE);
		textViewNewGame.setCompoundDrawablesWithIntrinsicBounds(null, null,
		getResources().getDrawable(R.drawable.flecha), null);
		textViewNewGame.setClickable(true);
	}

	private void makeYesNoQuestion(final String text)
	{
		textViewNewGame.setText(text);
		btn1NewGame.setVisibility(View.VISIBLE);
		btn2NewGame.setVisibility(View.VISIBLE);
		editTextNewGame.setVisibility(View.GONE);
		textViewNewGame.setCompoundDrawables(null, null, null, null);
		textViewNewGame.setClickable(false);
	}

	private void makeAnimatureSelector(final String text)
	{
		textViewNewGame.setText(text);
		editTextNewGame.setText("");
		btn1NewGame.setVisibility(View.GONE);
		btn2NewGame.setVisibility(View.GONE);
		editTextNewGame.setVisibility(View.GONE);
		textViewNewGame.setCompoundDrawables(null, null, null, null);
		textViewNewGame.setClickable(false);
		layoutOak.setVisibility(View.GONE);
		layoutAnimOptions.setVisibility(View.VISIBLE);
	}

	private void btnYes()
	{
		if (index == 9)
		{
			Player.getInstance().setName(playerName);
		}
		else if (index == 12)
		{
			Player.getInstance().setNeighborName(enemyName);
		}
		changeDialog();
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
		changeDialog();
	}

	private void animatureChosen(final int id)
	{

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