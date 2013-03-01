package com.jdix.poketmon;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LaunchActivity extends Activity
{
	private EditText editText_UserLogin;
	private EditText editText_PasswordLogin;
	private CheckBox checkBox_Record;
	private Button btn_Register;
	private Button btn_Enter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
		
		// We get a reference to the interface controls
		editText_UserLogin = (EditText)findViewById(R.id.editText_UserLogin);
		editText_PasswordLogin = (EditText)findViewById(R.id.editText_PasswordLogin);
		checkBox_Record = (CheckBox)findViewById(R.id.checkBox_Record);
		btn_Register = (Button)findViewById(R.id.btn_Register);
		btn_Enter = (Button)findViewById(R.id.btn_Enter);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_launch, menu);
		return true;
	}
	
	/**
	 * Method pressing the register button
	 * @param View
	 */
	public void register(View view) {
		
	}
	/**
	 * Method pressing the enter button
	 * @param View
	 */
	public void enter(View view){
		
	}
}