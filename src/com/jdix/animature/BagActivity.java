package com.jdix.animature;

import java.util.ArrayList;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.jdix.animature.entities.Item;
import com.jdix.animature.utils.AdapterObjects;
import com.jdix.animature.utils.Database;

/**
 * @author Jordan Aranda Tejada
 */
public class BagActivity extends Activity {

	private ListView		list;
	private TextView		objectInformationTextView;
	public SQLiteDatabase	db;
	private ArrayList<Item>	items;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bag);

		db = (new Database(this)).getReadableDatabase();

		// We get a reference to the interface controls
		list = (ListView) findViewById(R.id.list_Objects);
		objectInformationTextView = (TextView) findViewById(R.id.object_description);

		items = new ArrayList<Item>();

		// Example data
		final Item i1 = new Item(1, "POCIÓN", 0, "Cura 50 PS de tu Animature.",
		10);
		final Item i2 = new Item(2, "ANTIDOTO", 1,
		"Cura del envenenamiento a tu Animature.", 12);
		final Item i3 = new Item(3, "SUPERPOCIÓN", 0,
		"Cura 100 PS de tu Animature.", 5);
		items.add(i1);
		items.add(i2);
		items.add(i3);

		final AdapterObjects adapter = new AdapterObjects(this, items);

		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(final AdapterView<?> a, final View v,
			final int position, final long id)
			{
				objectInformationTextView.setText(items.get(position)
				.getDescription());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bag, menu);
		return true;
	}
}
