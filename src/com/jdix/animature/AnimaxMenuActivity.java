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
import com.jdix.animature.utils.AnimatureAdapter;

/**
 * @author Jordan Aranda Tejada
 */
public class AnimaxMenuActivity extends Activity {

	private ListView				list;
	private ArrayList<Animature>	animatures;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animax_game_menu);

		// We get a reference to the interface controls
		list = (ListView) findViewById(R.id.list_Animatures);
		animatures = new ArrayList<Animature>();

		final AnimatureAdapter adapter = new AnimatureAdapter(this);

		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(final AdapterView<?> arg0, final View view,
			final int pos, final long arg3)
			{
				final Intent intent = new Intent(AnimaxMenuActivity.this,
				AnimaxAnimatureActivity.class);
				final Bundle b = new Bundle();
				b.putSerializable("animature_id", animatures.get(pos).getId());
				intent.putExtras(b);

				startActivity(intent);
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