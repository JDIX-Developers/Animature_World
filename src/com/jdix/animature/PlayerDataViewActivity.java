package com.jdix.animature;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.widget.TextView;

import com.jdix.animature.entities.Player;

/**
 * @author Jordan Aranda Tejada
 */
public class PlayerDataViewActivity extends Activity {

	private TextView	playerId;
	private TextView	playerName;
	private TextView	playerPlayedTime;
	private TextView	playerViewAnimatures;
	private TextView	playerCapturedAnimatures;
	private TextView	playerMoney;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_data_view);

		/*
		 * Player.set("JORDAN", 0, "PEPE",new Date( 1371726970906L), 350, new
		 * Animature[6], 10, 25, 2, 1, 3, 100, 3, 200, new Vector<Item>(),
		 * null);
		 */

		// We get a reference to the interface controls
		playerId = (TextView) findViewById(R.id.player_id_view);
		playerName = (TextView) findViewById(R.id.player_name_view);
		playerPlayedTime = (TextView) findViewById(R.id.player_playedTime_view);
		playerViewAnimatures = (TextView) findViewById(R.id.player_viewedAnimatures_view);
		playerCapturedAnimatures = (TextView) findViewById(R.id.player_capturedAnimatures_view);
		playerMoney = (TextView) findViewById(R.id.player_money_view);

		playerId.setText("ID: " + Player.getInstance().getId());
		playerName.setText("Nombre: " + Player.getInstance().getName());
		playerPlayedTime.setText("Tiempo jugado:"
		+ Player.getInstance().getPlayedTime());
		playerViewAnimatures.setText("Animax: "
		+ Player.getInstance().getViewed(this));
		playerCapturedAnimatures.setText("Animatures caputarados: "
		+ Player.getInstance().getCaptured(this));
		playerMoney.setText("Monedas: " + Player.getInstance().getMoney());
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