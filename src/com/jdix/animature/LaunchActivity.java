package com.jdix.animature;

import org.json.JSONException;
import org.json.JSONObject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
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

import com.jdix.animature.utils.Connection;
import com.jdix.animature.utils.Database;
import com.jdix.animature.utils.StringUtils;

public class LaunchActivity extends Activity {

	private UserLoginTask	mAuthTask	= null;
	public SQLiteDatabase	db;
	private String			dbEmail, dbPassword;

	private EditText		mEditTextUserLogin;
	private EditText		mEditTextPasswordLogin;
	private CheckBox		mCheckBoxRecord;
	private Button			btn_Register;
	private Button			btn_Enter;
	private View			mLoginFormView;
	private View			mLoginStatusView;
	private TextView		mLoginStatusMessageView;

	private String			userEmail;
	private String			password;
	private boolean			remember;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);

		db = (new Database(this, "AnimatureWorldDB", null, 1))
				.getWritableDatabase();

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
				register();
			}
		});
		btn_Enter = (Button) findViewById(R.id.btn_Enter);
		btn_Enter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view)
			{
				attemptLogin();
			}
		});

		Cursor u = db.rawQuery("Select * FROM User", null);

		dbEmail = dbPassword = null;
		if (u.moveToFirst())
		{
			dbEmail = u.getString(1);
			dbPassword = u.getString(2);
		}

		if ( ! TextUtils.isEmpty(dbEmail) && ! TextUtils.isEmpty(dbPassword))
		{
			showProgress(true);

			Thread t;
			(t = new Thread() {
				@Override
				public void run()
				{
					Connection c = Connection.getInstance();

					c.setAction("login");
					c.addData("method", "auto");
					c.addData("email", dbEmail);
					c.addData("pass", dbPassword);

					String token = null;
					try
					{
						JSONObject jsonObject = c.execute();
						token = jsonObject.getString("token");
					}
					catch (JSONException e)
					{
					}

					if (token == null)
					{
						mEditTextUserLogin
								.setError(getString(R.string.conection_error));
						mEditTextUserLogin.requestFocus();
					}

					if (token != null)
					{
						Connection.setLogin(dbEmail, dbPassword);
					}
				}
			}).start();

			try
			{
				t.join();
			}
			catch (InterruptedException e)
			{
			}

			// We create the intent
			Intent intent = new Intent(LaunchActivity.this,
					MainMenuActivity.class);
			// We start the activity
			startActivity(intent);
			// We do not want a logged user to go back to login
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * Method pressing the register button
	 * 
	 */
	public void register()
	{
		// We create the intent
		Intent intent = new Intent(LaunchActivity.this, RegisterActivity.class);
		intent.putExtra("login", new ResultReceiver(null) {
			@Override
			protected void onReceiveResult(int resultCode, Bundle resultData)
			{
				LaunchActivity.this.finish();
			}
		});
		// We start the activity
		startActivityForResult(intent, 1);

		mEditTextUserLogin.setError(null);
		mEditTextPasswordLogin.setError(null);
	}

	/**
	 * Attempts to sign in the account specified by the login form. If there are
	 * form errors (invalid username and password, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin()
	{
		if (mAuthTask != null)
		{
			return;
		}

		// Reset errors.
		mEditTextUserLogin.setError(null);
		mEditTextPasswordLogin.setError(null);

		// We get the values from EditText
		userEmail = mEditTextUserLogin.getText().toString();
		password = mEditTextPasswordLogin.getText().toString();

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
		else
		{
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
			showProgress(true);
			mAuthTask = new UserLoginTask();
			mAuthTask.execute((Void) null);
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show)
	{
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2)
		{
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation)
						{
							mLoginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation)
						{
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		}
		else
		{
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
		boolean	bEmail, bPassword, bError;

		@Override
		protected Boolean doInBackground(Void... params)
		{
			Connection c = Connection.getInstance();

			c.setAction("login");
			c.addData("method", "manual");
			c.addData("email", userEmail);
			c.addData("pass", StringUtils.sha1(password + "--MiniunisHUB"));

			JSONObject jsonObject = c.execute();
			bEmail = bPassword = bError = false;
			if (jsonObject != null)
			{
				try
				{
					bEmail = jsonObject.getBoolean("email");
					bPassword = jsonObject.getBoolean("pass");
				}
				catch (JSONException e)
				{
				}
			}
			else
			{
				bError = true;
			}
			return bEmail && bPassword;
		}

		@Override
		protected void onPostExecute(final Boolean success)
		{
			mAuthTask = null;
			showProgress(false);

			if (success)
			{
				if (remember)
				{
					db.execSQL("UPDATE User SET email='" + userEmail + "',"
							+ "password='"
							+ StringUtils.sha1(password + "--Animature") + "'");
					Connection.setLogin(userEmail,
							StringUtils.sha1(password + "--Animature"));
				}

				// We create the intent
				Intent intent = new Intent(LaunchActivity.this,
						MainMenuActivity.class);

				// We start the activity
				startActivity(intent);
				// We do not want a logged user to go back to login
				finish();
			}
			else
			{
				if ( ! bError)
				{
					if ( ! bPassword)
					{
						mEditTextPasswordLogin
								.setError(getString(R.string.error_incorrect_password));
						mEditTextPasswordLogin.requestFocus();
					}
					if ( ! bEmail)
					{
						mEditTextUserLogin
								.setError(getString(R.string.error_invalid_email));
						mEditTextUserLogin.requestFocus();
					}
				}
				else
				{
					mEditTextUserLogin
							.setError(getString(R.string.conection_error));
					mEditTextUserLogin.requestFocus();
				}
			}
		}

		@Override
		protected void onCancelled()
		{
			mAuthTask = null;
			showProgress(false);
		}
	}
}