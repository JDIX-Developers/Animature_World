package com.jdix.animature;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.jdix.animature.map.MapView;

/**
 * @author Razican (Iban Eguia)
 */
public class MapActivity extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(new MapView(this, R.raw.map_test, R.raw.sprite,
		R.drawable.sprite));
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		return super.onCreateOptionsMenu(menu);
	}
}