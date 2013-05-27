package com.jdix.animature;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jdix.animature.entities.Animature;
import com.jdix.animature.entities.Attack;
import com.jdix.animature.entities.Player;
import com.jdix.animature.utils.BattleUtils;

/**
 * @author Jordan Aranda Tejada
 */
public class BattleActivity extends Activity {

	private Animature		wildAnimature;
	private Animature		playerAnimature;

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
	private Button[]		btnsAttacks;
	private Button			btnAttack1;
	private Button			btnAttack2;
	private Button			btnAttack3;
	private Button			btnAttack4;

	private int				stageOfBattle;
	private boolean			levelUp;
	private int				playerSelectedAttack;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle);

		// We recover the information passed in the intent
		wildAnimature = (Animature) this.getIntent().getSerializableExtra(
		"wild_animature");

		// GETS PLAYER FIRST ENABLE ANIMATURE
		playerAnimature = getPlayerFirstAnimature();

		btnsAttacks = new Button[4];

		// We get a reference to the interface controls
		// Wild Animature Components
		enemyNameTextView = (TextView) findViewById(R.id.enemy_name_textView);
		enemyLevelTextView = (TextView) findViewById(R.id.enemy_level_textView);
		enemyCapturedImageView = (ImageView) findViewById(R.id.enemy_captured_image);
		enemyLifeProgressBar = (ProgressBar) findViewById(R.id.enemy_live_progressBar);
		enemyImageView = (ImageView) findViewById(R.id.enemy_imageView);
		// Player Animature Components
		playerAnimatureDataLayout = (LinearLayout) findViewById(R.id.player_animature_data_layout);
		playerAnimatureNameTextView = (TextView) findViewById(R.id.player_animature_name_textView);
		playerAnimatureLevelTextView = (TextView) findViewById(R.id.player_animature_level_textView);
		playerAnimatureLifeProgressBar = (ProgressBar) findViewById(R.id.player_animature_live_progressBar);
		playerAnimatureCurrentPSTextView = (TextView) findViewById(R.id.player_animature_ps_textView);
		playerAnimatureExperienceProgressBar = (ProgressBar) findViewById(R.id.player_animature_experience_progressBar);
		playerAnimatureImageView = (ImageView) findViewById(R.id.player_animature_imageView);

		// Player Components
		playerTextView = (TextView) findViewById(R.id.player_text_view_battle);
		playerTextView.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				stageOfBattle++;
				stagesOfBattle();
			}
		});

		playerAnimatureAttacksLayout = (LinearLayout) findViewById(R.id.player_animature_attacks_layout);
		playerBattleOptionsLayout = (LinearLayout) findViewById(R.id.player_battle_options_layout);

		playerBattleOptionsHeader = (TextView) findViewById(R.id.battle_options_header);
		btnFightBattleActivity = (Button) findViewById(R.id.btnFightBattleActivity);
		btnFightBattleActivity.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				if (BattleUtils.hasAnimatureEnableAttacks(playerAnimature))
				{
					playerBattleOptionsLayout.setVisibility(View.GONE);
					loadPlayerAnimatureAttackButtons();
					playerAnimatureAttacksLayout.setVisibility(View.VISIBLE);
				}
				else
				{
					BattleUtils.combat(playerAnimature, wildAnimature,
					BattleActivity.this);
					try
					{
						this.wait(2000);
					}
					catch (final InterruptedException e)
					{
						e.printStackTrace();
					}
					showPlayerTextView(getResources().getString(
					R.string.battle_string_15).replace(
					"%a",
					Animature.getName(playerAnimature.getAnimature(),
					BattleActivity.this)));
				}
			}
		});
		btnAnimatureBattleActivity = (Button) findViewById(R.id.btnAnimatureBattleActivity);
		btnAnimatureBattleActivity
		.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				// TODO NOTHING
			}
		});
		btnBagBattleActivity = (Button) findViewById(R.id.btnBagBattleActivity);
		btnBagBattleActivity.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				// TODO NOTHING
			}
		});
		btnEscapeBattleActivity = (Button) findViewById(R.id.btnEscapeBattleActivity);
		btnEscapeBattleActivity.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				if (BattleUtils.canEscape(playerAnimature, wildAnimature,
				BattleActivity.this))
				{
					showPlayerTextView(getResources().getString(
					R.string.battle_string_14));
					finish();
				}
				else
				{
					Log.e("NO PUEDE ESCAPAR", "No puede huir");
					stageOfBattle = 2;
					stagesOfBattle();
				}
			}
		});

		playerAnimatureAttacksLayout = (LinearLayout) findViewById(R.id.player_animature_attacks_layout);
		btnAttack1 = (Button) findViewById(R.id.btn_attack1);
		btnAttack1.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				if ( ! playerAnimature.getAttacks()[0].equals(null)
				&& playerAnimature.getAttacksPP()[0] > 0)
				{
					stageOfBattle = 5;
					playerSelectedAttack = 0;
					stagesOfBattle();
				}
			}
		});
		btnsAttacks[0] = btnAttack1;
		btnAttack2 = (Button) findViewById(R.id.btn_attack2);
		btnAttack2.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				if ( ! playerAnimature.getAttacks()[1].equals(null)
				&& playerAnimature.getAttacksPP()[1] > 0)
				{
					stageOfBattle = 5;
					playerSelectedAttack = 1;
					stagesOfBattle();
				}
			}
		});
		btnsAttacks[1] = btnAttack2;
		btnAttack3 = (Button) findViewById(R.id.btn_attack3);
		btnAttack3.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				if ( ! playerAnimature.getAttacks()[2].equals(null)
				&& playerAnimature.getAttacksPP()[2] > 0)
				{
					stageOfBattle = 5;
					playerSelectedAttack = 2;
					stagesOfBattle();
				}
			}
		});
		btnsAttacks[2] = btnAttack3;
		btnAttack4 = (Button) findViewById(R.id.btn_attack4);
		btnAttack4.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				if ( ! playerAnimature.getAttacks()[3].equals(null)
				&& playerAnimature.getAttacksPP()[3] > 0)
				{
					stageOfBattle = 5;
					playerSelectedAttack = 3;
					stagesOfBattle();
				}
			}
		});
		btnsAttacks[3] = btnAttack4;
		// END REFERENCE GETTERS

		// LOAD WILD ANIMATURE COMPONENTS
		loadEnemyAnimatureComponents();

		// SHOW PLAYER IMAGE
		playerAnimatureImageView.setImageDrawable(this.getResources()
		.getDrawable(R.drawable.player_battle_image));

		// SHOW FIRST MESSAGE --> ¡Un Pidgey salvaje!
		showPlayerTextView(getResources().getString(R.string.battle_string_1)
		.replace("%a", Animature.getName(wildAnimature.getAnimature(), this)));

		stageOfBattle = 0;
	}

	private void clearBottomLayouts()
	{
		playerAnimatureAttacksLayout.setVisibility(View.GONE);
		playerBattleOptionsLayout.setVisibility(View.GONE);
		playerTextView.setVisibility(View.GONE);
	}

	private void showPlayerTextView(final String text)
	{
		playerTextView.setText(text);
		playerTextView.setVisibility(View.VISIBLE);

		playerTextView.setCompoundDrawablesWithIntrinsicBounds(null, null,
		getResources().getDrawable(R.drawable.flecha), null);
		playerTextView.setClickable(true);
	}

	private void stagesOfBattle()
	{
		switch (stageOfBattle)
		{
			case 1:
				final String namePlayerAnimature = getResources().getString(
				R.string.battle_string_2).replace("%a",
				Animature.getName(playerAnimature.getAnimature(), this));
				// SHOW MESSAGE --> ¡Adelante Charmander!
				showPlayerTextView(namePlayerAnimature);
			break;
			case 2:
				loadPlayerAnimatureComponents();
				loadEnemyAnimatureComponents();
				clearBottomLayouts();
				playerAnimatureDataLayout.setVisibility(View.VISIBLE);
				playerBattleOptionsLayout.setVisibility(View.VISIBLE);
			break;
			case 3:
				clearBottomLayouts();
				loadPlayerAnimatureAttackButtons();
				playerAnimatureAttacksLayout.setVisibility(View.VISIBLE);
			break;
			case 5:
				int indexAttack = - 1;
				if (BattleUtils.hasAnimatureEnableAttacks(wildAnimature))
				{
					indexAttack = BattleUtils
					.getEnemyAnimatureRandomAttack(wildAnimature);
				}
				runBattle(indexAttack);
			break;
			case 6:
				playerBattleOptionsLayout.setVisibility(View.GONE);
				showPlayerTextView(getResources().getString(
				R.string.battle_string_11).replace("%a",
				playerAnimature.getNickname()));
			break;
			case 7:
				playerAnimatureDataLayout.setVisibility(View.GONE);
				showPlayerTextView(getResources().getString(
				R.string.battle_string_13).replace("%a",
				Player.getInstance().getName()));
			break;
			case 8:
				showPlayerTextView(getResources().getString(
				R.string.battle_combat_gameOver).replace("%a",
				Player.getInstance().getName()));
			break;
			case 9:
			// RETURN TO MAP VIEW
			break;
			case 10:
				showPlayerTextView(getResources().getString(
				R.string.battle_string_10).replace("%a",
				Animature.getName(wildAnimature.getAnimature(), this)));
			break;
			case 11:
				final int exp = BattleUtils.giveExp(0, playerAnimature,
				wildAnimature, this);

				String text = getResources().getString(
				R.string.battle_string_12).replace("%a",
				playerAnimature.getNickname());
				text = text.replace("$s", exp + "");
				showPlayerTextView(text);

				playerAnimature.setCurrentExp(playerAnimature.getCurrentExp()
				+ exp);
				if (playerAnimature.getCurrentExp() >= playerAnimature
				.getMaxExperience(this))
				{
					levelUp = true;
					playerAnimature.levelUp(this);
				}
			break;
			case 12:
				if (levelUp == true)
				{
					showPlayerTextView(getResources().getString(
					R.string.battle_string_16).replace("%a",
					playerAnimature.getNickname()));
				}
			// RETURN TO MAP VIEW
			break;
			case 13:

			break;

		}
	}

	private Animature getPlayerFirstAnimature()
	{
		boolean enc = false;
		int i = 0;
		final Animature[] anims = Player.getInstance().getActiveAnimatures();
		while ( ! enc && i < anims.length)
		{
			if (anims[i].getHealth() > 0)
			{
				enc = true;
			}
			else
			{
				i++;
			}
		}
		return anims[i];
	}

	private void loadPlayerAnimatureComponents()
	{
		// Player Animature Name
		playerAnimatureNameTextView.setText(Animature.getName(
		playerAnimature.getAnimature(), this));
		// Player Animature Image
		final int id = getResources().getIdentifier(
		"b" + playerAnimature.getAnimature(), "drawable", getPackageName());
		playerAnimatureImageView.setImageDrawable(this.getResources()
		.getDrawable(id));
		// Player Animature Level
		playerAnimatureLevelTextView
		.setText("Nv " + playerAnimature.getLevel());
		// Player Animature Life ProgressBar and TextView
		final int maxHealth = playerAnimature.getMaxHealth(this);
		final int currentHealth = playerAnimature.getHealth();
		playerAnimatureLifeProgressBar.setMax(maxHealth);
		playerAnimatureLifeProgressBar.setProgress(currentHealth);
		playerAnimatureCurrentPSTextView.setText(currentHealth + " / "
		+ maxHealth);
		// Player Animature Exp ProgressBar
		playerAnimatureExperienceProgressBar.setMax(playerAnimature
		.getMaxHealth(this));
		// Battle Options Layout Header
		playerBattleOptionsHeader.setText(getResources().getString(
		R.string.battle_string_3).replace("%a",
		Animature.getName(playerAnimature.getAnimature(), this)));
	}

	private void loadPlayerAnimatureAttackButtons()
	{
		for (int i = 0; i < btnsAttacks.length; i++)
		{
			if (playerAnimature.getAttacks()[i] != null)
			{
				final Attack attack = playerAnimature.getAttacks()[i];
				btnsAttacks[i]
				.setText(attack.getName()
				+ " ("
				+ playerAnimature.getAttacksPP()[i]
				+ " / "
				+ attack.getMaxPP()
				+ ")\nTipo"
				+ getResources().getStringArray(R.array.animature_types_names)[(int) Math
				.round(Math.log(attack.getType()) / Math.log(2))]);
			}
			else
			{
				btnsAttacks[i].setOnClickListener(null);
			}
		}
	}

	private void loadEnemyAnimatureComponents()
	{
		// WILD ANIMATURE IMAGE
		final int id = getResources().getIdentifier(
		"f" + wildAnimature.getAnimature(), "drawable", getPackageName());
		enemyImageView.setImageDrawable(this.getResources().getDrawable(id));

		// IF PLAYER HAS THIS ANIMATURE
		if (Player.getInstance().hasAnimature(wildAnimature.getAnimature()))
		{
			enemyCapturedImageView.setVisibility(View.VISIBLE);
		}

		enemyNameTextView.setText(wildAnimature.getNickname());

		// WILD ANIMATURE LEVEL
		enemyLevelTextView.setText("Nv " + wildAnimature.getLevel());

		// WILD ANIMATURE LIFE PROGRESSBAR
		enemyLifeProgressBar.setMax(wildAnimature.getMaxHealth(this));
		enemyLifeProgressBar.setProgress(wildAnimature.getHealth());
	}

	private void runBattle(final int enemyIndexAttack)
	{
		final Attack playerAnimatureAttack = playerAnimature.getAttacks()[playerSelectedAttack];
		final Attack enemyAnimatureAttack = wildAnimature.getAttacks()[enemyIndexAttack];

		if (BattleUtils.attacksFirst(playerAnimature, playerAnimatureAttack,
		wildAnimature, enemyAnimatureAttack, this)
		&& playerSelectedAttack > - 1)
		{
			attack(playerAnimature, playerSelectedAttack, wildAnimature);
			attack(wildAnimature, enemyIndexAttack, playerAnimature);
		}
		else
		{
			attack(wildAnimature, enemyIndexAttack, playerAnimature);
			attack(playerAnimature, playerSelectedAttack, wildAnimature);
		}
		Log.e("VIDA", "Vida animature: " + playerAnimature.getHealth());
		Log.e("VIDA2", "Vida wildAnimature: " + wildAnimature.getHealth());

		if (playerAnimature.getHealth() < 0)
		{
			playerAnimature.setHealth(0);
			playerAnimatureImageView.setVisibility(View.INVISIBLE);
			playerAnimatureAttacksLayout.setVisibility(View.GONE);
			loadEnemyAnimatureComponents();
			loadPlayerAnimatureComponents();
			stageOfBattle = 6;
			Log.e("MUERE",
			"Vida PlayerAnimature: " + playerAnimature.getHealth());
			stagesOfBattle();
		}
		else if (wildAnimature.getHealth() < 0)
		{
			stageOfBattle = 10;
			enemyImageView.setVisibility(View.INVISIBLE);
			stagesOfBattle();
		}

	}

	private void attack(final Animature attacker, final int indexAttack,
	final Animature defender)
	{
		attacker.getAttacksPP()[indexAttack]--;

		if (BattleUtils.getHit(attacker, attacker.getAttacks()[indexAttack],
		defender, this))
		{
			if (attacker.getAttacks()[indexAttack].isActive())
			{
				final int damage = BattleUtils.getDamage(attacker, defender,
				attacker.getAttacks()[indexAttack], this);
				Log.e("DAMAGE", "Damage: " + damage);
				defender.setHealth(defender.getHealth() - damage);
			}
		}
	}

}