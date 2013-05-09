package com.jdix.animature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

		final DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		switch (metrics.densityDpi)
		{
			case DisplayMetrics.DENSITY_LOW:
				setContentView(new MapView(this, R.raw.map_test,
				R.raw.sprite24, R.drawable.sprite));
			break;
			case DisplayMetrics.DENSITY_MEDIUM:
				setContentView(new MapView(this, R.raw.map_test,
				R.raw.sprite32, R.drawable.sprite));
			break;
			case DisplayMetrics.DENSITY_HIGH:
				setContentView(new MapView(this, R.raw.map_test,
				R.raw.sprite48, R.drawable.sprite));
			break;
			case DisplayMetrics.DENSITY_XHIGH:
				setContentView(new MapView(this, R.raw.map_test,
				R.raw.sprite64, R.drawable.sprite));
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		super.onCreateOptionsMenu(menu);

		startActivity(new Intent(MapActivity.this, GameMenuActivity.class));

		return true;
	}
}