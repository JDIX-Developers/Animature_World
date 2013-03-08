package com.jdix.animature;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class NewGameActivity extends Activity {
	
	private EditText	editText_playerName;
	private RadioGroup	rgSex;
	private RadioButton	rButtonBoy;
	private RadioButton	rButtonGirl;
	private EditText	editText_neighborName;
	private Button		generate;
	private RadioGroup	rgPet;
	private RadioButton	rButtonDog;
	private RadioButton	rButtonCat;
	private Button		btn_play;

	private String		playerName;
	private int			sex;													// 0
																				// -
																				// Boy
																				// and
																				// 1
																				// -
																				// Girl
	private String		neighborName;
	private int			pet;													// 0
																				// -
																				// Dog
																				// and
																				// 1
																				// -
																				// Cat

	private String[]	generatedNames	= { "Xabier", "Iban", "David",
			"Jordan", "Paula", "Lucas", "Nerea", "Javier", "Irene", "Mario", "Lucia", "Daniel" };
	private int			index			= -1;									// Index
																				// for
																				// generatedNames

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_game);

		// We get a reference to the interface controls
		editText_playerName = (EditText) findViewById(R.id.editText_playerName);
		rgSex = (RadioGroup) findViewById(R.id.rbgroup_sex);
		rButtonBoy = (RadioButton) findViewById(R.id.radioButton_boy);
		rButtonGirl = (RadioButton) findViewById(R.id.radioButton_girl);
		editText_neighborName = (EditText) findViewById(R.id.editText_neighborName);
		generate = (Button) findViewById(R.id.btn_generate);
		rgPet = (RadioGroup) findViewById(R.id.rbgroup_pet);
		rButtonDog = (RadioButton) findViewById(R.id.radioButton_dog);
		rButtonCat = (RadioButton) findViewById(R.id.radioButton_cat);
		btn_play = (Button) findViewById(R.id.btnPlay);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * Method pressing the generate button
	 * 
	 * @param View
	 */
	public void generate_NameNeighbor(View view)
	{
		this.index++;
		if (this.index > 11)
			this.index = 0;
		editText_neighborName.setText(this.generatedNames[this.index]);
	}

	/**
	 * Method pressing the play button
	 * 
	 * @param View
	 */
	public void startNewGame(View view)
	{

	}
}