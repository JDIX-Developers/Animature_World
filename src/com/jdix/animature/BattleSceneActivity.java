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
import com.jdix.animature.entities.Capturable;

/**
 * @author Jordan Aranda Tejada
 */
public class BattleSceneActivity extends Activity {

	// Enemy's Animature Components
	private Capturable			enemy;
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

	private int					battleType;
	private final Capturable[]	animSel	= new Capturable[6];
	private int					animatureIndex;

	boolean						yourTurn;
	boolean						animatureFainted;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle_scene);

		loadBattleAnimatures();

		animatureIndex = 0;

		yourTurn = true;

		// We get a reference to the interface controls
		// Enemy's Animature Components
		enemy_animature_name = (TextView) findViewById(R.id.enemyAnimatureName);
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
				if (yourTurn)
				{
					attack(0);
				}
			}
		});
		attack2 = (Button) findViewById(R.id.btnAtack2);
		attacksBtns[1] = attack2;
		attack2.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				if (yourTurn)
				{
					attack(1);
				}
			}
		});
		attack3 = (Button) findViewById(R.id.btnAtack3);
		attacksBtns[2] = attack3;
		attack3.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				if (yourTurn)
				{
					attack(2);
				}
			}
		});
		attack4 = (Button) findViewById(R.id.btnAtack4);
		attacksBtns[3] = attack4;
		attack4.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				if (yourTurn)
				{
					attack(3);
				}
			}
		});

		exitBtn = (Button) findViewById(R.id.btnExitBattle);
		exitBtn.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				if (yourTurn)
				{
					finish();
				}
			}
		});

		loadEnemyAnimature();
		loadYourAnimature();
		changeAttacksBackground();
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.battle_scene, menu);
		return true;
	}

	private void attack(final int index)
	{
		/*
		 * if(animSel[animatureIndex].getCualitiesC(Attack.SPEED) >=
		 * enemy.getCualitiesC(Attack.SPEED)) yourTurn = true; if (yourTurn)
		 */
		if (animSel[animatureIndex].getAttackPP(index) > 0)
		{
			enemy.setHealthAct(enemy.getHealthAct() - 10);
			animatureFainted = enemy.getHealthAct() <= 0;
			enemy_animature_life.setProgress(enemy.getHealthAct());
			animSel[animatureIndex].reduceAttackPP(index);
			changeAttacksBackground();
			yourTurn = false;
		}

		while ( ! yourTurn && ! animatureFainted)
		{
			int attack = randomAttack();
			if (enemy.getAttackPP(attack) > 0)
			{
				animSel[animatureIndex].setHealthAct(animSel[animatureIndex]
				.getHealthAct() - 9);
				your_animature_life.setProgress(animSel[animatureIndex]
				.getHealthAct());
				animatureFainted = animSel[animatureIndex].getHealthAct() <= 0;
				enemy.reduceAttackPP(attack);
				yourTurn = true;
			}
			else
			{
				attack = randomAttack();
			}
		}

		if (animatureFainted)
		{
			finish();
		}
	}

	private void loadEnemyAnimature()
	{
		/*
		 * dataSource.open(); enemy.setCualitiesC(
		 * dataSource.readAnimatureColInt(enemy.getIdAnimature(), 6),
		 * Animature.SPEED); enemy.setCualitiesC(
		 * dataSource.readAnimatureColInt(enemy.getIdAnimature(), 7),
		 * Animature.DEFENSE); enemy.setCualitiesC(
		 * dataSource.readAnimatureColInt(enemy.getIdAnimature(), 8),
		 * Animature.AGILITY); enemy.setCualitiesC(
		 * dataSource.readAnimatureColInt(enemy.getIdAnimature(), 9),
		 * Animature.STRENGHT); enemy.setCualitiesC(
		 * dataSource.readAnimatureColInt(enemy.getIdAnimature(), 10),
		 * Animature.PRECISSION);
		 */

		/*
		 * for (int i = 1; i <= enemy.getLevel(); i++) { for (int j = 0; j < 5;
		 * j++) { enemy.setCualitiesC( enemy.getCualitiesC(j) +
		 * (enemy.getCualitiesC(j) / 3), j); } }
		 */

		/*
		 * enemy.setHealthMax(dataSource.readAnimatureColInt(
		 * enemy.getIdAnimature(), 11)); if (enemy.getLevel() > 1) { for (int i
		 * = 2; i <= enemy.getLevel(); i++) {
		 * enemy.setHealthMax(enemy.getHealthMax() + enemy.getHealthMax() / 3);
		 * } } dataSource.close();
		 */

		enemy_animature_name.setText(enemy.getNickname());
		enemy_animature_level.setText("Nvl " + enemy.getLevel());
		enemy_animature_life.setMax(100);// enemy.getHealthMax());
		enemy_animature_life.setProgress(enemy.getHealthAct());
		final int id = getResources().getIdentifier(
		"f" + enemy.getIdAnimature(), "drawable", getPackageName());
		enemy_animature_image.setImageDrawable(this.getResources().getDrawable(
		id));
	}

	private void loadYourAnimature()
	{
		/*
		 * dataSource.open();
		 * animSel[animatureIndex].setCualitiesC(dataSource.readAnimatureColInt(
		 * animSel[animatureIndex].getIdAnimature(), 6), Animature.SPEED);
		 * animSel[animatureIndex].setCualitiesC(dataSource.readAnimatureColInt(
		 * animSel[animatureIndex].getIdAnimature(), 7), Animature.DEFENSE);
		 * animSel[animatureIndex].setCualitiesC(dataSource.readAnimatureColInt(
		 * animSel[animatureIndex].getIdAnimature(), 8), Animature.AGILITY);
		 * animSel[animatureIndex].setCualitiesC(dataSource.readAnimatureColInt(
		 * animSel[animatureIndex].getIdAnimature(), 9), Animature.STRENGHT);
		 * animSel[animatureIndex].setCualitiesC(dataSource.readAnimatureColInt(
		 * animSel[animatureIndex].getIdAnimature(), 10), Animature.PRECISSION);
		 */

		/*
		 * for (int i = 1; i <= animSel[animatureIndex].getLevel(); i++) { for
		 * (int j = 0; j < 5; j++) { animSel[animatureIndex].setCualitiesC(
		 * animSel[animatureIndex].getCualitiesC(j) +
		 * (animSel[animatureIndex].getCualitiesC(j) / 3), j); } }
		 */

		/*
		 * animSel[animatureIndex].setHealthMax(dataSource.readAnimatureColInt(
		 * animSel[animatureIndex].getIdAnimature(), 11)); if
		 * (animSel[animatureIndex].getLevel() > 1) { for (int i = 2; i <=
		 * animSel[animatureIndex].getLevel(); i++) {
		 * animSel[animatureIndex].setHealthMax(animSel[animatureIndex]
		 * .getHealthMax() + animSel[animatureIndex].getHealthMax() / 3); } }
		 * dataSource.close();
		 */

		your_animature_name.setText(animSel[animatureIndex].getNickname());
		your_animature_level.setText("Nvl "
		+ animSel[animatureIndex].getLevel());
		your_animature_life.setMax(100);// animSel[animatureIndex].getHealthMax());
		your_animature_life.setProgress(animSel[animatureIndex].getHealthAct());
		your_animature_exp.setMax(animSel[animatureIndex].getExperience());
		your_animature_exp.setProgress(animSel[animatureIndex].getCurrentExp());
		final int id = getResources().getIdentifier(
		"b" + animSel[animatureIndex].getIdAnimature(), "drawable",
		getPackageName());
		your_animature_image.setImageDrawable(this.getResources().getDrawable(
		id));
	}

	private int randomAttack()
	{
		return (new Random()).nextInt(4);
	}

	private void loadBattleAnimatures()
	{
		/*
		 * dataSource.open(); dataSource.createCaptured(0, 2, 0, "BLASTOISE", 0,
		 * 0, 0, 0, 5, 0, 10, 0, 10, 0, 10, 40, 100, 200, 100, 0);
		 * dataSource.createCaptured(0, 1, 0, "CHARIZARD", 0, 0, 0, 0, 10, 0,
		 * 10, 0, 10, 0, 10, 40, 100, 200, 100, 0); animatureIndex = 0;
		 */

		/*
		 * animSel[animatureIndex] = dataSource.readCaptured(1); enemy =
		 * dataSource.readCaptured(2);
		 */

		/*
		 * for (int i = 0; i < 4; i++) { animSel[animatureIndex].setAttack(i,
		 * dataSource.readAttack(animSel[animatureIndex].getAttackN(i)));
		 * enemy.setAttack(i, dataSource.readAttack(enemy.getAttackN(i))); }
		 * dataSource.close();
		 */

		animSel[0] = new Capturable(0, 2, 0, "BLASTOISE", 0, 0, 0, 0, 5, 0, 10,
		0, 10, 0, 10, 40, 100, 200, 100, 0);

		// final Attack a = new Attack(0, "Placaje", 0, 20, 1, - 1, 35, 90);
		// final Attack a1 = new Attack(1, "Latigo", 0, 25, 0,
		// Animature.STRENGHT,
		// 0, 100);
		// final Attack a2 = new Attack(2, "Pistola agua", 2, 15, 1, 50, 1,
		// 100);
		// final Attack a3 = new Attack(3, "Gruñido", 0, 20, 0,
		// Animature.DEFENSE,
		// 0, 100);

		// final Attack[] aAttacks = new Attack[4];
		// aAttacks[0] = a;
		// aAttacks[1] = a1;
		// aAttacks[2] = a2;
		// aAttacks[3] = a3;
		//
		// animSel[0].setAttacks(aAttacks);

		enemy = new Capturable(0, 1, 0, "CHARIZARD", 0, 0, 0, 0, 10, 0, 10, 0,
		10, 0, 10, 40, 100, 200, 100, 0);

	}

	private void changeAttacksBackground()
	{
		for (int i = 0; i < this.attacksBtns.length; i++)
		{
			final Attack a = this.animSel[animatureIndex].getAttack(i);

			// Añadimos el nombre del ataque
			this.attacksBtns[i].setText(a.getName() + " ("
			+ this.animSel[animatureIndex].getAttackPP(i) + "/" + a.getMaxpp()
			+ ")");

			/*
			 * switch (a.getType_Attack()) { case 0:
			 * this.attacksBtns[i].setBackgroundColor(0xDACDB3); break; case 1:
			 * this.attacksBtns[i].setBackgroundColor(0xFF5930); break; case 2:
			 * this.attacksBtns[i].setBackgroundColor(0x308AFF); break; case 3:
			 * this.attacksBtns[i].setBackgroundColor(0x30FF35); break; case 4:
			 * this.attacksBtns[i].setBackgroundColor(0xFFF930); break; case 5:
			 * this.attacksBtns[i].setBackgroundColor(0x77FFF9); break; case 6:
			 * this.attacksBtns[i].setBackgroundColor(0xDF8A60); break; case 7:
			 * this.attacksBtns[i].setBackgroundColor(0xEF58C4); break; case 8:
			 * this.attacksBtns[i].setBackgroundColor(0xD59751); break; case 9:
			 * this.attacksBtns[i].setBackgroundColor(0xB2A9FA); break; case 10:
			 * this.attacksBtns[i].setBackgroundColor(0xAB6292); break; case 11:
			 * this.attacksBtns[i].setBackgroundColor(0x6ABF6C); break; case 12:
			 * this.attacksBtns[i].setBackgroundColor(0xA19989); break; case 13:
			 * this.attacksBtns[i].setBackgroundColor(0x9F9F9F); break; case 14:
			 * this.attacksBtns[i].setBackgroundColor(0xBA3343); break; case 15:
			 * this.attacksBtns[i].setBackgroundColor(0x6E6E6E); break; case 16:
			 * this.attacksBtns[i].setBackgroundColor(0xC2C2C2); break; }
			 */
		}
	}

	@Override
	public void onBackPressed()
	{
	}

	/*
	 * private boolean throwAnimatureBall(final Captured enemy, final int ball)
	 * { boolean captured = false; float capture; int effectiveness; final int m
	 * = enemy.getHealthMax(); final int h = enemy.getHealthAct(); final int r =
	 * enemy.getCaptureRange(); final int status = enemy.getStatus(); int s = 0;
	 * int ballEffect = 100; final int rand = (new Random()).nextInt(100); if
	 * (status > 0 && status < 4) { s = 5; } if (status > 3) { s = 10; } switch
	 * (ball) { case 1: ballEffect = 150; break; case 2: ballEffect = 200;
	 * break; case 3: captured = true; break; } capture = (((m * 4) - (h * 2) *
	 * r) / m) + s + 1; effectiveness = (int) capture * (ballEffect / 100); if
	 * (rand <= effectiveness) { captured = true; } return captured; }
	 */
}