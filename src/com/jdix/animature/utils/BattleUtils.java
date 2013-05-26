package com.jdix.animature.utils;

import java.util.Random;

import android.content.Context;

import com.jdix.animature.entities.Animature;
import com.jdix.animature.entities.Attack;

/**
 * BattleUtils
 * 
 * @author Jordan Aranda Tejada
 */
public class BattleUtils {

	/**
	 * Method to get if player's Animature hits first.
	 * 
	 * @param playerAnimature The player Animature.
	 * @param playerAnimatureAttack The player animature used attack.
	 * @param enemyAnimature The enemy Animature.
	 * @param enemyAnimatureAttack The enemi animature used attack.
	 * @param context - The context of the application
	 * @return true if player Animature hit first.
	 */
	public static boolean attacksFirst(final Animature playerAnimature,
	final Attack playerAnimatureAttack, final Animature enemyAnimature,
	final Attack enemyAnimatureAttack, final Context context)
	{
		return (playerAnimatureAttack.isFirst() && ! enemyAnimatureAttack
		.isFirst())
		|| (playerAnimatureAttack.isFirst() && enemyAnimatureAttack.isFirst() && playerAnimature
		.getQualities(context)[Animature.SPEED] > enemyAnimature
		.getQualities(context)[Animature.SPEED]);
	}

	/**
	 * Method to know if attacker Animature get hit other Animature.
	 * 
	 * @param attacker The attacker Animature.
	 * @param attack The attacker Animature used attack.
	 * @param underAttackAnimature The Animature who is under attack.
	 * @param context - The context of the application
	 * @return true if attacker Animature get hit.
	 */
	public static boolean getHit(final Animature attacker, final Attack attack,
	final Animature underAttackAnimature, final Context context)
	{
		return (new Random()).nextInt(100) <= (attack.getProbability() + (attacker
		.getQualities(context)[Animature.PRECISION] - underAttackAnimature
		.getQualities(context)[Animature.AGILITY]));
	}

	/**
	 * Method to know if player can escape from the wild Animature battle.
	 * 
	 * @param playerAnimature The player Animature.
	 * @param enemyAnimature Wild Animature.
	 * @param context - The context of the application
	 * @return true if player can escape from the battle.
	 */
	public static boolean canEscape(final Animature playerAnimature,
	final Animature enemyAnimature, final Context context)
	{
		return (playerAnimature.getLevel() > enemyAnimature.getLevel())
		|| (playerAnimature.getLevel() == enemyAnimature.getLevel() && enemyAnimature
		.getHealthAct() < enemyAnimature.getMaxHealth(context) * 0.4);
	}

	/**
	 * Method to get infringed damage to Animature.
	 * 
	 * @param attacker The Animature who attacks.
	 * @param defender The Animature under attack.
	 * @param attack The atack of attacker Animature.
	 * @param context - The context of the application
	 * @return The infringed damage.
	 */
	public static int getDamage(final Animature attacker,
	final Animature defender, final Attack attack, final Context context)
	{
		int damage = 0;

		if (attack.getEffectivenes(defender, context) == Attack.VERY_EFFECTIVE)
		{
			damage = ((attack.getPower() / 100) * (attacker
			.getQualities(context)[Animature.STRENGTH]
			/ defender.getQualities(context)[Animature.DEFENSE] * attacker
			.getQualities(context)[Animature.STRENGTH])) * 2;
		}
		else if (attack.getEffectivenes(defender, context) == Attack.FEW_EFFECTIVE)
		{
			damage = ((attack.getPower() / 100) * (attacker
			.getQualities(context)[Animature.STRENGTH]
			/ defender.getQualities(context)[Animature.DEFENSE] * attacker
			.getQualities(context)[Animature.STRENGTH])) / 2;
		}
		else
		{
			damage = (attack.getPower() / 100)
			* (attacker.getQualities(context)[Animature.STRENGTH]
			/ defender.getQualities(context)[Animature.DEFENSE] * attacker
				.getQualities(context)[Animature.STRENGTH]);
		}
		return damage;
	}

	/**
	 * Method to get infringed damage to Animature, when the PPs of all the
	 * attacks of the animature are equal to 0.
	 * 
	 * @param attacker - The Animature who attacks.
	 * @param defensor - The Animature under attack.
	 * @param context - The context of the application
	 * @return The infringed damage.
	 */
	public static int combat(final Animature attacker,
	final Animature defensor, final Context context)
	{
		int damage = 0;
		damage = (int) 0.1
		* (attacker.getQualities(context)[Animature.STRENGTH]
		/ defensor.getQualities(context)[Animature.DEFENSE] * attacker
			.getQualities(context)[Animature.STRENGTH]);
		return damage;
	}

