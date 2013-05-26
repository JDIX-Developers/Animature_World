package com.jdix.animature;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdix.animature.entities.Animature;

/**
 * @author Jordan Aranda Tejada
 */
public class AnimaxAnimatureActivity extends Activity {

	private int			animature;
	private TextView	animatureId;
	private TextView	animatureName;
	private ImageView	animatureImage;
	private TextView	animatureDescription;
	private TextView	animatureHeight;
	private TextView	animatureWeight;
	private TextView	animatureStrenght;
	private TextView	animatureDefense;
	private TextView	animatureSpeed;
	private TextView	animatureAgility;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animax_animature_view);

		// We get a reference to the interface controls
		animatureId = (TextView) findViewById(R.id.id_Animature_Animax_view);
		animatureName = (TextView) findViewById(R.id.name_Animature_Animax_view);
		animatureImage = (ImageView) findViewById(R.id.image_Animature_Animax_view);
		animatureDescription = (TextView) findViewById(R.id.desc_Animature_Animax_view);
		animatureHeight = (TextView) findViewById(R.id.height_Animature_Animax_view);
		animatureWeight = (TextView) findViewById(R.id.weight_Animature_Animax_view);
		animatureStrenght = (TextView) findViewById(R.id.strenght_Animature_Animax_view);
		animatureDefense = (TextView) findViewById(R.id.defense_Animature_Animax_view);
		animatureSpeed = (TextView) findViewById(R.id.velocity_Animature_Animax_view);
		animatureAgility = (TextView) findViewById(R.id.health_Animature_Animax_view);

		// We recover the information passed in the intent
		this.animature = this.getIntent().getExtras().getInt("animature_id");

		final DecimalFormat dF = new DecimalFormat("####.##");

		animatureId.setText("N.º " + getFormatedIdAnimature(this.animature));
		animatureName.setText(Animature.getName(this.animature, this));

		final int id = getResources().getIdentifier("f" + this.animature,
		"drawable", getPackageName());
		animatureImage.setImageDrawable(this.getResources().getDrawable(id));

		animatureDescription.setText(getResources().getStringArray(
		R.array.animature_descriptions)[this.animature - 1]);

		// ANIMATURE HEIGHT
		String height = getResources().getString(R.string.animax_height);
		height = height.replaceAll("%f",
		dF.format(Animature.getHeight(this.animature, this)));
		animatureHeight.setText(height);
		// ANIMATURE WEIGHT
		String weight = getResources().getString(R.string.animax_weight);
		weight = weight.replaceAll("%f",
		dF.format(Animature.getWeight(this.animature, this)));
		animatureWeight.setText(weight);
		// ANIMATURE STRENGHT
		String strenght = getResources().getString(R.string.animax_strength);
		strenght = strenght.replaceAll("%d",
		Animature.getQualities(animature, this)[Animature.STRENGTH] + "");
		animatureStrenght.setText(strenght);
		// ANIMATURE DEFENSE
		String defense = getResources().getString(R.string.animax_defense);
		defense = defense.replaceAll("%d",
		Animature.getQualities(animature, this)[Animature.DEFENSE] + "");
		animatureDefense.setText(defense);
		// ANIMATURE SPEED
		String speed = getResources().getString(R.string.animax_speed);
		speed = speed.replaceAll("%d",
		Animature.getQualities(animature, this)[Animature.SPEED] + "");
		animatureSpeed.setText(speed);
		// ANIMATURE AGILITY
		String agility = getResources().getString(R.string.animax_agility);
		agility = agility.replaceAll("%d",
		Animature.getQualities(animature, this)[Animature.AGILITY] + "");
		animatureAgility.setText(agility);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		return true;
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
