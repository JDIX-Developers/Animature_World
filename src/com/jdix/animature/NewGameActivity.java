package com.jdix.animature;

import java.util.Vector;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewGameActivity extends Activity {

	private TextView		textViewNewGame;
	private EditText		editTextNewGame;
	private Button			btn1NewGame;
	private Button			btn2NewGame;

	private Vector<String>	strings;
	private int				index;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_game);

		// We get a reference to the interface controls
		textViewNewGame = (TextView) findViewById(R.id.textViewNewGame);
		editTextNewGame = (EditText) findViewById(R.id.editTextNewGame);
		btn1NewGame = (Button) findViewById(R.id.btn1NewGame);
		btn2NewGame = (Button) findViewById(R.id.btn2NewGame);

		index = 0;
		strings = new Vector<String>();
		loadVectorStrings(strings);

		textViewNewGame.setText(strings.get(index));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return super.onCreateOptionsMenu(menu);
	}

	public void loadVectorStrings(Vector<String> strings)
	{
		strings.add(this.getString(R.string.newGame1));
		strings.add(this.getString(R.string.newGame2));
		strings.add(this.getString(R.string.newGame3));
		strings.add(this.getString(R.string.newGame4));
		strings.add(this.getString(R.string.newGame5));
		strings.add(this.getString(R.string.newGame6));
		strings.add(this.getString(R.string.newGame7));
		strings.add(this.getString(R.string.newGame8));
		strings.add(this.getString(R.string.newGame9));
		strings.add(this.getString(R.string.newGame10));
		strings.add(this.getString(R.string.newGame11));

	}

	public void next(View view)
	{
		index++;
		switch (index)
		{
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				textViewNewGame.setText(strings.get(index));
				break;
			case 8:
				textViewNewGame.setText(strings.get(index));
				btn1NewGame.setVisibility(View.VISIBLE);
				btn2NewGame.setVisibility(View.VISIBLE);
				btn1NewGame.setText(this.getString(R.string.boy));
				btn2NewGame.setText(this.getString(R.string.girl));
				textViewNewGame.setClickable(false);
				break;
			case 11:
				editTextNewGame.requestFocus();
				break;
		}

	}
}