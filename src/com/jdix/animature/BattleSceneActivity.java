package com.jdix.animature;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jdix.animature.entities.Attack;
import com.jdix.animature.entities.Captured;
import com.jdix.animature.utils.AnimatureDataSource;
import com.jdix.animature.utils.AttackDataSource;
import com.jdix.animature.utils.CapturedDataSource;

public class BattleSceneActivity extends Activity {

	// Enemy's Animature Components
	private Captured					enemy;
	private TextView					enemy_animature_name;
	private TextView					enemy_animature_level;
	private ProgressBar					enemy_animature_life;
	private ImageView					enemy_animature_image;

	// Your Animature Components
	private TextView					your_animature_name;
	private TextView					your_animature_level;
	private ProgressBar					your_animature_life;
	private ProgressBar					your_animature_exp;
	private ImageView					your_animature_image;

	// Atacks Components
	private Button[]					attacksBtns;
	private Button						attack1;
	private Button						attack2;
	private Button						attack3;
	private Button						attack4;

	// Exit Button
	private Button						exitBtn;

	public static final int				SPEED				= 0;
	public static final int				DEFENSE				= 1;
	public static final int				AGILITY				= 2;
	public static final int				STRENGHT			= 3;
	public static final int				PRECISSION			= 4;

	private int							battleType;							// If==0->Wild_Animature____if==1->Trainer's_Animature
	private int							attackChoice;
	private boolean						turn				= false;
	private final boolean				playerWin			= false;
	private final boolean				enemyWin			= false;
	private final boolean				animatureFainted	= false;

	private final Captured[]			animSel				= new Captured[6];

	private int							animatureIndex;

