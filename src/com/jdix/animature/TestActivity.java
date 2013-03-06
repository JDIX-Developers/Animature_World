package com.jdix.animature;

import org.json.JSONException;
import org.json.JSONObject;

import com.jdix.animature.utils.Connection;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class TestActivity extends Activity {

	private TextView	replyView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		replyView = (TextView) findViewById(R.id.reply);

		(new Thread() {
			public void run() {
				Connection c = Connection.getInstance();

				c.setAction("test");
				JSONObject j = c.execute();

				String r = null;
				try
				{
					r = j.getString("text");
				}
				catch (JSONException e)
				{
					e.printStackTrace();
				}

				replyView.setText(r);
			}
		}).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
}
