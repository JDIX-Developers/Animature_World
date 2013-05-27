package com.jdix.animature;

import android.app.Activity;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
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
	private Button[]		btnsAttacks;
	private Button			btnAttack1;
	private Button			btnAttack2;
	private Button			btnAttack3;
	private Button			btnAttack4;

	private ActionListener	animatureRestLifeAction;

	private int				stageOfBattle;

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
		enemyDataLayout = (LinearLayout) findViewById(R.id.enemy_animature_data_layout);
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
					showPlayerTextView(
					getResources().getString(R.string.battle_string_15)
					.replace(
					"%a",
					Animature.getName(playerAnimature.getAnimature(),
					BattleActivity.this)), false);
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
					showPlayerTextView(
					getResources().getString(R.string.battle_string_14), false);
					try
					{
						Thread.sleep(3000);
					}
					catch (final InterruptedException e)
					{
						e.printStackTrace();
					}
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
		.replace("%a", Animature.getName(wildAnimature.getAnimature(), this)),
		true);

		stageOfBattle = 0;
	}

	private void clearBottomLayouts()
	{
		playerAnimatureAttacksLayout.setVisibility(View.GONE);
		playerBattleOptionsLayout.setVisibility(View.GONE);
		playerTextView.setVisibility(View.GONE);
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

	private void stagesOfBattle()
	{
		switch (stageOfBattle)
		{
			case 1:
				final String namePlayerAnimature = getResources().getString(
				R.string.battle_string_2).replace("%a",
				Animature.getName(playerAnimature.getAnimature(), this));
				// SHOW MESSAGE --> ¡Adelante Charmander!
				showPlayerTextView(namePlayerAnimature, false);
				try
				{
					Thread.sleep(5000);
				}
				catch (final InterruptedException e)
				{
					e.printStackTrace();
				}
				loadPlayerAnimatureComponents();
			case 2:
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
				btnAttack1
				.setText(attack.getName()
				+ " ("
				+ playerAnimature.getAttacksPP()[i]
				+ " / "
				+ attack.getMaxPP()
				+ ")\nTipo"
				+ getResources().getStringArray(R.array.animature_types_names)[(int) Math
				.round(Math.log(attack.getType()) / Math.log(2))]);
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
		enemyLevelTextView.setText("" + wildAnimature.getLevel());

		// WILD ANIMATURE LIFE PROGRESSBAR
		enemyLifeProgressBar.setMax(wildAnimature.getMaxHealth(this));
		enemyLifeProgressBar.setProgress(wildAnimature.getMaxHealth(this));
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
			attack(wildAnimature,
			BattleUtils.getEnemyAnimatureRandomAttack(wildAnimature),
			playerAnimature);
		}
		else
		{
			attack(wildAnimature,
			BattleUtils.getEnemyAnimatureRandomAttack(wildAnimature),
			playerAnimature);
			attack(playerAnimature, playerSelectedAttack, wildAnimature);
		}
	}

	private void attack(final Animature attacker, final int indexAttack,
	final Animature defender)
	{
		String text = getResources().getString(R.string.battle_string_4)
		.replace("%a", Animature.getName(attacker.getAnimature(), this));
		text = text.replace("$b", attacker.getAttacks()[indexAttack].getName());
		// SHOW MESSAGE --> ¡Charmander usó ascuas!
		showPlayerTextView(text, false);
		attacker.getAttacksPP()[indexAttack]--;
		try
		{
			this.wait(2000);
		}
		catch (final InterruptedException e)
		{
			e.printStackTrace();
		}
		if ( ! BattleUtils.getHit(attacker, attacker.getAttacks()[indexAttack],
		defender, this))
		{
			// SHOW MESSAGE --> ¡El ataque de Charmander falló!
			showPlayerTextView(
			getResources().getString(R.string.battle_string_5).replace("%a",
			Animature.getName(attacker.getAnimature(), this)), false);
			try
			{
				this.wait(2000);
			}
			catch (final InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			if (attacker.getAttacks()[indexAttack].isActive())
			{
				BattleUtils.getDamage(attacker, defender,
				attacker.getAttacks()[indexAttack], this);

				switch (attacker.getAttacks()[indexAttack].getEffectivenes(
				defender, this))
				{
				// SHOW MESSAGE --> ¡Es muy eficaz!
					case Attack.VERY_EFFECTIVE:
						showPlayerTextView(
						getResources().getString(R.string.battle_string_7),
						false);
					break;

					// SHOW MESSAGE --> No es muy eficaz…
					case Attack.FEW_EFFECTIVE:
						showPlayerTextView(
						getResources().getString(R.string.battle_string_8),
						false);
					break;

					// SHOW MESSAGE --> ¡No afecta a Pidgey!
					case Attack.NOT_EFFECTIVE:
						showPlayerTextView(
						getResources().getString(R.string.battle_string_9)
						.replace("*anim*",
						Animature.getName(attacker.getAnimature(), this)),
						false);
					break;
				}
				try
				{
					this.wait(2000);
				}
				catch (final InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				// SHOW MESSAGE --> ¡Defensa de Charmander bajó!
				String text2 = getResources().getString(
				R.string.battle_string_6).replace("*anim*",
				Animature.getName(defender.getAnimature(), this));
				text2 = text2.replace("*quality*", "Defensa");
				showPlayerTextView(text2, false);
			}
		}
	}
}