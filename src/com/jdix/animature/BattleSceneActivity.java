package com.jdix.animature;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jdix.animature.entities.Attack;
import com.jdix.animature.entities.Captured;

public class BattleSceneActivity extends Activity {

	// Enemy's Animature Components
	private Captured			enemy;
	private TextView			enemy_animature_name;
	private TextView			enemy_animature_level;
	private ProgressBar			enemy_animature_life;
	private ImageView			enemy_animature_image;

	// Your Animature Components
	private TextView			your_animature_name;
	private TextView			your_animature_level;
	private ProgressBar			your_animature_life;
	private ProgressBar			your_animature_exp;
	private ImageView			your_animature_image;

	// Atacks Components
	private Button[]			attacksBtns;
	private Button				attack1;
	private Button				attack2;
	private Button				attack3;
	private Button				attack4;

	// Exit Button
	private Button				exitBtn;

	private final Captured[]	animSel	= new Captured[6];
	private int					animatureIndex;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle_scene);

		loadAnimSel();
		animatureIndex = 0;

		// We get a reference to the interface controls
		// Enemy's Animature Components
		enemy_animature_name = (TextView) findViewById(R.id.enemy_animature_name);
		enemy_animature_level = (TextView) findViewById(R.id.enemyAnimatureLevel);
		enemy_animature_life = (ProgressBar) findViewById(R.id.enemyAnimatureLife);
		enemy_animature_image = (ImageView) findViewById(R.id.enemyAnimatureImage);

		// Your Animature Components
		your_animature_name = (TextView) findViewById(R.id.your_animature_name);
		your_animature_level = (TextView) findViewById(R.id.your_animature_level);
		your_animature_life = (ProgressBar) findViewById(R.id.your_animature_life);
		your_animature_exp = (ProgressBar) findViewById(R.id.your_animature_exp);
		your_animature_image = (ImageView) findViewById(R.id.your_animature_image);

		// Load attack buttons
		attacksBtns = new Button[4];
		attack1 = (Button) findViewById(R.id.btnAtack1);
		attacksBtns[0] = attack1;
		attack1.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{

			}
		});
		attack2 = (Button) findViewById(R.id.btnAtack2);
		attacksBtns[1] = attack2;
		attack2.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{

			}
		});
		attack3 = (Button) findViewById(R.id.btnAtack3);
		attacksBtns[2] = attack3;
		attack3.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{

			}
		});
		attack4 = (Button) findViewById(R.id.btnAtack4);
		attacksBtns[3] = attack4;
		attack4.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{

			}
		});

		exitBtn = (Button) findViewById(R.id.btnExitBattle);
		exitBtn.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				finish();
			}
		});

		// loadEnemyAnimature(enemy);
		loadYourAnimature();
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.battle_scene, menu);
		return true;
	}

	private void loadEnemyAnimature(final Captured captured)
	{
		enemy_animature_name.setText(captured.getNickname());
		enemy_animature_level.setText("Nvl " + captured.getLevel());
		enemy_animature_life.setMax(captured.getHealthMax());
		final int id = getResources().getIdentifier(
		"f" + captured.getIdAnimature(), "drawable", getPackageName());
		enemy_animature_image.setImageDrawable(this.getResources().getDrawable(
		id));
	}

	private void loadYourAnimature()
	{
		your_animature_name.setText(animSel[animatureIndex].getNickname());
		your_animature_level.setText("Nvl "
		+ animSel[animatureIndex].getLevel());
		your_animature_life.setMax(animSel[animatureIndex].getHealthMax());
		your_animature_life.setProgress(animSel[animatureIndex].getHealthAct());
		your_animature_exp.setMax(animSel[animatureIndex].getExperience());
		your_animature_exp
		.setProgress(animSel[animatureIndex].getCurrent_exp());
		final int id = getResources().getIdentifier(
		"b" + animSel[animatureIndex].getIdAnimature(), "drawable",
		getPackageName());
		your_animature_image.setImageDrawable(this.getResources().getDrawable(
		id));
	}

	private int randomAttack()
	{
		final Random r = new Random();
		final int rand = r.nextInt(4);
		return rand;
	}

	private void loadAnimSel()
	{
		animSel[0] = new Captured(0, 2, 0, "BLASTOISE", 0, 0, 0, 0, 10, 0, 10,
		0, 10, 0, 10, 40, 100, 200, 100, 0);

	}

	private void changeAttacksBackground()
	{
		for (int i = 0; i < this.attacksBtns.length; i++)
		{
			final Attack a = this.animSel[animatureIndex].getAttack(i);

			// Añadimos el nombre del ataque
			this.attacksBtns[i].setText(a.getName_Attack() + " ("
			+ this.animSel[animatureIndex].getAttackPP(i) + "/" + a.getMax_pp()
			+ ")");

			switch (a.getType_Attack())
			{
				case 0:
					this.attacksBtns[i].setBackgroundColor(0xDACDB3);
				break;
				case 1:
					this.attacksBtns[i].setBackgroundColor(0xFF5930);
				break;
				case 2:
					this.attacksBtns[i].setBackgroundColor(0x308AFF);
				break;
				case 3:
					this.attacksBtns[i].setBackgroundColor(0x30FF35);
				break;
				case 4:
					this.attacksBtns[i].setBackgroundColor(0xFFF930);
				break;
				case 5:
					this.attacksBtns[i].setBackgroundColor(0x77FFF9);
				break;
				case 6:
					this.attacksBtns[i].setBackgroundColor(0xDF8A60);
				break;
				case 7:
					this.attacksBtns[i].setBackgroundColor(0xEF58C4);
				break;
				case 8:
					this.attacksBtns[i].setBackgroundColor(0xD59751);
				break;
				case 9:
					this.attacksBtns[i].setBackgroundColor(0xB2A9FA);
				break;
				case 10:
					this.attacksBtns[i].setBackgroundColor(0xAB6292);
				break;
				case 11:
					this.attacksBtns[i].setBackgroundColor(0x6ABF6C);
				break;
				case 12:
					this.attacksBtns[i].setBackgroundColor(0xA19989);
				break;
				case 13:
					this.attacksBtns[i].setBackgroundColor(0x9F9F9F);
				break;
				case 14:
					this.attacksBtns[i].setBackgroundColor(0xBA3343);
				break;
				case 15:
					this.attacksBtns[i].setBackgroundColor(0x6E6E6E);
				break;
				case 16:
					this.attacksBtns[i].setBackgroundColor(0xC2C2C2);
				break;
			}
		}
	}
}