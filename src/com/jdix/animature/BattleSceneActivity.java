package com.jdix.animature;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BattleSceneActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle_scene);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.battle_scene, menu);
		return true;
	}

}
