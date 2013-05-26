package com.jdix.animature.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.jdix.animature.entities.User;

/**
 * @author Razican (Iban Eguia)
 */
public class Connection {

	private User				user;
	private static Connection	instance;
	private List<NameValuePair>	data;
	private HttpPost			query;
	private final HttpClient	connection;
	private String				token;
	private String				action;

	private Connection()
	{
		connection = new DefaultHttpClient();
		newQuery();
	}

	private void newQuery()
	{
		query = new HttpPost("http://jdix.razican.com/animature/api.php");
		query.setHeader("User-Agent", "Animature/1.0.0");
		action = null;
		data = new ArrayList<NameValuePair>();

		if (token != null)
		{
			addData("token", token);
		}
	}

	private void setToken(final String token)
	{
		this.token = token;
	}

	/**
	 * @param key Key for the new data
	 * @param value Value for the new data
	 */
	public void addData(final String key, final String value)
	{
		data.add(new BasicNameValuePair(key, value));
	}

	/**
	 * @return JSONObject containing the reply from the server
	 */
	public JSONObject execute()
	{
		try
		{
			query.setEntity(new UrlEncodedFormEntity(data));
		}
		catch (final UnsupportedEncodingException e)
		{
		}

		HttpResponse response = null;
		try
		{
			response = connection.execute(query);
		}
		catch (final ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}

		if (response != null)
		{
			final int responseCode = response.getStatusLine().getStatusCode();
			String responseBody = null;
			switch (responseCode)
			{
				case 200:
					final HttpEntity entity = response.getEntity();
					if (entity != null)
					{
						try
						{
							responseBody = EntityUtils.toString(entity);
						}
						catch (final ParseException e)
						{
						}
						catch (final IOException e)
						{
						}
					}
				break;
			}

			boolean regenerate = false;
			JSONObject jsonObject = null;
			try
			{
				jsonObject = new JSONObject(responseBody);
				regenerate = jsonObject.getBoolean("regenerate");
				setToken(jsonObject.getString("token"));
			}
			catch (final JSONException e)
			{
			}

			if (regenerate)
			{
				if (user != null)
				{
					final List<NameValuePair> d = data;
					final String a = action;

					setToken(null);
					newQuery();

					setAction("login");
					addData("method", "auto");
					addData("email", user.getEmail());
					addData("pass", user.getPassword());

					final JSONObject login = execute();
					try
					{
						setToken(login.getString("token"));
					}
					catch (final JSONException e)
					{
						e.printStackTrace();
					}

					action = a;
					data = d;

					final Iterator<NameValuePair> i = d.iterator();

					while (i.hasNext())
					{
						final NameValuePair nvp = i.next();
						if (nvp.getName().equals("token"))
						{
							d.remove(nvp);
							break;
						}
					}

					addData("token", token);

					return execute();
				}
				else
				{
					return null;
				}
			}
			else
			{
				newQuery();
				return jsonObject;
			}
		}
		else
		{
			return null;
		}
	}

	/**
	 * @param action The action to perform in the server
	 */
	public void setAction(final String action)
	{
		this.action = action;
		addData("action", action);
	}

	/**
	 * @param user - The user logged in
	 */
	public void setUser(final User user)
	{
		this.user = user;
	}

	/**
	 * @return Current Connection
	 */
	public static Connection getInstance()
	{
		if (instance == null)
		{
			instance = new Connection();
		}

		return instance;
	}
}