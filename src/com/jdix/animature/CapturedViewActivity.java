package com.jdix.animature;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.jdix.animature.entities.Animature;
import com.jdix.animature.entities.Player;
import com.jdix.animature.utils.CapturedAdapter;

/**
 * @author Jordan Aranda Tejada
 */
public class CapturedViewActivity extends Activity {

	private ListView				list;
	private ArrayList<Animature>	playerCaptured;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_captured_view);

		// We get a reference to the interface controls
		list = (ListView) findViewById(R.id.list_player_captured);

		playerCaptured = new ArrayList<Animature>();

		for (int i = 0; i < Player.getInstance().getActiveAnimatures().length; i++)
		{
			if (Player.getInstance().getActiveAnimatures()[i] != null)
			{
				playerCaptured
				.add(Player.getInstance().getActiveAnimatures()[i]);
			}
		}

		final CapturedAdapter adapter = new CapturedAdapter(this,
		playerCaptured);

		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(final AdapterView<?> a, final View v,
			final int position, final long id)
			{
				if (position < Player.getInstance().getActiveAnimatures().length)
				{
					// TODO View captured data.
					final Bundle b = new Bundle();
					b.putInt("animatureIndex", position);
					final Intent intent = new Intent(CapturedViewActivity.this,
					PlayerCapturedAnimatureDataView.class);
					intent.putExtras(b);
					startActivity(intent);
				}
			}
		});
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