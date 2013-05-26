package com.jdix.animature.utils;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jdix.animature.R;
import com.jdix.animature.entities.Animature;

/**
 * @author Jordan Aranda Tejada
 */
public class CapturedAdapter extends BaseAdapter {

	private final Context				context;
	private final ArrayList<Animature>	items;

	/**
	 * @param context The aplication context.
	 * @param items Items to show in list.
	 */
	public CapturedAdapter(final Context context,
	final ArrayList<Animature> items)
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
	public Object getItem(final int idCapturable)
	{
		return items.get(idCapturable);
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
			final LayoutInflater inf = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inf.inflate(R.layout.captured_row, null);
		}

		final LinearLayout animatureCapturedRow = (LinearLayout) v
		.findViewById(R.id.animature_captured_row);

		final TextView textViewCaptureNewAnimature = (TextView) v
		.findViewById(R.id.text_view_capture_new_animature);

		// Creamos un objeto Animature
		final Animature captured = items.get(position);

		animatureCapturedRow.setVisibility(View.GONE);
		textViewCaptureNewAnimature.setVisibility(View.VISIBLE);

		if (captured != null)
		{
			// NAME
			final TextView nameAnimature = (TextView) v
			.findViewById(R.id.text_view_captured_name);
			nameAnimature.setText(captured.getNickname());

			// LIFE PROGRESSBAR
			final ProgressBar lifeProgressBar = (ProgressBar) v
			.findViewById(R.id.progress_bar_captured_life);
			lifeProgressBar.setMax(captured.getMaxHealth(context));
			lifeProgressBar.setProgress(captured.getHealth());
			// LEVEL
			final TextView levelAnimature = (TextView) v
			.findViewById(R.id.text_view_captured_level);
			levelAnimature.setText("Nv " + captured.getLevel());
			// LIFE TEXTVIEW
			final TextView lifeTextView = (TextView) v
			.findViewById(R.id.text_view_captured_ps_life);
			lifeTextView.setText(captured.getHealth() + " / "
			+ captured.getMaxHealth(context));

			textViewCaptureNewAnimature.setVisibility(View.GONE);
			animatureCapturedRow.setVisibility(View.VISIBLE);
		}

		// Retornamos la vista
		return v;
	}
}