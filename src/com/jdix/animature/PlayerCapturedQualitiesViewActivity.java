package com.jdix.animature;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jdix.animature.entities.Animature;
import com.jdix.animature.entities.Player;

/**
 * @author Jordan Aranda Tejada
 */
public class PlayerCapturedQualitiesViewActivity extends Activity {

	private TextView	capturedDataLevelTextView;
	private TextView	capturedDataNameTextView;
	private ProgressBar	capturedDataPsProgressBar;
	private TextView	capturedDataAttackTextView;
	private TextView	capturedDataDefenseTextView;
	private TextView	capturedDataSpeedTextView;
	private TextView	capturedDataPrecisionTextView;
	private TextView	capturedDataAgilityTextView;
	private TextView	captureDataExperienceTextView;
	private ProgressBar	capturedDataExperienceProgressBar;
	private TextView	capturedDataPsTextView;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_captured_qualities_view);

		// We recover the information passed in the intent
		final int animatureIndex = this.getIntent().getExtras()
		.getInt("animatureIndex");

		final Animature animature = Player.getInstance().getActiveAnimatures()[animatureIndex];

		// We get a reference to the interface controls
		capturedDataLevelTextView = (TextView) findViewById(R.id.captured_data_level_text_view2);
		capturedDataNameTextView = (TextView) findViewById(R.id.captured_data_name_text_view2);

		capturedDataPsProgressBar = (ProgressBar) findViewById(R.id.captured_data_ps_progressBar);
		capturedDataPsTextView = (TextView) findViewById(R.id.captured_data_ps_text_view);

		capturedDataAttackTextView = (TextView) findViewById(R.id.capture_data_attack_text_view);
		capturedDataDefenseTextView = (TextView) findViewById(R.id.capture_data_defense_text_view);
		capturedDataSpeedTextView = (TextView) findViewById(R.id.capture_data_speed_text_view);
		capturedDataAgilityTextView = (TextView) findViewById(R.id.capture_data_agility_text_view);
		capturedDataPrecisionTextView = (TextView) findViewById(R.id.capture_data_precision_text_view);

		capturedDataExperienceProgressBar = (ProgressBar) findViewById(R.id.captured_data_experience_progressBar);
		captureDataExperienceTextView = (TextView) findViewById(R.id.capture_data_experience_text_view);

		capturedDataLevelTextView.setText("Nv " + animature.getLevel());
		capturedDataNameTextView.setText(animature.getNickname());

		capturedDataPsProgressBar.setMax(animature.getMaxHealth(this));
		capturedDataPsProgressBar.setProgress(animature.getHealth());
		capturedDataPsTextView.setText(animature.getHealth() + " / "
		+ animature.getMaxHealth(this));

		capturedDataAttackTextView.setText(""
		+ animature.getQualities(this)[Animature.STRENGTH]);
		capturedDataDefenseTextView.setText(""
		+ animature.getQualities(this)[Animature.DEFENSE]);
		capturedDataSpeedTextView.setText(""
		+ animature.getQualities(this)[Animature.SPEED]);
		capturedDataAgilityTextView.setText(""
		+ animature.getQualities(this)[Animature.AGILITY]);
		capturedDataPrecisionTextView.setText(""
		+ animature.getQualities(this)[Animature.PRECISION]);

		capturedDataExperienceProgressBar.setMax(animature
		.getMaxExperience(this));
		capturedDataExperienceProgressBar
		.setProgress(animature.getCurrentExp());
		captureDataExperienceTextView.setText(animature.getCurrentExp() + " / "
		+ animature.getMaxExperience(this));
	}
}
