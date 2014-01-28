package com.jdix.animature;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.jdix.animature.utils.GifView;

/**
 * @author Razican (Iban Eguia)
 */
public class GifTestActivity extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(new GifView(this));
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		return super.onCreateOptionsMenu(menu);
	}
}
