package com.jdix.poketmon;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends Activity 
{
	private Button btnNewGame;
	private Button btnLoadGame;
	private Button btnMultiplayer;
	private Button btnAnimatureShop;
	private Button btnOptions;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		// We get a reference to the interface controls
		btnNewGame = (Button)findViewById(R.id.btnNewGame);
		btnLoadGame = (Button)findViewById(R.id.btnLoadGame);
		btnMultiplayer = (Button)findViewById(R.id.btnMultiplayer);
		btnAnimatureShop = (Button)findViewById(R.id.btnAnimatureShop);
		btnOptions = (Button)findViewById(R.id.btnOptions);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	/**
	 * Method pressing the new game button
	 * @param View
	 */
	public void newGame(View view)
	{
		
	}
	/**
	 * Method pressing the load game button
	 * @param View
	 */
	public void loadGame(View view)
	{
		
	}
	/**
	 * Method pressing the multiplayer button
	 * @param View
	 */
	public void multiplayer(View view)
	{
		
	}
	/**
	 * Method pressing the animature shop button
	 * @param View
	 */
	public void animatureShop(View view)
	{
		
	}
	/**
	 * Method pressing the options button
	 * @param View
	 */
	public void options(View view)
	{
		
	}

}
