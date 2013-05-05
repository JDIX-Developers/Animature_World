package com.jdix.animature;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jdix.animature.entities.Captured;

public class BattleSceneActivity extends Activity {

	private LinearLayout		enemy_information_layout;
	private TextView			enemy_animature_name;
	private TextView			enemy_animature_level;
	private ProgressBar			enemy_animature_live;

	private int					battleType;							// If==0->Wild_Animature____if==1->Trainer's_Animature
	private int					attack;
	private boolean				finishTurn			= false;
	private final boolean		inMain				= true;
	private final boolean		inFight				= false;
	private final boolean		inItem				= false;
	private final boolean		inAnimature			= false;
	private final boolean		inRun				= false;
	private final boolean		playerWin			= false;
	private final boolean		enemyWin			= false;
	private final boolean		animatureFainted	= false;

	private final Captured[]	animSel				= new Captured[6];
	private final Captured		enemy				= new Captured();

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle_scene);

		// We get a reference to the interface controls
		enemy_information_layout = (LinearLayout) findViewById(R.id.enemy_information_layout);
		enemy_animature_name = (TextView) findViewById(R.id.enemy_animature_name);
		enemy_animature_level = (TextView) findViewById(R.id.enemy_animature_level);
		enemy_animature_live = (ProgressBar) findViewById(R.id.enemy_live_progressbar);

		// Enemy battle example
		int attack = randomAttack();
		while ( ! finishTurn)
		{
			if (enemy.getAttackPP(attack) > 0)
			{
				animSel[0] = enemy.getAttack(attack).getCapturedDamage(
				animSel[0], enemy);
				enemy.reduceAttackPP(attack);
				finishTurn = true;
			}
			else
			{
				attack = randomAttack();
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.battle_scene, menu);
		return true;
	}

	public void showEnemyAnimature(final Captured captured)
	{
		enemy_information_layout.setVisibility(View.VISIBLE);
		enemy_animature_name.setText(captured.getName());
		enemy_animature_level.setText("Nvl " + captured.getLevel());
		enemy_animature_live.setMax(captured.getHealth());
	}

	public int randomAttack()
	{
		final Random r = new Random();
		final int rand = r.nextInt(4);
		return rand;
	}
}