package com.jdix.animature;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jdix.animature.entities.Animature;

/**
 * @author Jordan Aranda Tejada
 */
public class BattleActivity extends Activity {

	private Animature		wildAnimature;

	private LinearLayout	enemyDataLayout;
	private TextView		enemyNameTextView;
	private TextView		enemyLevelTextView;
	private ImageView		enemyCapturedImageView;
	private ProgressBar		enemyLifeProgressBar;
	private ImageView		enemyImageView;

	private TextView		playerAnimatureNameTextView;
	private TextView		playerAnimatureLevelTextView;
	private ProgressBar		playerAnimatureLifeProgressBar;
	private TextView		playerAnimatureCurrentPSTextView;
	private ProgressBar		playerAnimatureExperienceProgressBar;
	private ImageView		playerAnimatureImageView;

	private TextView		playerTextView;

	private LinearLayout	playerBattleOptionsLayout;
	private TextView		playerBattleOptionsHeader;
	private Button			btnFightBattleActivity;
	private Button			btnAnimatureBattleActivity;
	private Button			btnBagBattleActivity;
	private Button			btnEscapeBattleActivity;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle);

		// We recover the information passed in the intent
		wildAnimature = (Animature) this.getIntent().getSerializableExtra(
		"wild_capturable");

		// We get a reference to the interface controls
		enemyDataLayout = (LinearLayout) findViewById(R.id.enemy_animature_data_layout);
		enemyNameTextView = (TextView) findViewById(R.id.enemy_animature_name);
		enemyLevelTextView = (TextView) findViewById(R.id.enemy_level_textView);
		enemyCapturedImageView = (ImageView) findViewById(R.id.enemy_captured_image);
		enemyLifeProgressBar = (ProgressBar) findViewById(R.id.enemy_live_progressBar);
		enemyImageView = (ImageView) findViewById(R.id.enemy_imageView);

		playerAnimatureNameTextView = (TextView) findViewById(R.id.player_animature_name_textView);
		playerAnimatureLevelTextView = (TextView) findViewById(R.id.player_animatura_level_textView);
		playerAnimatureLifeProgressBar = (ProgressBar) findViewById(R.id.player_animature_live_progressBar);
		playerAnimatureCurrentPSTextView = (TextView) findViewById(R.id.player_animature_ps_textView);
		playerAnimatureExperienceProgressBar = (ProgressBar) findViewById(R.id.player_animature_experience_progressBar);
		playerAnimatureImageView = (ImageView) findViewById(R.id.player_animature_imageView);

		playerTextView = (TextView) findViewById(R.id.textViewBattleActivity);

		playerBattleOptionsLayout = (LinearLayout) findViewById(R.id.player_battle_options_layout);
		playerBattleOptionsHeader = (TextView) findViewById(R.id.battle_options_header);
		btnFightBattleActivity = (Button) findViewById(R.id.btnFightBattleActivity);
		btnAnimatureBattleActivity = (Button) findViewById(R.id.btnAnimatureBattleActivity);
		btnBagBattleActivity = (Button) findViewById(R.id.btnBagBattleActivity);
		btnEscapeBattleActivity = (Button) findViewById(R.id.btnEscapeBattleActivity);

		// TODO References
	}
}
