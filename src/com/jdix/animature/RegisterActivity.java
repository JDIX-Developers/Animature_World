package com.jdix.animature;

import org.json.JSONException;
import org.json.JSONObject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jdix.animature.utils.Connection;
import com.jdix.animature.utils.Database;
import com.jdix.animature.utils.StringUtils;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class RegisterActivity extends Activity {

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask	mAuthTask	= null;

	// Values for email and password at the time of the login attempt.
	private String			mUsername;
	private String			mEmail;
	private String			mPassword1;
	private String			mPassword2;

	// UI references.
	private EditText		mUsernameView;
	private EditText		mPasswordView1;
	private EditText		mPasswordView2;
	private EditText		mEmailView;
	private View			mLoginFormView;
	private View			mLoginStatusView;
	private TextView		mLoginStatusMessageView;

	private SQLiteDatabase	db;

	private ResultReceiver	login;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_register);

		db = (new Database(this, "AnimatureWorldDB", null, 1))
				.getWritableDatabase();

		login = (ResultReceiver) getIntent().getExtras().get("login");

		// Set up the login form.
		mUsernameView = (EditText) findViewById(R.id.userName);
		mEmailView = (EditText) findViewById(R.id.email);
		mPasswordView1 = (EditText) findViewById(R.id.password1);
		mPasswordView2 = (EditText) findViewById(R.id.password2);

		findViewById(R.id.register).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view)
					{
						attemptRegister();
					}
				});

		mLoginFormView = findViewById(R.id.login_form);
		mLoginStatusView = findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * Attempts to register the account specified by the login form. If there
	 * are form errors (invalid email, missing fields, etc.), the errors are
	 * presented and no actual login attempt is made.
	 */
	public void attemptRegister()
	{
		if (mAuthTask != null)
		{
			return;
		}

		// Reset errors.
		mUsernameView.setError(null);
		mPasswordView1.setError(null);
		mPasswordView2.setError(null);
		mEmailView.setError(null);

		// Store values at the time of the login attempt.
		mUsername = mUsernameView.getText().toString();
		mPassword1 = mPasswordView1.getText().toString();
		mPassword2 = mPasswordView2.getText().toString();
		mEmail = mEmailView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid email address.
		if (TextUtils.isEmpty(mEmail))
		{
			mEmailView.setError(getString(R.string.error_field_required));
			focusView = mEmailView;
			cancel = true;
		}
		else if ( ! Patterns.EMAIL_ADDRESS.matcher(mEmail).matches())
		{
			mEmailView.setError(getString(R.string.error_invalid_email));
			focusView = mEmailView;
			cancel = true;
		}

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword1))
		{
			mPasswordView1.setError(getString(R.string.error_field_required));
			focusView = mPasswordView1;
			cancel = true;
		}
		else if (mPassword1.length() < 4 || mPassword1.length() > 15)
		{
			mPasswordView1.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView1;
			cancel = true;
		}
		else if ( ! mPassword1.equals(mPassword2))
		{
			mPasswordView2
					.setError(getString(R.string.error_not_same_password));
			focusView = mPasswordView2;
			cancel = true;
		}

		// Check for a valid username.
		if (TextUtils.isEmpty(mUsername))
		{
			mUsernameView.setError(getString(R.string.error_field_required));
			focusView = mUsernameView;
			cancel = true;
		}

		if (cancel)
		{
			// There was an error; don't attempt login and focus the first
			// form field with an error.
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

	/**
	 * Shows the progress UI and hides the login form.
	 */
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

		private boolean	user, email, error;

		@Override
		protected Boolean doInBackground(Void... params)
		{
			Connection c = Connection.getInstance();

			c.setAction("register");
			c.addData("user", mUsername);
			c.addData("email", mEmail);
			c.addData("pass", StringUtils.sha1(mPassword1 + "--AnimatureWorld"));

			JSONObject jsonObject = c.execute();
			user = email = error = false;
			if (jsonObject != null)
			{
				try
				{
					user = jsonObject.getBoolean("user");
					email = jsonObject.getBoolean("email");
				}
				catch (JSONException e)
				{
				}
			}
			else
			{
				error = true;
			}

			return user && email;
		}

		@Override
		protected void onPostExecute(final Boolean success)
		{
			mAuthTask = null;
			showProgress(false);

			if (success)
			{
				// db.execSQL("UPDATE user SET email='" + mEmail + "',"
				// + "password='"
				// + StringUtils.sha1(mPassword1 + "--MiniunisHUB") + "'");

				Connection.setLogin(mEmail,
						StringUtils.sha1(mPassword1 + "--Animature"));

				// We create the intent
				Intent intent = new Intent(RegisterActivity.this,
						MainMenuActivity.class);

				// We start the activity
				startActivity(intent);
				// We do not want a logged user to go back to login
				login.send(1, new Bundle());
				finish();
			}
			else
			{
				if ( ! error)
				{
					if ( ! email)
					{
						mEmailView
								.setError(getString(R.string.error_invalid_email));
						mEmailView.requestFocus();
					}
					if ( ! user)
					{
						mUsernameView
								.setError(getString(R.string.error_user_used));
						mUsernameView.requestFocus();
					}
				}
				else
				{
					mEmailView.setError(getString(R.string.error_connection));
					mEmailView.requestFocus();
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