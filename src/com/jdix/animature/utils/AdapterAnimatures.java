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

		// Retornamos la vista
		return v;
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
		if (animature.isNormal())
		{
			textViews[cont].setText("NORMAL");
			textViews[cont].setBackgroundColor(activity.getResources()
			.getColor(R.color.color_normal));
			cont++;
		}
		if (animature.isFire())
		{
			textViews[cont].setText("FUEGO");
			textViews[cont].setBackgroundColor(activity.getResources()
			.getColor(R.color.color_fire));
			cont++;
		}
		if (animature.isWater())
		{
			textViews[cont].setText("AGUA");
			textViews[cont].setBackgroundColor(activity.getResources()
			.getColor(R.color.color_water));
			cont++;
		}
		if (animature.isGrass())
		{
			textViews[cont].setText("PLANTA");
			textViews[cont].setBackgroundColor(activity.getResources()
			.getColor(R.color.color_grass));
			cont++;
		}
		if (animature.isElectric())
		{
			textViews[cont].setText("ELÉCTRICO");
			textViews[cont].setBackgroundColor(activity.getResources()
			.getColor(R.color.color_electric));
			cont++;
		}
		if (animature.isIce())
		{
			textViews[cont].setText("HIELO");
			textViews[cont].setBackgroundColor(activity.getResources()
			.getColor(R.color.color_ice));
			cont++;
		}
		if (animature.isFighting())
		{
			textViews[cont].setText("LUCHA");
			textViews[cont].setBackgroundColor(activity.getResources()
			.getColor(R.color.color_fighting));
			cont++;
		}
		if (animature.isPoison())
		{
			textViews[cont].setText("VENENO");
			textViews[cont].setBackgroundColor(activity.getResources()
			.getColor(R.color.color_poison));
			cont++;
		}
		if (animature.isGround())
		{
			textViews[cont].setText("TIERRA");
			textViews[cont].setBackgroundColor(activity.getResources()
			.getColor(R.color.color_ground));
			cont++;
		}
		if (animature.isFlying())
		{
			textViews[cont].setText("VOLADOR");
			textViews[cont].setBackgroundColor(activity.getResources()
			.getColor(R.color.color_flying));
			cont++;
		}
		if (animature.isPsychic())
		{
			textViews[cont].setText("PSÍQUICO");
			textViews[cont].setBackgroundColor(activity.getResources()
			.getColor(R.color.color_psychic));
			cont++;
		}
		if (animature.isBug())
		{
			textViews[cont].setText("BICHO");
			textViews[cont].setBackgroundColor(activity.getResources()
			.getColor(R.color.color_bug));
			cont++;
		}
		if (animature.isRock())
		{
			textViews[cont].setText("ROCA");
			textViews[cont].setBackgroundColor(activity.getResources()
			.getColor(R.color.color_rock));
			cont++;
		}
		if (animature.isGhost())
		{
			textViews[cont].setText("FANTASMA");
			textViews[cont].setBackgroundColor(activity.getResources()
			.getColor(R.color.color_ghost));
			cont++;
		}
		if (animature.isDragon())
		{
			textViews[cont].setText("DRAGÓN");
			textViews[cont].setBackgroundColor(activity.getResources()
			.getColor(R.color.color_dragon));
			cont++;
		}
		if (animature.isDark())
		{
			textViews[cont].setText("SINIESTRO");
			textViews[cont].setBackgroundColor(activity.getResources()
			.getColor(R.color.color_dark));
			cont++;
		}
		if (animature.isSteel())
		{
			textViews[cont].setText("ACERO");
			textViews[cont].setBackgroundColor(activity.getResources()
			.getColor(R.color.color_steel));
			cont++;
		}

	}
}