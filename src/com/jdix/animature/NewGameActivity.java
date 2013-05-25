package com.jdix.animature;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdix.animature.entities.Animature;
import com.jdix.animature.entities.Player;
import com.jdix.animature.map.Map;
import com.jdix.animature.map.Sprite;
import com.jdix.animature.map.Square;

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
	private int				playerSex;
	private int				idAnimatureSelected;
	private String			nameAnimatureSelected;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_game);

		index = 0;

		// We get a reference to the interface controls
		editTextNewGame = (EditText) findViewById(R.id.editTextNewGame);
		layoutOak = (LinearLayout) findViewById(R.id.newGameOakLayout);
		layoutAnimOptions = (LinearLayout) findViewById(R.id.newGameAnimatureOptLayout);

		textViewNewGame = (TextView) findViewById(R.id.textViewBattleActivity);
		textViewNewGame.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				if ( ! (index == 9 && editTextNewGame.getText().toString()
				.trim().equals(""))
				&& ! (index == 12 && editTextNewGame.getText().toString()
				.trim().equals(""))
				&& ! (index == 21 && editTextNewGame.getText().toString()
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
				btnUP();
			}
		});
		btn2NewGame = (Button) findViewById(R.id.btn2NewGame);
		btn2NewGame.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				btnDOWN();
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
	}

	@Override
	public boolean onKeyDown(final int keyCode, final KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_MENU
		|| keyCode == KeyEvent.KEYCODE_BACK)
		{
			((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(50);
			startActivity(new Intent(NewGameActivity.this,
			MainMenuActivity.class));
			finish();
		}

		return super.onKeyDown(keyCode, event);
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
				makeBinaryQuestion(strings[index], "Chico", "Chica");
			break;
			case 9:
				makeNameQuestion(strings[index]);
			break;
			case 10:
				this.playerName = editTextNewGame.getText().toString().trim()
				.toUpperCase(Locale.getDefault());
				makeBinaryQuestion(
				strings[index].replace("%s", this.playerName), "Si", "No");
			break;
			case 11:
				makeDialog(strings[index]);
			break;
			case 12:
				makeNameQuestion(strings[index]);
			break;
			case 13:
				this.enemyName = editTextNewGame.getText().toString().trim()
				.toUpperCase(Locale.getDefault());
				makeBinaryQuestion(
				strings[index].replace("%s", this.enemyName), "Si", "No");
			break;
			case 14:
				makeDialog(strings[index].replace("%s", this.playerName));
			break;
			case 15:
			case 16:
				makeDialog(strings[index]);
			break;
			case 17:
				makeAnimatureSelector(strings[index]);
			break;
			case 18:
				layoutAnimOptions.setVisibility(View.GONE);
				layoutOak.setVisibility(View.VISIBLE);
				String text = strings[index].replace("$a",
				nameAnimatureSelected);

				if (idAnimatureSelected == 1)
				{
					text = text.replace("$s", "Planta");
				}
				else if (idAnimatureSelected == 4)
				{
					text = text.replace("$s", "Fuego");
				}
				else
				{
					text = text.replace("$s", "Agua");
				}
				makeBinaryQuestion(text, "Si", "No");
			break;
			case 19:
				String text2 = strings[index].replace("$s", this.playerName);
				text2 = text2.replace("$a", nameAnimatureSelected);
				makeDialog(text2);
			break;
			case 20:
				makeBinaryQuestion(
				strings[index].replace("$a", nameAnimatureSelected), "Si", "No");
			break;
			case 21:
				makeNameQuestion(strings[index]);
			break;
			case 22:
				this.nameAnimatureSelected = editTextNewGame.getText()
				.toString().trim();
				makeBinaryQuestion(
				strings[index].replace("$a", nameAnimatureSelected), "Si", "No");
			break;
			case 23:
				makeDialog(strings[index].replace("%s", playerName));
			break;
			case 24:
			case 25:
				makeDialog(strings[index]);
			break;
			case 26:
				final DisplayMetrics metrics = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(metrics);
				switch (metrics.densityDpi)
				{
					case DisplayMetrics.DENSITY_LOW:
						Square.setSprite(new Sprite(this, R.raw.sprite24,
						R.drawable.sprite));
					break;
					case DisplayMetrics.DENSITY_MEDIUM:
						Square.setSprite(new Sprite(this, R.raw.sprite32,
						R.drawable.sprite));
					break;
					case DisplayMetrics.DENSITY_HIGH:
						Square.setSprite(new Sprite(this, R.raw.sprite48,
						R.drawable.sprite));
					break;
					case DisplayMetrics.DENSITY_XHIGH:
						Square.setSprite(new Sprite(this, R.raw.sprite64,
						R.drawable.sprite));
					break;
				}

				final Animature[] anims = new Animature[6];
				// TODO create animature
				// anims[0] = Capturable.load(idAnimatureSelected, this); TODO
				final Animature firstAnim = null; // TODO select animature

				Player.set(new Map(R.raw.map_test, this), playerName,
				playerSex, enemyName, anims, 5, 5, Player.SOUTH,
				idAnimatureSelected, this);

				startActivity(new Intent(NewGameActivity.this,
				MapActivity.class));
				finish();
			break;
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

	private void makeBinaryQuestion(final String text, final String op1,
	final String op2)
	{
		textViewNewGame.setText(text);
		btn1NewGame.setText(op1);
		btn2NewGame.setText(op2);
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

	private void btnUP()
	{
		if (index == 8)
		{
			playerSex = Player.BOY;
		}
		changeDialog();
	}

	private void btnDOWN()
	{
		if (index == 8)
		{
			playerSex = Player.GIRL;
		}
		else if (index == 10)
		{
			this.index = 8;
		}
		else if (index == 13)
		{
			this.index = 11;
		}
		else if (index == 18)
		{
			this.index = 16;
		}
		else if (index == 20)
		{
			this.index = 22;
		}
		else if (index == 22)
		{
			this.index = 20;
		}
		changeDialog();
	}

	private void animatureChosen(final int id)
	{
		idAnimatureSelected = id;
		final int idAnimatureName = getResources().getIdentifier(
		"animatureOption" + idAnimatureSelected, "string", getPackageName());
		nameAnimatureSelected = getResources().getString(idAnimatureName);
		changeDialog();
	}
}