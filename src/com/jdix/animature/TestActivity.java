package com.jdix.animature;

import org.json.JSONException;
import org.json.JSONObject;

import com.jdix.animature.utils.Connection;

import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TestActivity extends Activity {

	private EditText	mEmailView;
	private EditText	mPasswordView;
	private TextView	textView;
	private Button		button;
	private String		dbEmail, dbPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		mEmailView = (EditText) findViewById(R.id.editText1);
		mPasswordView = (EditText) findViewById(R.id.editText2);
		textView = (TextView) findViewById(R.id.textView1);
		button = (Button) findViewById(R.id.button1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}
	
	public void probar(View view)
	{
		textView.setText("Bien!");
		if (!TextUtils.isEmpty(dbEmail) && !TextUtils.isEmpty(dbPassword))
		{
			textView.setText("Conectando...");
			Thread t;
			(t = new Thread() {
				@Override
				public void run() {
					Connection c = Connection.getInstance();

					c.setAction("login");
					c.addData("method", "auto");
					c.addData("email", dbEmail);
					c.addData("pass", dbPassword);

					String newLang = null, token = null;
					try
					{
						JSONObject jsonObject = c.execute();
						newLang = jsonObject.getString("lang");
						token = jsonObject.getString("token");
					}
					catch (JSONException e)
					{
					}

					if (token != null)
					{
						Connection.setLogin(dbEmail, dbPassword);
					}
				}
			}).start();

			textView.setText(dbEmail + dbPassword);

			try
			{
				t.join();
			}
			catch (InterruptedException e)
			{
			}
		}
	}
}
