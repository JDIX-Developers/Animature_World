package com.jdix.animature;

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
	private TextView	animatureVelocity;
	private TextView	animatureHealth;

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
		animatureVelocity = (TextView) findViewById(R.id.velocity_Animature_Animax_view);
		animatureHealth = (TextView) findViewById(R.id.health_Animature_Animax_view);

		// We recover the information passed in the intent
		this.animature = this.getIntent().getIntExtra("animature_id", 1);

		// ADD ANIMATURE DATA TODO Quality names from strings
		animatureId.setText("N.º " + getFormatedIdAnimature(this.animature));
		animatureName.setText(Animature.getName(this.animature, this));
		final int id = getResources().getIdentifier("f" + this.animature,
		"drawable", getPackageName());
		animatureImage.setImageDrawable(this.getResources().getDrawable(id));
		animatureDescription.setText(getResources().getStringArray(
		R.array.animature_names)[this.animature - 1]);
		animatureHeight.setText("Altura: "
		+ Animature.getHeight(this.animature, this) + " m");
		animatureWeight.setText("Peso: "
		+ Animature.getWeight(this.animature, this) + " kg");
		animatureStrenght.setText("Fuerza: "
		+ Animature.getQualities(this.animature, this)[Animature.STRENGTH]);
		animatureDefense.setText("Defensa: "
		+ Animature.getQualities(this.animature, this)[Animature.DEFENSE]);
		animatureVelocity.setText("Velocidad: "
		+ Animature.getQualities(this.animature, this)[Animature.SPEED]);
		animatureHealth.setText("Agilidad: "
		+ Animature.getQualities(this.animature, this)[Animature.AGILITY]);
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
