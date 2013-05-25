package com.jdix.animature.utils;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
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
public class AnimatureAdapter extends BaseAdapter {

	private final Context				context;
	private final ArrayList<Integer>	items;
	private String[]					typesNames;

	/**
	 * @param context The context of list.
	 */
	public AnimatureAdapter(final Context context,
	final ArrayList<Integer> items)
	{
		this.context = context;
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
		return items.get(position);
	}

	@Override
	public View getView(final int position, final View convertView,
	final ViewGroup parent)
	{
		View v = convertView;

		if (convertView == null)
		{
			final LayoutInflater inf = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inf.inflate(R.layout.animax_row, null);
		}

		typesNames = context.getResources().getStringArray(
		R.array.animature_types_names);

		// ID
		final TextView idAnimature = (TextView) v
		.findViewById(R.id.animax_row_animature_id);
		idAnimature.setText(getFormatedIdAnimature(position + 1));
		// NAME
		final TextView nameAnimature = (TextView) v
		.findViewById(R.id.animax_row_animature_name);
		nameAnimature.setText(Animature.getName(position + 1, context));
		// TYPE 1
		final TextView type1Animature = (TextView) v
		.findViewById(R.id.animax_row_type1);
		// TYPE 2
		final TextView type2Animature = (TextView) v
		.findViewById(R.id.animax_row_type2);

		Log.e("INDICE POSITION", "Indice: " + position);

		final TextView[] textViews = {type1Animature, type2Animature};
		modifyTypeTextView(textViews, position + 1);

		return v;
	}

	private void modifyTypeTextView(final TextView[] textViews,
	final int animature)
	{
		int cont = 0;
		for (int i = Animature.NORMAL; i < Animature.STEEL; i *= 2)
		{
			Log.e("ANIMATURE CHECK", "Animature: " + animature);
			if (Animature.isOfType(i, animature, context))
			{
				textViews[cont].setText(typesNames[(int) (Math.log(i) / Math
				.log(2))]);

				final TypedArray colors = context.getResources()
				.obtainTypedArray(R.array.animature_types_colors);
				final int color = colors.getColor(
				(int) (Math.log(i) / Math.log(2)), 0);

				textViews[cont].setBackgroundColor(color);

				colors.recycle();
				cont++;
			}
		}
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
}