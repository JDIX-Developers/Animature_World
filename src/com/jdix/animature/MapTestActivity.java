package com.jdix.animature;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;

import com.jdix.animature.map.MapView;
import com.jdix.animature.map.Square;

/**
 * @author Razican (Iban Eguia)
 */
public class MapTestActivity extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Square.setSize((short) 16);
		Square.setSprite(BitmapFactory.decodeResource(getResources(),
		R.drawable.sprite));
		setContentView(new MapView(this, R.raw.map_test));
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		return super.onCreateOptionsMenu(menu);
	}
}