	private final CapturedDataSource	cds					= new CapturedDataSource(
															this,
															"AnimatureWorldDB",
															null, 1);
	private final AttackDataSource		ads					= new AttackDataSource(
															this,
															"AnimatureWorldDB",
															null, 1);
	private final AnimatureDataSource	ands				= new AnimatureDataSource(
															this,
															"AnimatureWorldDB",
															null, 1);

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle_scene);

		attacksBtns = new Button[4];

		ands.open();
		ands.createAnimature("Blastoise", 200, 500, 2, - 1, 30, 60, 40, 50, 50,
		20, - 1, 200);
		ands.createAnimature("Charizard", 180, 300, 1, - 1, 50, 40, 50, 45, 45,
		25, - 1, 200);
		ands.close();

		ads.open();
		ads.createAttack("Placaje", 0, 35, 1, - 1, 35, 95);
		ads.createAttack("Gruñido", 0, 40, 0, DEFENSE, - 1, 100);
		ads.createAttack("Latigo", 0, 40, 0, STRENGHT, - 1, 100);
		ads.createAttack("Pistola agua", 2, 25, 1, - 1, 40, 100);
		ads.createAttack("Ascuas", 1, 25, 1, - 1, 40, 100);
		ads.close();

		cds.open();
		cds.createCaptured(1, - 1, "Pepe", 0, 0, 0, 1, 32, 2, 37, 4, 24, 3, 40,
		44, 5, 50, 250, 0);
		cds.createCaptured(2, - 1, "Juan", 1, 0, 0, 1, 32, 2, 37, 5, 24, 3, 40,
		48, 5, 60, 250, 0);
		cds.close();

		animatureIndex = 0;
		animSel[animatureIndex] = cds.readCaptured(1);
		enemy = cds.readCaptured(2);
		ads.open();
		for (int i = 0; i < 4; i++)
		{
			animSel[animatureIndex].setAttack(i,
			ads.readAttack(animSel[animatureIndex].getAttackN(i)));
			enemy.setAttack(i, enemy.getAttack(i));
		}
		ads.close();

		ands.open();
		animSel[animatureIndex].setCualitiesC(
		ands.readAnimatureColInt(animSel[animatureIndex].getIdAnimature(), 6),
		SPEED);
		animSel[animatureIndex].setCualitiesC(
		ands.readAnimatureColInt(animSel[animatureIndex].getIdAnimature(), 7),
		DEFENSE);
		animSel[animatureIndex].setCualitiesC(
		ands.readAnimatureColInt(animSel[animatureIndex].getIdAnimature(), 8),
		AGILITY);
		animSel[animatureIndex].setCualitiesC(
		ands.readAnimatureColInt(animSel[animatureIndex].getIdAnimature(), 9),
		STRENGHT);
		animSel[animatureIndex].setCualitiesC(
		ands.readAnimatureColInt(animSel[animatureIndex].getIdAnimature(), 10),
		PRECISSION);
		for (int i = 1; i <= animSel[animatureIndex].getLevel(); i++)
		{
			for (int j = 0; j < 5; j++)
			{
				animSel[animatureIndex].setCualitiesC(
				animSel[animatureIndex].getCualitiesC(j)
				+ (animSel[animatureIndex].getCualitiesC(j) / 3), j);
			}
		}
		animSel[animatureIndex].setHealthMax(ands.readAnimatureColInt(
		animSel[animatureIndex].getIdAnimature(), 11));
		if (animSel[animatureIndex].getLevel() > 1)
		{
			for (int i = 2; i <= animSel[animatureIndex].getLevel(); i++)
			{
				animSel[animatureIndex].setHealthMax(animSel[animatureIndex]
				.getHealthMax() + animSel[animatureIndex].getHealthMax() / 3);
			}
		}
		ands.close();

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
		attack1 = (Button) findViewById(R.id.btnAtack1);
		attacksBtns[0] = attack1;
		attack1.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				attackChoice = 0;
			}
		});
		attack2 = (Button) findViewById(R.id.btnAtack2);
		attacksBtns[1] = attack2;
		attack2.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				attackChoice = 0;
			}
		});
		attack3 = (Button) findViewById(R.id.btnAtack3);
		attacksBtns[2] = attack3;
		attack3.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				attackChoice = 0;
			}
		});
		attack4 = (Button) findViewById(R.id.btnAtack4);
		attacksBtns[3] = attack4;
		attack4.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				attackChoice = 0;
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

		loadEnemyAnimature(enemy);
		loadYourAnimature();

		while ( ! animatureFainted)
		{
			if (animSel[animatureIndex].getCualitiesC(SPEED) >= enemy
			.getCualitiesC(SPEED))
			{
				turn = true;
			}

			if (turn)
			{
				if (animSel[animatureIndex].getAttackPP(attackChoice) > 0)
				{
					enemy = animSel[animatureIndex].getAttack(attackChoice)
					.getCapturedDamage(enemy, animSel[animatureIndex]);
				}
			}
			// Enemy battle example
			int attack = randomAttack();
			while ( ! turn)
			{
				if (enemy.getAttackPP(attack) > 0)
				{
					animSel[animatureIndex] = enemy.getAttack(attack)
					.getCapturedDamage(animSel[animatureIndex], enemy);
					enemy.reduceAttackPP(attack);
					turn = true;
				}
				else
				{
					attack = randomAttack();
				}
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

	private void loadEnemyAnimature(final Captured captured)
	{
		enemy_animature_name.setText(captured.getNickname());
		enemy_animature_level.setText("Nvl " + captured.getLevel());
		enemy_animature_life.setMax(captured.getHealthMax()); // Cambiar
		final int id = getResources().getIdentifier(
		"f" + captured.getIdAnimature(), "drawable", getPackageName()); // Obtenemos
																		// el id
																		// del
																		// drawable
																		// del
																		// animature
		enemy_animature_image.setImageDrawable(this.getResources().getDrawable(
		id)); // Insertamos la imagen del animature
	}

	private void loadYourAnimature()
	{
		your_animature_name.setText(animSel[animatureIndex].getNickname());
		your_animature_level.setText("Nvl " + animSel[animatureIndex]);
		your_animature_life.setMax(animSel[animatureIndex].getHealthMax());
		your_animature_life.setProgress(animSel[animatureIndex].getHealthAct());
		your_animature_exp.setMax(animSel[animatureIndex].getExperience());
		your_animature_exp
		.setProgress(animSel[animatureIndex].getCurrent_exp());
		final int id = getResources().getIdentifier(
		"b" + animSel[animatureIndex].getIdAnimature(), "drawable",
		getPackageName()); // Obtenemos
		// el id
		// del
		// drawable
		// del
		// animature
		your_animature_image.setImageDrawable(this.getResources().getDrawable(
		id)); // Insertamos la imagen del animature
	}

	private int randomAttack()
	{
		final Random r = new Random();
		final int rand = r.nextInt(4);
		return rand;
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

	public Context getContext()
	{
		return this;
	}
}