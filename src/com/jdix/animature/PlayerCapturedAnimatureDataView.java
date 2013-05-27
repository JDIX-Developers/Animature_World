package com.jdix.animature;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

/**
 * @author Jordan Aranda Tejada
 */
@SuppressWarnings ("deprecation")
public class PlayerCapturedAnimatureDataView extends TabActivity {

	private int	playerAnimatureIndex;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_captured_animature_data_view);

		final TabHost tabHost = getTabHost();

		final Bundle b = new Bundle();
		b.putInt("animatureIndex", playerAnimatureIndex);

		// Tab for Info Animature
		final TabSpec infoAnim = tabHost.newTabSpec("Inf. Anim.");
		infoAnim.setIndicator("Inf. Anim");
		final Intent infoAnimatureIntent = new Intent(this,
		PlayerCapturedInfoViewActivity.class);
		infoAnimatureIntent.putExtras(b);
		infoAnim.setContent(infoAnimatureIntent);

		// Tab for Qualities. Anim.
		final TabSpec habilAnim = tabHost.newTabSpec("Habil. Anim.");
		habilAnim.setIndicator("Habil. Anim");
		final Intent qualitiesAnimatureIntent = new Intent(this,
		PlayerCapturedQualitiesViewActivity.class);
		qualitiesAnimatureIntent.putExtras(b);
		qualitiesAnimatureIntent.putExtras(b);
		habilAnim.setContent(qualitiesAnimatureIntent);

		// Tab for Attacks Anim.
		final TabSpec attacksAnim = tabHost.newTabSpec("Attacks");
		attacksAnim.setIndicator("Ataques");
		/*
		 * final Intent attacksAnimIntent = new Intent(this,
		 * PlayerCapturedAttacksViewIntent.class);
		 * attacksAnim.setContent(attacksAnimIntent);
		 */
		infoAnimatureIntent.putExtras(b);
		attacksAnim.setContent(infoAnimatureIntent);

		// Adding all TabSpec to TabHost
		tabHost.addTab(infoAnim);
		tabHost.addTab(habilAnim);
		tabHost.addTab(attacksAnim);
	}
}