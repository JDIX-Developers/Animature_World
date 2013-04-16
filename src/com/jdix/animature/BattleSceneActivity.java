package com.jdix.animature;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jdix.animature.entities.Animature;

public class BattleSceneActivity extends Activity {

	private int				battleType;
	private LinearLayout	enemy_information_layout;
	private TextView		enemy_animature_name;
	private TextView		enemy_animature_level;
	private ProgressBar		enemy_animature_live;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle_scene);

		// We get a reference to the interface controls
		// enemy_information_layout = (LinearLayout)findViewById(R.id.);
		// enemy_animature_name = (TextView)findViewById(R.id.);
		// enemy_animature_level = (TextView)findViewById(R.id.);
		// enemy_animature_live = (ProgressBar) findViewById(R.id.);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.battle_scene, menu);
		return true;
	}

	private void changeEnemyAnimature(Animature animature)
	{
		enemy_information_layout.setVisibility(View.VISIBLE);
		enemy_animature_name.setText(animature.getName());
		enemy_animature_level.setText("Nvl " + animature.getCurrent_level());
		enemy_animature_live.setMax(animature.getHealth());
	}
}