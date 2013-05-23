package com.jdix.animature;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.jdix.animature.entities.Animature;
import com.jdix.animature.utils.AnimatureAdapter;
import com.jdix.animature.utils.Database;

/**
 * @author Jordan Aranda Tejada
 */
public class AnimaxMenuActivity extends Activity {

	private ListView				list;
	private SQLiteDatabase			db;
	private ArrayList<Animature>	animatures;
	private String[]				animatureNames;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animax_game_menu);

		db = (new Database(this)).getReadableDatabase();

		// We get a reference to the interface controls
		list = (ListView) findViewById(R.id.list_Animatures);
		animatures = new ArrayList<Animature>();

		final Cursor c = db.rawQuery("SELECT * FROM ANIMATURE", null);
		if (c.getCount() > 0)
		{
			c.moveToFirst();
			while ( ! c.isAfterLast())
			{
				animatures.add(new Animature(c.getInt(0), animatureNames[c
				.getInt(0) - 1], c.getDouble(1), c.getDouble(2), c.getInt(3), c
				.getInt(4), c.getInt(5), c.getInt(6), c.getInt(7), c.getInt(8),
				c.getInt(9), c.getInt(10), c.getInt(11), c.getInt(12)));
				c.moveToNext();
			}
		}
		c.close();
		db.close();

		final AnimatureAdapter adapter = new AnimatureAdapter(this, animatures);

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
				b.putSerializable("Animature", animatures.get(pos));
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