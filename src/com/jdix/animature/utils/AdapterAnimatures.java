package com.jdix.animature.utils;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jdix.animature.R;
import com.jdix.animature.entities.Animature;

/**
 * @author Jordan Aranda Tejada
 */
public class AdapterAnimatures extends BaseAdapter {

	protected Activity				activity;
	protected ArrayList<Animature>	items;
	protected String[]				typesNames;
	protected String[]				typesColors;

	/**
	 * @param activity The activity of the list.
	 * @param items Items to show in list.
	 */
	public AdapterAnimatures(final Activity activity,
	final ArrayList<Animature> items)
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
		return items.get(position).getId_Animature();
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
			v = inf.inflate(R.layout.animax_row, null);
		}

		typesNames = getResources().getStringArray(R.array.animature_types);
		typesColors = getResources().getStringArray(
		R.array.animature_types_colors);

		// Creamos un objeto Animature
		final Animature animature = items.get(position);
		// ID
		final TextView idAnimature = (TextView) v
		.findViewById(R.id.animax_row_animature_id);
		idAnimature
		.setText(getFormatedIdAnimature(animature.getId_Animature()));
		// NAME
		final TextView nameAnimature = (TextView) v
		.findViewById(R.id.animax_row_animature_name);
		nameAnimature.setText(animature.getName());
		// TYPE 1
		final TextView type1Animature = (TextView) v
		.findViewById(R.id.animax_row_type1);
		// TYPE 2
		final TextView type2Animature = (TextView) v
		.findViewById(R.id.animax_row_type2);

		final TextView[] textViews = {type1Animature, type2Animature};
		modifyTypeTextView(textViews, animature);

		return v;
	}

	private Resources getResources()
	{
		// TODO Auto-generated method stub
		return null;
	}

	private String getFormatedIdAnimature(final int id)
	{
		if (id < 10)
		{
			return "00" + id;
		}
		else if (id < 100)
		{
			return "0" + id;
		}
		else
		{
			return "" + id;
		}
	}

	private void modifyTypeTextView(final TextView[] textViews,
	final Animature animature)
	{
		int cont = 0;
		for (int i = Animature.NORMAL; i < Animature.STEEL; i *= 2)
		{
			if (animature.isOfType(i))
			{
				textViews[cont].setText(typesNames[(int) (Math.log(i) / Math
				.log(2))]);
				textViews[cont].setBackgroundColor(Color
				.parseColor(typesColors[(int) (Math.log(i) / Math.log(2))]));
				cont++;
			}
		}
	}
}