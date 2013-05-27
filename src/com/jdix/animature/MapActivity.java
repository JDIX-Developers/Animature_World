package com.jdix.animature;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;

import com.jdix.animature.map.MapView;

/**
 * @author Razican (Iban Eguia)
 */
public class MapActivity extends Activity {

	private MapView	view;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(view = new MapView(this));
	}

	@Override
	protected void onActivityResult(final int requestCode,
	final int resultCode, final Intent data)
	{
		if (requestCode == 1)
		{
			view.continueMoving();
		}
	}

	@Override
	public boolean onKeyDown(final int keyCode, final KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_MENU)
		{
			((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(50);
			startActivity(new Intent(MapActivity.this, GameMenuActivity.class));
		}

		return super.onKeyDown(keyCode, event);
	}
}