	/**
	 * Method to get enemy Animature random attack
	 * 
	 * @param enemyAnimature - Enemy animature
	 * @return The index of the attack for enemy Animature.
	 */
	public static int getEnemyAnimatureRandomAttack(
	final Animature enemyAnimature)
	{
		final Random r = new Random();
		int indexAttack = - 1;
		boolean enc = false;
		do
		{
			indexAttack = r.nextInt(4);
			if ( ! enemyAnimature.getAttacks()[indexAttack].equals(null)
			&& enemyAnimature.getAttacksPP()[indexAttack] > 0)
			{
				enc = true;
			}
		}
		while ( ! enc);
		return indexAttack;
	}

	/**
	 * Method to check if Animature has enable attacks.
	 * 
	 * @param animature - Animature.
	 * @return True if it has enable attacks.
	 */
	public static boolean hasAnimatureEnableAttacks(final Animature animature)
	{
		boolean enc = false;
		for (int i = 0; i < 4 && ! enc; i++)
		{
			if ( ! animature.getAttacks()[i].equals(null)
			&& animature.getAttacksPP()[i] > 0)
			{
				enc = true;
			}
		}
		return enc;
	}

	// /**
	// * Method to load the cualities of the Animature
	// *
	// * @param captured The Animature whose cualities are going to be loaded
	// * @param context - The context of the application
	// */
	// public void loadCualities(final Animature captured, Context context)
	// {
	// for (int i = 1; i <= captured.getLevel(); i++)
	// {
	// for (int j = 0; j < 5; j++)
	// {
	// captured.setQualities(context)(
	// captured.getQualities(context)[j] + (captured.getQualities(context)[j] /
	// 3), j);
	// }
	// }
	// }

	// /**
	// * Method to upgrade Animature's cualities when level grow up
	// *
	// * @param captured The Animature whose level grow up
	// */
	// public static void levelUp(final Animature captured)
	// {
	// captured.setLevel(captured.getLevel() + 1);
	// captured.setCualitiesC(captured.getQualities(context)[Animature.SPEED] +
	// (captured.getCualitiesC(Animature.SPEED) / 3), Animature.SPEED);
	// captured.setCualitiesC(captured.getCualitiesC(Animature.DEFENSE)
	// + (captured.getCualitiesC(Animature.DEFENSE) / 3), Animature.DEFENSE);
	// captured.setCualitiesC(captured.getCualitiesC(Animature.AGILITY)
	// + (captured.getCualitiesC(Animature.AGILITY) / 3), Animature.AGILITY);
	// captured.setCualitiesC(captured.getCualitiesC(Animature.STRENGTH)
	// + (captured.getCualitiesC(Animature.STRENGTH) / 3), Animature.STRENGTH);
	// captured.setCualitiesC(captured.getCualitiesC(Animature.PRECISION)
	// + (captured.getCualitiesC(Animature.PRECISION) / 3),
	// Animature.PRECISION);
	// captured.setHealthAct(captured.getHealthAct()
	// + (captured.getHealthAct() / 3));
	// captured.setHealthMax(captured.getHealthMax()
	// + (captured.getHealthMax() / 3));
	// captured.setCurrentExp(captured.getCurrentExp()
	// - captured.getCurrentExp());
	// captured.setExperience((int) Math.pow(captured.getLevel(), 3));
	// }

	// /**
	// * Method to check if the Animature could evolution and it do if meets the
	// * condition
	// *
	// * @param captured if The Animature who could evolution
	// */
	// public static void evolution(final Animature captured)
	// {
	// if (captured.getLevel() == captured.getLevelEvo())
	// {
	// captured.setAnimature(captured.getAnimature() + 1);
	// }
	// }

	// /**
	// * Method to heal the Animature
	// *
	// * @param captured The Animature who is going to be healed
	// */
	// public void healAnimature(final Animature captured)
	// {
	// captured.setHealthAct(captured.getHealthMax());
	// captured.setStatus(Animature.NORMAL);
	// }

	// /**
	// * Method to get experience to your animature
	// *
	// * @param battleType 0 wild battle, 1 trainer battle
	// * @param yourCaptured Your Animature who is going to receive experience
	// * @param enemy The enemy Animature which has been fainted
	// * @return the experience in the battle
	// */
	// public int giveExp(final int battleType, final Animature yourCaptured,
	// final Animature enemy)
	// {
	// if (battleType == 0)
	// {
	// return (enemy.getBaseExp() * enemy.getLevel()) / 7;
	// }
	// else
	// {
	// return (int) ((enemy.getBaseExp() * enemy.getLevel() * 1.5) / 7);
	// }
	// }
}
