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
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

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

	private String			userEmail;
	private String			mPassword;
	private boolean			remember;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);

		Log.e("INICIO", "INICIO");
		startActivity(new Intent(LaunchActivity.this,
		AnimaxGameMenuActivity.class));
		this.finish();

		db = (new Database(this, "AnimatureWorldDB", null, 1))
		.getWritableDatabase();

		// We get a reference to the interface controls
		mEditTextUserLogin = (EditText) findViewById(R.id.editText_UserLogin);
		mEditTextPasswordLogin = (EditText) findViewById(R.id.editText_PasswordLogin);

		mCheckBoxRecord = (CheckBox) findViewById(R.id.checkBox_Record);
		mCheckBoxRecord
		.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(final CompoundButton buttonView,
			final boolean isChecked)
			{
				remember = isChecked;
			}
		});

		mLoginFormView = findViewById(R.id.login_form);
		mLoginStatusView = findViewById(R.id.login_status);

		btn_Register = (Button) findViewById(R.id.btn_Register);
		btn_Register.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				register();
			}
		});
		btn_Enter = (Button) findViewById(R.id.btn_Enter);
		btn_Enter.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(final View view)
			{
				attemptLogin();
			}
		});

		final Cursor u = db.rawQuery("Select * FROM User", null);

		dbEmail = dbPassword = null;
		if (u.moveToFirst())
		{
			dbEmail = u.getString(1);
			dbPassword = u.getString(2);
		}
		u.close();
		db.close();
		// if ( ! TextUtils.isEmpty(dbEmail) && ! TextUtils.isEmpty(dbPassword))
		// {
		// showProgress(true);
		//
		// Thread t;
		// (t = new Thread() {
		// @Override
		// public void run()
		// {
		// Connection c = Connection.getInstance();
		//
		// c.setAction("login");
		// c.addData("method", "auto");
		// c.addData("email", dbEmail);
		// c.addData("pass", dbPassword);
		//
		// String token = null;
		// try
		// {
		// JSONObject jsonObject = c.execute();
		// token = jsonObject.getString("token");
		// }
		// catch (JSONException e)
		// {
		// }
		//
		// if (token == null)
		// {
		// mEditTextUserLogin
		// .setError(getString(R.string.conection_error));
		// mEditTextUserLogin.requestFocus();
		// }
		//
		// if (token != null)
		// {
		// Connection.setLogin(dbEmail, dbPassword);
		// }
		// }
		// }).start();
		//
		// try
		// {
		// t.join();
		// }
		// catch (InterruptedException e)
		// {
		// }
		//
		// // We create the intent
		// Intent intent = new Intent(LaunchActivity.this,
		// MainMenuActivity.class);
		// // We start the activity
		// startActivity(intent);
		// // We do not want a logged user to go back to login
		// finish();
		// }
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * Method pressing the register button
	 */
	public void register()
	{
		// We create the intent
		final Intent intent = new Intent(LaunchActivity.this,
		RegisterActivity.class);
		intent.putExtra("login", new ResultReceiver(null)
		{

			@Override
			protected void onReceiveResult(final int resultCode,
			final Bundle resultData)
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
		mPassword = mEditTextPasswordLogin.getText().toString();

		View focusView = null;
		boolean isAcepted = true;

		if (TextUtils.isEmpty(mPassword))
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
			showProgress(true);
			mAuthTask = new UserLoginTask();
			mAuthTask.execute((Void) null);
		}
	}

	@TargetApi (Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show)
	{
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2)
		{
			final int shortAnimTime = getResources().getInteger(
			android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
			.alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter()
			{

				@Override
				public void onAnimationEnd(final Animator animation)
				{
					mLoginStatusView.setVisibility(show ? View.VISIBLE
					: View.GONE);
				}
			});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
			.alpha(show ? 0 : 1).setListener(new AnimatorListenerAdapter()
			{

				@Override
				public void onAnimationEnd(final Animator animation)
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

		private boolean	email, password, error;

		@Override
		protected Boolean doInBackground(final Void ... params)
		{
			final Connection c = Connection.getInstance();

			c.setAction("login");
			c.addData("method", "manual");
			c.addData("email", userEmail);
			c.addData("pass", StringUtils.sha1(mPassword + "--Animature"));

			final JSONObject jsonObject = c.execute();
			email = password = error = false;
			if (jsonObject != null)
			{
				try
				{
					email = jsonObject.getBoolean("email");
					password = jsonObject.getBoolean("pass");
				}
				catch (final JSONException e)
				{
				}
			}
			else
			{
				error = true;
			}
			return email && password;
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
					// TODO guardar usuario en la base de datos.
					// db.execSQL("UPDATE User SET email='" + userEmail + "',"
					// + "password='"
					// + StringUtils.sha1(password + "--Animature") + "'");
					// Connection.setLogin(userEmail,
					// StringUtils.sha1(password + "--Animature"));
				}

				// We create the intent
				final Intent intent = new Intent(LaunchActivity.this,
				MainMenuActivity.class);

				// We start the activity
				startActivity(intent);
				// We do not want a logged user to go back to login
				finish();
			}
			else
			{
				if ( ! error)
				{
					if ( ! password)
					{
						mEditTextPasswordLogin
						.setError(getString(R.string.error_incorrect_password));
						mEditTextPasswordLogin.requestFocus();
					}
					if ( ! email)
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