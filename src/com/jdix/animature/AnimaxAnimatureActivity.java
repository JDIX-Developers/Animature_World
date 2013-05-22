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

	private Animature	animature;
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
		this.animature = (Animature) this.getIntent().getExtras()
		.getSerializable("Animature");

		// ADD ANIMATURE DATA
		animatureId.setText("N.º "
		+ getFormatedIdAnimature(this.animature.getId()));
		animatureName.setText(this.animature.getName());
		final int id = getResources().getIdentifier(
		"f" + this.animature.getId(), "drawable", getPackageName());
		animatureImage.setImageDrawable(this.getResources().getDrawable(id));
		animatureDescription.setText(this.animature.getDescription());
		animatureHeight.setText("Altura: " + this.animature.getHeight() + " m");
		animatureWeight.setText("Peso: " + this.animature.getWeight() + " kg");
		animatureStrenght.setText("Fuerza: " + this.animature.getStrenght());
		animatureDefense.setText("Defensa: " + this.animature.getDefense());
		animatureVelocity.setText("Velocidad: " + this.animature.getVelocity());
		animatureHealth.setText("Vida: " + this.animature.getHealth());
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
