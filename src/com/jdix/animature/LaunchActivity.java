package com.jdix.animature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class LaunchActivity extends Activity {

	private EditText	mEditTextUserLogin;
	private EditText	mEditTextPasswordLogin;
	private CheckBox	mCheckBoxRecord;
	private Button		btn_Register;
	private Button		btn_Enter;
	private View		mLoginFormView;
	private View		mLoginStatusView;
	private TextView	mLoginStatusMessageView;

	private String		userEmail;
	private String		password;
	private boolean		remember;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);

		// startActivity(new Intent(LaunchActivity.this,
		// MapTestActivity.class));
		// finish();

		// We get a reference to the interface controls
		mEditTextUserLogin = (EditText) findViewById(R.id.editText_UserLogin);
		mEditTextPasswordLogin = (EditText) findViewById(R.id.editText_PasswordLogin);

		mCheckBoxRecord = (CheckBox) findViewById(R.id.checkBox_Record);
		mCheckBoxRecord
				.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked)
					{
						remember = isChecked;
					}
				});

		mLoginFormView = findViewById(R.id.login_form);
		mLoginStatusView = findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

		btn_Register = (Button) findViewById(R.id.btn_Register);
		btn_Register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view)
			{
				// We create the intent
				Intent intent = new Intent(LaunchActivity.this,
						RegisterActivity.class);
				intent.putExtra("login", new ResultReceiver(null) {
					@Override
					protected void onReceiveResult(int resultCode,
							Bundle resultData)
					{
						LaunchActivity.this.finish();
					}
				});
				// We start the activity
				startActivityForResult(intent, 1);

				mEditTextUserLogin.setError(null);
				mEditTextPasswordLogin.setError(null);
			}
		});
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
		userEmail = mEditTextUserLogin.getText().toString();
		password = mEditTextPasswordLogin.getText().toString();

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
			mEditTextPasswordLogin
					.setError(getString(R.string.error_field_required));
			isAcepted = false;
			focusView = mEditTextPasswordLogin;
		}
		if (TextUtils.isEmpty(userEmail))
		{
			mEditTextUserLogin
					.setError(getString(R.string.error_field_required));
			isAcepted = false;
			focusView = mEditTextUserLogin;
		}
		else if ( ! Patterns.EMAIL_ADDRESS.matcher(userEmail).matches())
		{
			mEditTextUserLogin
					.setError(getString(R.string.error_invalid_email));
			focusView = mEditTextUserLogin;
			isAcepted = false;
		}

		if ( ! isAcepted)
		{
			focusView.requestFocus();
		}

		return isAcepted;
	}
}