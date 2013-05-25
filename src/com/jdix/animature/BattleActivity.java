package com.jdix.animature;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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

	private LinearLayout	playerAnimatureDataLayout;
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

	private LinearLayout	playerAnimatureAttacksLayout;
	private Button			btnAttack1;
	private Button			btnAttack2;
	private Button			btnAttack3;
	private Button			btnAttack4;

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

		playerAnimatureDataLayout = (LinearLayout) findViewById(R.id.player_animature_data_layout);
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

		playerAnimatureAttacksLayout = (LinearLayout) findViewById(R.id.player_animature_attacks_layout);
		btnAttack1 = (Button) findViewById(R.id.btn_attack1);
		btnAttack2 = (Button) findViewById(R.id.btn_attack2);
		btnAttack3 = (Button) findViewById(R.id.btn_attack3);
		btnAttack4 = (Button) findViewById(R.id.btn_attack4);

		// playerAnimatureImageView.setImageDrawable(this.getResources()
		// .getDrawable(R.drawable.player_battle_image));

		enemyLifeProgressBar.setMax(wildAnimature.getHealthMax());
		enemyLifeProgressBar.setProgress(wildAnimature.getHealthMax());

		showPlayerTextView("¡Un Pidgey salvaje!", true);
	}

	private void showPlayerTextView(final String text, final boolean clickable)
	{
		playerTextView.setText(text);
		playerTextView.setVisibility(View.VISIBLE);
		if (clickable)
		{
			playerTextView.setCompoundDrawablesWithIntrinsicBounds(null, null,
			getResources().getDrawable(R.drawable.flecha), null);
			playerTextView.setClickable(true);
		}
		else
		{
			playerTextView.setCompoundDrawables(null, null, null, null);
			playerTextView.setClickable(false);
		}
	}
}
