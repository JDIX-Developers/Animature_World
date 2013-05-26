package com.jdix.animature.entities;

import java.io.Serializable;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jdix.animature.R;
import com.jdix.animature.utils.Database;

/**
 * @author Jordan Aranda Tejada
 */
public class Animature implements Serializable {

	private static final long	serialVersionUID	= - 2197066367542502490L;

	public static final int		SPEED				= 0;
	public static final int		DEFENSE				= 1;
	public static final int		AGILITY				= 2;
	public static final int		STRENGTH			= 3;
	public static final int		PRECISION			= 4;

	public static final int		NORMAL				= 1;
	public static final int		FIRE				= 2;
	public static final int		WATER				= 4;
	public static final int		GRASS				= 8;
	public static final int		ELECTRIC			= 16;
	public static final int		ICE					= 32;
	public static final int		FIGHTING			= 64;
	public static final int		POISON				= 128;
	public static final int		GROUND				= 256;
	public static final int		FLYING				= 512;
	public static final int		PSYCHIC				= 1024;
	public static final int		BUG					= 2048;
	public static final int		ROCK				= 4096;
	public static final int		GHOST				= 8192;
	public static final int		DRAGON				= 16384;
	public static final int		DARK				= 32768;
	public static final int		STEEL				= 65536;
	public static final int		ELEMENTAL			= 131072;

	public static final int		OK					= 0;
	public static final int		PARALYZED			= 1;
	public static final int		BURNED				= 2;
	public static final int		POISONED			= 3;
	public static final int		SLEEPED				= 4;
	public static final int		FROZEN				= 5;

	private int					id;
	private int					animature;
	private int					save;
	private String				nickname;
	private int					status;
	private int					capturedTime;
	private Attack[]			attacks				= new Attack[4];
	private final int[]			attacksPP			= new int[4];
	private int					level;
	private int					currentExp;
	private int					health;

	public Animature(final int id, final int animature, final int save,
	final String nickname, final int status, final Attack a1, final int a1pp,
	final Attack a2, final int a2pp, final Attack a3, final int a3pp,
	final Attack a4, final int a4pp, final int level, final int currentExp,
	final int health)
	{
		this.id = id;
		this.animature = animature;
		this.save = save;
		this.nickname = nickname;
		this.status = status;
		this.attacks[0] = a1;
		this.attacks[1] = a2;
		this.attacks[2] = a3;
		this.attacks[3] = a4;
		this.attacksPP[0] = a1pp;
		this.attacksPP[1] = a2pp;
		this.attacksPP[2] = a3pp;
		this.attacksPP[3] = a4pp;
		this.level = level;
		this.currentExp = currentExp;
		this.health = health;
	}

	/**
	 * Creates a new animature at the begining of the game
	 * 
	 * @param animature - The animature ID
	 * @param nickname - The nickname of the animature
	 * @param context - The context of the application
	 */
	public Animature(final int animature, final String nickname,
	final Context context)
	{
		this(0, animature, Player.getInstance().getId(),
		(nickname == null ? Animature.getName(animature, context) : nickname),
		OK, Attack.load(1, context), Attack.load(1, context).getMaxPP(), null,
		0, null, 0, null, 0, 5, 0, 0);

		this.setHealth(this.getMaxHealth(context));
	}

	public int getId()
	{
		return id;
	}

	public int getAnimature()
	{
		return animature;
	}

	public void setAnimature(final int animature)
	{
		this.animature = animature;
	}

