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
import com.jdix.animature.entities.Animature;

/**
 * @author Jordan Aranda Tejada
 */
public class AdapterAnimatures extends BaseAdapter {

	protected Activity				activity;
	protected ArrayList<Animature>	items;

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

		// Asociamos el layout de la lista que hemos creado
		if (convertView == null)
		{
			final LayoutInflater inf = (LayoutInflater) activity
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inf.inflate(R.layout.animax_row, null);
		}

		// Creamos un objeto Animature
		final Animature animature = items.get(position);
		// ID
		final TextView idAnimature = (TextView) v
		.findViewById(R.id.animax_row_animature_id);
		idAnimature.setText(animature.getId_Animature() + "");
		// NAME
		final TextView nameAnimature = (TextView) v
		.findViewById(R.id.animax_row_animature_name);
		nameAnimature.setText(animature.getName());
		// TYPE 1
		final TextView type1Animature = (TextView) v
		.findViewById(R.id.animax_row_type1);
		// modifyTypeTextView(type1Animature, animature.getType());
		// TYPE 2
		final TextView type2Animature = (TextView) v
		.findViewById(R.id.animax_row_type2);
		// modifyTypeTextView(type2Animature, animature.getType2());

		// Retornamos la vista
		return v;
	}

	private void modifyTypeTextView(final TextView textView, final int type)
	{
		switch (type)
		{
			case - 1:
			// INVISIBLE
			break;
			case 0:
				textView.setText("NORMAL");
				textView.setBackgroundColor(0x7f050003);
			break;
			case 1:
				textView.setText("FUEGO");
				textView.setBackgroundColor(0xFF5930);
			break;
			case 2:
				textView.setText("AGUA");
				textView.setBackgroundColor(0x308AFF);
			break;
			case 3:
				textView.setText("PLANTA");
				textView.setBackgroundColor(0x1E7F19);
			break;
			case 4:
				textView.setText("ELÉCTRICO");
				textView.setBackgroundColor(0xFFF930);
			break;
			case 5:
				textView.setText("HIELO");
				textView.setBackgroundColor(0x77FFF9);
			break;
			case 6:
				textView.setText("LUCHA");
				textView.setBackgroundColor(0xDF8A60);
			break;
			case 7:
				textView.setText("VENENO");
				textView.setBackgroundColor(0xEF58C4);
			break;
			case 8:
				textView.setText("TIERRA");
				textView.setBackgroundColor(0xD59751);
			break;
			case 9:
				textView.setText("VOLADOR");
				textView.setBackgroundColor(0xB2A9FA);
			break;
			case 10:
				textView.setText("PSÍQUICO");
				textView.setBackgroundColor(0xAB6292);
			break;
			case 11:
				textView.setText("BICHO");
				textView.setBackgroundColor(0x6ABF6C);
			break;
			case 12:
				textView.setText("ROCA");
				textView.setBackgroundColor(0xA19989);
			break;
			case 13:
				textView.setText("FANTASMA");
				textView.setBackgroundColor(0x9F9F9F);
			break;
			case 14:
				textView.setText("DRAGÓN");
				textView.setBackgroundColor(0xBA3343);
			break;
			case 15:
				textView.setText("SINIESTRO");
				textView.setBackgroundColor(0x6E6E6E);
			break;
			case 16:
				textView.setText("ACERO");
				textView.setBackgroundColor(0xC2C2C2);
			break;
		}

	}

	private Object getResources()
	{
		// TODO Auto-generated method stub
		return null;
	}

}