package com.jdix.animature;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdix.animature.entities.Animature;
import com.jdix.animature.entities.Player;

/**
 * @author Jordan Aranda Tejada
 */
public class PlayerCapturedInfoViewActivity extends Activity {

	private TextView	capturedDataLevelTextView;
	private TextView	capturedDataNameTextView;
	private ImageView	capturedDataImageView;
	private TextView	capturedDataAnimatureId;
	private TextView	capturedDataAnimatureName;
	private TextView	capturedType1TextView;
	private TextView	capturedType2TextView;
	private TextView	captureDataDescription;
	private TypedArray	colors;
	private String[]	typesNames;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player_captured_info_view);

		this.colors = getResources().obtainTypedArray(
		R.array.animature_types_colors);
		this.typesNames = getResources().getStringArray(
		R.array.animature_types_names);

		// We recover the information passed in the intent
		final int animatureIndex = this.getIntent().getExtras()
		.getInt("animatureIndex");
		;
		final Animature animature = Player.getInstance().getActiveAnimatures()[animatureIndex];

		// We get a reference to the interface controls
		capturedDataLevelTextView = (TextView) findViewById(R.id.captured_data_level_text_view);
		capturedDataNameTextView = (TextView) findViewById(R.id.captured_data_name_text_view);
		capturedDataImageView = (ImageView) findViewById(R.id.captured_data_image_view);
		capturedDataAnimatureId = (TextView) findViewById(R.id.captured_data_animature_number);
		capturedDataAnimatureName = (TextView) findViewById(R.id.captured_data_animature_name);
		capturedType1TextView = (TextView) findViewById(R.id.captured_data_type1);
		capturedType2TextView = (TextView) findViewById(R.id.captured_data_type2);
		final TextView[] typesTextViews = {capturedType1TextView,
		capturedType2TextView};
		captureDataDescription = (TextView) findViewById(R.id.captured_data_description);

		capturedDataLevelTextView.setText("Nv " + animature.getLevel());
		capturedDataNameTextView.setText(animature.getNickname());

		final int id = getResources().getIdentifier(
		"f" + animature.getAnimature(), "drawable", getPackageName());
		capturedDataImageView.setImageDrawable(this.getResources().getDrawable(
		id));

		capturedDataAnimatureId.setText(getFormatedIdAnimature(animature
		.getAnimature()));

		capturedDataAnimatureName.setText(Animature.getName(
		animature.getAnimature(), this));

		// captureDataDescription.setText("Description");

		modifyTypeTextView(typesTextViews, animature.getAnimature());
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
	final int animature)
	{
		final int[] types = Animature.getTypes(animature, this);

		final int typeIndex1 = (int) Math.round(Math.log(types[0])
		/ Math.log(2));

		textViews[0].setText(typesNames[typeIndex1]);
		int color = colors.getColor(typeIndex1, 0);
		textViews[0].setBackgroundColor(color);

		textViews[1].setVisibility(View.GONE);

		if (types[1] != 0)
		{
			final int typeIndex2 = (int) Math.round(Math.log(types[1])
			/ Math.log(2));

			textViews[1].setText(typesNames[typeIndex2]);
			color = colors.getColor(typeIndex2, 0);
			textViews[1].setBackgroundColor(color);
			textViews[1].setVisibility(View.VISIBLE);
		}
		colors.recycle();
	}
}
