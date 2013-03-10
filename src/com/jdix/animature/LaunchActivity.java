package com.jdix.animature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LaunchActivity extends Activity {

	private EditText	editText_UserLogin;
	private EditText	editText_PasswordLogin;
	private CheckBox	checkBox_Record;
	private Button		btn_Register;
	private Button		btn_Enter;

	private String		username;
	private String		password;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);

		// We get a reference to the interface controls
		editText_UserLogin = (EditText) findViewById(R.id.editText_UserLogin);
		editText_PasswordLogin = (EditText) findViewById(R.id.editText_PasswordLogin);
		checkBox_Record = (CheckBox) findViewById(R.id.checkBox_Record);
		btn_Register = (Button) findViewById(R.id.btn_Register);
		btn_Enter = (Button) findViewById(R.id.btn_Enter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * Method pressing the register button
	 * 
	 * @param View
	 */
	public void register(View view)
	{
		// We create the attempt
		Intent intent = new Intent(LaunchActivity.this, RegisterActivity.class);
		// We started the new activity
		startActivity(intent);
	}

	/**
	 * Method pressing the enter button
	 * 
	 * @param View
	 */
	public void enter(View view)
	{
		// We get the values from EditText
		username = editText_UserLogin.getText().toString();
		password = editText_PasswordLogin.getText().toString();

		if (attemptLogin())
		{
			// Provisional
			Intent intent = new Intent(LaunchActivity.this,
					MainMenuActivity.class);
			startActivity(intent);
		}
	}

	/**
	 * Attempts to sign in the account specified by the login form. If there are
	 * form errors (invalid username and password, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public boolean attemptLogin()
	{
		View focusView = null;
		boolean isAcepted = true;

		if (TextUtils.isEmpty(password))
		{
			editText_PasswordLogin
					.setError(getString(R.string.error_field_required));
			isAcepted = false;
			focusView = editText_PasswordLogin;
		}
		if (TextUtils.isEmpty(username))
		{
			editText_UserLogin
					.setError(getString(R.string.error_field_required));
			isAcepted = false;
			focusView = editText_UserLogin;
		}

		if (!isAcepted)
		{
			focusView.requestFocus();
		}

		return isAcepted;
	}
}