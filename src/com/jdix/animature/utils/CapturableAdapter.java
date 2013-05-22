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
import com.jdix.animature.entities.Capturable;

/**
 * @author Jordan Aranda Tejada
 */
public class CapturableAdapter extends BaseAdapter {

	private final Activity				activity;
	private final ArrayList<Capturable>	items;

	/**
	 * @param activity The activity of the list.
	 * @param items Items to show in list.
	 */
	public CapturableAdapter(final Activity activity,
	final ArrayList<Capturable> items)
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

		// Asociamos el layout de la lista que hemos creado
		if (convertView == null)
		{
			final LayoutInflater inf = (LayoutInflater) activity
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inf.inflate(R.layout.animax_row, null);
		}

		// Creamos un objeto Animature
		final Capturable captured = items.get(position);
		// NAME

		// STATUS

		//

		// Retornamos la vista
		return v;
	}

	private void modifyStatusTextView(final TextView textView, final int status)
	{
		switch (status)
		{
			case 0:
				textView.setVisibility(View.INVISIBLE);
			break;
			case 1:
				textView.setText("PARALIZADO");

			break;
			case 2:
				textView.setText("QUEMADO");

			break;
			case 3:
				textView.setText("ENVENENADO");

			break;
			case 4:
				textView.setText("DORMIDO");

			break;
			case 5:
				textView.setText("CONGELADO");

			break;
		}
	}
}