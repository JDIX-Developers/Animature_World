package com.jdix.animature.utils;

import java.util.Random;

import com.jdix.animature.entities.Animature;
import com.jdix.animature.entities.Attack;
import com.jdix.animature.entities.Capturable;

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
	 * @return true if player Animature hit first.
	 */
	public static boolean attacksFirst(final Animature playerAnimature,
	final Attack playerAnimatureAttack, final Animature enemyAnimature,
	final Attack enemyAnimatureAttack)
	{
		return (playerAnimatureAttack.isFirst() && ! enemyAnimatureAttack
		.isFirst())
		|| (playerAnimatureAttack.isFirst() && enemyAnimatureAttack.isFirst() && playerAnimature
		.getVelocity() > enemyAnimature.getVelocity());
	}

	/**
	 * Method to know if attacker Animature get hit other Animature.
	 * 
	 * @param attacker The attacker Animature.
	 * @param attack The attacker Animature used attack.
	 * @param underAttackAnimature The Animature who is under attack.
	 * @return true if attacker Animature get hit.
	 */
	public static boolean getHit(final Animature attacker, final Attack attack,
	final Animature underAttackAnimature)
	{
		return (new Random()).nextInt(100) <= (attack.getProbability() + (attacker
		.getPrecission() - underAttackAnimature.getAgility()));
	}

	/**
	 * Method to know if player can escape from the wild Animature battle.
	 * 
	 * @param playerAnimature The player Animature.
	 * @param enemyAnimature Wild Animature.
	 * @return true if player can escape from the battle.
	 */
	public static boolean canEscape(final Capturable playerAnimature,
	final Capturable enemyAnimature)
	{
		return (playerAnimature.getLevel() > enemyAnimature.getLevel())
		|| (playerAnimature.getLevel() == enemyAnimature.getLevel() && enemyAnimature
		.getHealthAct() < enemyAnimature.getHealthMax() * 0.4);
	}

	/**
	 * Method to get infringed damage to Animature.
	 * 
	 * @param attacker The Animature who attacks.
	 * @param defensor The Animature under attack.
	 * @param attack The atack of attacker Animature.
	 * @return The infringed damage.
	 */
	public static int getDamage(final Capturable attacker,
	final Capturable defensor, final Attack attack)
	{
		int damage = 0;

		if (attack.getEffectivenes(defensor) == Attack.VERY_EFFECTIVENES)
		{
			damage = (attack.getPower() / 100)
			* (attacker.getStrenght() / defensor.getDefense())
			* attacker.getStrenght() * 2;
		}
		else if (attack.getEffectivenes(defensor) == Attack.FEW_EFFECTIVENES)
		{
			damage = (attack.getPower() / 100)
			* (attacker.getStrenght() / defensor.getDefense())
			* attacker.getStrenght() / 2;
		}
		else
		{
			damage = (attack.getPower() / 100)
			* (attacker.getStrenght() / defensor.getDefense());
		}
		return damage;
	}
}