	public int getSave()
	{
		return save;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(final String nickname)
	{
		this.nickname = nickname;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(final int status)
	{
		this.status = status;
	}

	public Attack[] getAttacks()
	{
		return attacks;
	}

	public void setAttacks(final Attack[] attacks)
	{
		this.attacks = attacks;
	}

	public int[] getAttacksPP()
	{
		return attacksPP;
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(final int level)
	{
		this.level = level;
	}

	public int getCurrentExp()
	{
		return currentExp;
	}

	public void setCurrentExp(final int currentExp)
	{
		this.currentExp = currentExp;
	}

	public int getHealth()
	{
		return health;
	}

	public void setHealth(final int health)
	{
		this.health = health;
	}

	/**
	 * @return The number of attacks of the Animature
	 */
	public int getNumAttacks()
	{
		int attacks = 0;
		for (final Attack attack: this.attacks)
		{
			if (attack != null)
			{
				attacks++;
			}
		}
		return attacks;
	}

	/**
	 * Changes the save of the Animature
	 * 
	 * @param save - The save game
	 */
	public void setSave(final int save)
	{
		this.save = save;
	}

	/**
	 * @param context - The context of the application
	 * @return The types of the Animature, 0 in the second value of the array if
	 *         it has only one type
	 */
	public int[] getTypes(final Context context)
	{
		return getTypes(animature, context);
	}

	/**
	 * @param context - The context of the application
	 * @return The type of the Animature
	 */
	public int getType(final Context context)
	{
		return getType(animature, context);
	}

	/**
	 * Gets the qualities of the Animature
	 * 
	 * @param context - The context of the application
	 * @return The qualities of the Animature
	 */
	public int[] getQualities(final Context context)
	{
		return getQualities(animature, context);
	}

	/**
	 * Gets the evolution level for the Animature
	 * 
	 * @param context - The context of the appplication
	 * @return The Animature's evolution level.
	 */
	public int getLevelEvo(final Context context)
	{
		return getLevelEvo(animature, context);
	}

	/**
	 * @param context - The context of the application
	 * @return The base experience for the Animature
	 */
	public int getBaseExp(final Context context)
	{
		return getBaseExp(animature, context);
	}

	/**
	 * @param context - The context of the application
	 * @return The maximum health of the Animature
	 */
	public int getMaxHealth(final Context context)
	{
		return getMaxHealth(animature, level, context);
	}

	/**
	 * @param context - The context of the application
	 * @return The experience that the Animature must achieve to level up
	 */
	public int getMaxExperience(final Context context)
	{
		return getMaxExperience(level, context);
	}

	/**
	 * @param context - The context of the application
	 */
	public void levelUp(final Context context)
	{
		levelUp(this, context);
	}

	/**
	 * Saves the current Animature into the database
	 * 
	 * @param context - The context of the application
	 */
	public void save(final Context context)
	{
		final SQLiteDatabase db = (new Database(context)).getWritableDatabase();

		final ContentValues values = new ContentValues(15);
		values.put("animature", animature);
		values.put("save", this.save != 0 ? this.save : null);
		values.put("nickname", nickname);
		values.put("status", status);
		values.put("attack1", attacks[0].getId());
		values.put("attack1_pp", attacksPP[0]);
		values.put("attack2", attacks[1] != null ? attacks[1].getId() : null);
		values.put("attack2_pp", attacks[1] != null ? attacksPP[1] : null);
		values.put("attack3", attacks[2] != null ? attacks[2].getId() : null);
		values.put("attack3_pp", attacks[2] != null ? attacksPP[2] : null);
		values.put("attack4", attacks[3] != null ? attacks[3].getId() : null);
		values.put("attack4_pp", attacks[3] != null ? attacksPP[3] : null);
		values.put("health", health);
		values.put("level", level);
		values.put("cur_exp", currentExp);

		if (this.id == 0)
		{
			db.insert("ANIMATURE", null, values);

			final Cursor c = db.rawQuery("SELECT max(id) FROM ANIMATURE", null);
			if (c.moveToFirst())
			{
				this.id = c.getInt(0);
			}
			c.close();
		}
		else
		{
			db.update("ANIMATURE", values, "id = " + this.id, null);
		}

		db.close();

		// TODO save to server
	}

	/**
	 * Gets the type of the Animature
	 * 
	 * @param animature - The Animature to check
	 * @param context - The context of the application
	 * @return The type of the Animature
	 */
	public static int[] getTypes(final int animature, final Context context)
	{
		final int[] types = new int[2];
		int cont = 0;
		for (int i = 1; i < ELEMENTAL && cont < 2; i *= 2)
		{
			if (isOfType(i, animature, context))
			{
				types[cont++] = i;
			}
		}
		return types;
	}

	/**
	 * @param animature - The Animature to check
	 * @param context - The context of the application
	 * @return - The type of the Animature
	 */
	public static int getType(final int animature, final Context context)
	{
		return context.getResources().getIntArray(R.array.animature_type)[animature - 1];
	}

	/**
	 * @param animature - The Animature to check
	 * @param context - The context of the application
	 * @return - The description of the Animature
	 */
	public static String getDescription(final int animature,
	final Context context)
	{
		return context.getResources().getStringArray(
		R.array.animature_descriptions)[animature - 1];
	}

	/**
	 * @param animature - The animature to check
	 * @param context - The context of the application
	 * @return The qualities of the animature
	 */
	public static int[] getQualities(final int animature, final Context context)
	{
		final int[] qualities = new int[5];

		qualities[SPEED] = context.getResources().getIntArray(
		R.array.animature_speed)[animature - 1];
		qualities[DEFENSE] = context.getResources().getIntArray(
		R.array.animature_defense)[animature - 1];
		qualities[AGILITY] = context.getResources().getIntArray(
		R.array.animature_agility)[animature - 1];
		qualities[STRENGTH] = context.getResources().getIntArray(
		R.array.animature_strength)[animature - 1];
		qualities[PRECISION] = context.getResources().getIntArray(
		R.array.animature_precision)[animature - 1];
		return qualities;
	}

	/**
	 * Gets the evolution level for the given Animature
	 * 
	 * @param animature - The Animature to check
	 * @param context - The context of the application
	 * @return The evolution level for the Animature
	 */
	public static int getLevelEvo(final int animature, final Context context)
	{
		return context.getResources().getIntArray(R.array.animature_level_evo)[animature - 1];
	}

	/**
	 * Gets the base experience for the given Animature
	 * 
	 * @param animature - The Animature to check
	 * @param context - The context of the application
	 * @return The base experience
	 */
	public static int getBaseExp(final int animature, final Context context)
	{
		return Integer.parseInt(context.getResources().getStringArray(
		R.array.animature_base_exp)[animature - 1]);
	}

	/**
	 * Gets the height of the Animature
	 * 
	 * @param animature - The Animature to check
	 * @param context - The context of the application
	 * @return The height of the Animature
	 */
	public static float getHeight(final int animature, final Context context)
	{
		float height = 0;
		final TypedArray array = context.getResources().obtainTypedArray(
		R.array.animature_height);
		height = array.getFloat(animature - 1, 1);
		array.recycle();
		return height;
	}

	/**
	 * Gets the weight of the Animature
	 * 
	 * @param animature - The Animature to check
	 * @param context - The context of the application
	 * @return The weight of the Animature
	 */
	public static double getWeight(final int animature, final Context context)
	{
		float weight = 0;
		final TypedArray array = context.getResources().obtainTypedArray(
		R.array.animature_weight);
		weight = array.getFloat(animature - 1, 1);
		array.recycle();
		return weight;
	}

	/**
	 * @param type - The type to check
	 * @param animature - The Animature to check
	 * @param context - The context of the application
	 * @return If the Animature is of the given type
	 */
	public static boolean isOfType(final int type, final int animature,
	final Context context)
	{
		return (getType(animature, context) & type) == type;
	}

	/**
	 * @param animature - The Animature to check
	 * @param context - The context of the application
	 * @return - The name of the Animature
	 */
	public static String getName(final int animature, final Context context)
	{
		return context.getResources().getStringArray(R.array.animature_names)[animature - 1];
	}

	/**
	 * @param animature - The Animature to check
	 * @param level - The level of the Animature
	 * @param context - The context of the application
	 * @return - The max health of the Animature
	 */
	private static int getMaxHealth(final int animature, final int level,
	final Context context)
	{
		int maxHealth = context.getResources().getIntArray(
		R.array.animature_health)[animature - 1];
		if (level > 1)
		{
			for (int i = 2; i <= level; i++)
			{
				maxHealth += maxHealth / 3;
			}
		}
		return maxHealth;
	}

	/**
	 * @param level - The level of the Animature
	 * @param context - The context of the application
	 * @return - The experience that the Animature must achieve to level up
	 */
	private static int getMaxExperience(final int level, final Context context)
	{
		return (int) Math.pow(level, 3);
	}

	/**
	 * @param animature - The Animature to check
	 * @param context - The context of the application
	 */
	private static void levelUp(final Animature animature, final Context context)
	{
		animature.setLevel(animature.getLevel() + 1);
	}

	/**
	 * Loads an animature by ID
	 * 
	 * @param id - The ID of the animature
	 * @param context - The context of the application
	 * @return Theanimature loaded
	 */
	public static Animature load(final int id, final Context context)
	{
		if (id > 0)
		{
			final SQLiteDatabase db = (new Database(context))
			.getWritableDatabase();

			final Cursor c = db.rawQuery("SELECT * FROM ANIMATURE WHERE id = "
			+ id, null);

			c.moveToFirst();

			final int animature = c.getInt(1);
			final int save = c.getInt(2);
			final String nickname = c.getString(3);
			final int status = c.getInt(4);

			final Attack a1 = Attack.load(c.getInt(5), context);
			final Attack a2 = Attack.load(c.getInt(7), context);
			final Attack a3 = Attack.load(c.getInt(9), context);
			final Attack a4 = Attack.load(c.getInt(11), context);

			final int a1pp = c.getInt(6);
			final int a2pp = c.getInt(8);
			final int a3pp = c.getInt(10);
			final int a4pp = c.getInt(12);

			final int healthAct = c.getInt(13);
			final int level = c.getInt(14);
			final int currentExp = c.getInt(15);

			c.close();
			db.close();

			return new Animature(id, animature, save, nickname, status, a1,
			a1pp, a2, a2pp, a3, a3pp, a4, a4pp, level, currentExp, healthAct);
		}
		else
		{
			return null;
		}
	}
}