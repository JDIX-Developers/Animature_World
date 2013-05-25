package com.jdix.animature;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.jdix.animature.entities.Item;
import com.jdix.animature.utils.ItemAdapter;

/**
 * @author Jordan Aranda Tejada
 */
public class BagActivity extends Activity {

	private ListView		list;
	private TextView		objectInformationTextView;
	private ArrayList<Item>	items;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bag);

		// We get a reference to the interface controls
		list = (ListView) findViewById(R.id.list_Objects);
		objectInformationTextView = (TextView) findViewById(R.id.object_description);

		items = new ArrayList<Item>();

		// Example data
		final Item i1 = new Item(1, 0, 10);
		final Item i2 = new Item(2, 0, 16);
		final Item i3 = new Item(3, 0, 3);
		items.add(i1);
		items.add(i2);
		items.add(i3);

		final ItemAdapter adapter = new ItemAdapter(this, items);

		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(final AdapterView<?> a, final View v,
			final int position, final long id)
			{
				objectInformationTextView.setText(items.get(position)
				.getDescription(BagActivity.this));
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
