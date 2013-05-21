package com.jdix.animature.utils;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jdix.animature.R;
import com.jdix.animature.entities.Item;

/**
 * @author Jordan Aranda Tejada
 */
public class ItemAdapter extends BaseAdapter {

	protected Activity			activity;
	protected ArrayList<Item>	items;

	/**
	 * @param activity The activity of the list.
	 * @param items Items to show in list.
	 */
	public ItemAdapter(final Activity activity, final ArrayList<Item> items)
	{
		this.activity = activity;
		this.items = items;
	}

	@Override
	public int getCount()
	{
		return items.size();
	}

	@Override
	public Object getItem(final int arg0)
	{
		return items.get(arg0);
	}

	@Override
	public long getItemId(final int position)
	{
		return items.get(position).getId();
	}

	@Override
	public View getView(final int position, final View convertView,
	final ViewGroup parent)
	{
		View v = convertView;

		if (convertView == null)
		{
			final LayoutInflater inf = (LayoutInflater) activity
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inf.inflate(R.layout.object_row, null);
		}

		final Item item = items.get(position);
		// NAME
		final TextView idAnimature = (TextView) v
		.findViewById(R.id.object_row_object_name);
		idAnimature.setText(item.getName());
		// QUANTITY
		final TextView nameAnimature = (TextView) v
		.findViewById(R.id.object_row_object_quantity);
		nameAnimature.setText("x " + item.getQuantity());

		return v;
	}
}