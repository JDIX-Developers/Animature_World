package com.jdix.animature.utils;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
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
	private final TypedArray			colors;

	/**
	 * @param context The context of list.
	 */
	public AnimatureAdapter(final Context context,
	final ArrayList<Integer> items)
	{
		this.context = context;
		this.items = items;
		this.colors = context.getResources().obtainTypedArray(
		R.array.animature_types_colors);
		this.typesNames = context.getResources().getStringArray(
		R.array.animature_types_names);
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

		final TextView[] textViews = {type1Animature, type2Animature};
		modifyTypeTextView(textViews, position + 1);

		return v;
	}

	private void modifyTypeTextView(final TextView[] textViews,
	final int animature)
	{
		final int[] types = Animature.getTypes(animature, context);

		final int typeIndex1 = (int) Math.round(Math.log(types[0])
		/ Math.log(2));

		textViews[0].setText(typesNames[typeIndex1]);
		int color = colors.getColor(typeIndex1, 0);
		textViews[0].setBackgroundColor(color);

		textViews[1].setVisibility(View.INVISIBLE);

		if (types[1] != 0)
		{
			if (animature == 4)
			{
				System.out.println("ERROR");
			}
			final int typeIndex2 = (int) Math.round(Math.log(types[1])
			/ Math.log(2));

			textViews[1].setText(typesNames[typeIndex2]);
			color = colors.getColor(typeIndex2, 0);
			textViews[1].setBackgroundColor(color);
			textViews[1].setVisibility(View.VISIBLE);
		}
		colors.recycle();
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