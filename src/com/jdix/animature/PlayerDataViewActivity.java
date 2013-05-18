package com.jdix.animature;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.jdix.animature.utils.Database;

/**
 * @author Jordan Aranda Tejada
 */
public class PlayerDataViewActivity extends Activity {

	private TextView		playerId;
	private TextView		playerName;
	private TextView		playerPlayedTime;
	private TextView		playerViewAnimatures;
	private TextView		playerCapturedAnimatures;
	public SQLiteDatabase	db;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_data_view);

		db = (new Database(this)).getWritableDatabase();

		// We get a reference to the interface controls
		playerId = (TextView) findViewById(R.id.player_id_view);
		playerName = (TextView) findViewById(R.id.player_name_view);
		playerPlayedTime = (TextView) findViewById(R.id.player_playedTime_view);
		playerViewAnimatures = (TextView) findViewById(R.id.player_viewedAnimatures_view);
		playerCapturedAnimatures = (TextView) findViewById(R.id.player_capturedAnimatures_view);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.player_data_view, menu);
		return true;
	}
}