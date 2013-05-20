package com.jdix.animature;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
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
	public boolean onKeyDown(final int keyCode, final KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_MENU)
		{
			((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(50);
			finish();
		}

		return super.onKeyDown(keyCode, event);
	}
}