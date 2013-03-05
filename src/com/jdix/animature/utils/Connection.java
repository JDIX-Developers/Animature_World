package com.jdix.animature.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
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
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class Connection {

	private static Connection	instance;
	private static Context		context;
	private static String		loginEmail;
	private static String		loginPassword;
	private URI					uri;
	private List<NameValuePair>	data;
	private HttpPost			query;
	private HttpClient			connection;
	private String				token;
	private String				action;

	private Connection()
	{
		try
		{
			uri = new URI("http://jdix.razican.com/animature/api.php");
		}
		catch (URISyntaxException e)
		{
		}

		connection = new MuHttpClient();

		newQuery();
	}

	private void newQuery()
	{
		query = new HttpPost();
		query.setURI(uri);
		query.setHeader("User-Agent", "Animature/1.0.0");
		action = null;
		data = new ArrayList<NameValuePair>();

		if (token != null)
		{
			addData("token", token);
		}
	}

	private void setToken(String token)
	{
		this.token = token;
	}

	public void addData(String key, String value)
	{
		data.add(new BasicNameValuePair(key, value));
	}

	public JSONObject execute()
	{
		try
		{
			query.setEntity(new UrlEncodedFormEntity(data));
		}
		catch (UnsupportedEncodingException e)
		{
		}

		HttpResponse response = null;
		try
		{
			response = connection.execute(query);
		}
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		int responseCode = response.getStatusLine().getStatusCode();
		String responseBody = null;
		switch (responseCode)
		{
			case 200:
				HttpEntity entity = response.getEntity();
				if (entity != null)
				{
					try
					{
						responseBody = EntityUtils.toString(entity);
					}
					catch (ParseException e)
					{
					}
					catch (IOException e)
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
		catch (JSONException e)
		{
		}

		if (regenerate)
		{
			if (loginEmail != null && loginPassword != null)
			{
				List<NameValuePair> d = data;
				String a = action;

				setToken(null);
				newQuery();

				setAction("login");
				addData("method", "auto");
				addData("email", loginEmail);
				addData("pass", loginPassword);

				JSONObject login = execute();
				try
				{
					setToken(login.getString("token"));
				}
				catch (JSONException e)
				{
					e.printStackTrace();
				}

				System.err.println("Token: " + token);

				action = a;
				data = d;

				Iterator<NameValuePair> i = d.iterator();

				while (i.hasNext())
				{
					NameValuePair nvp = i.next();
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

	public String getToken() {
		return token;
	}

	public void setAction(String action) {
		this.action = action;
		addData("action", action);
	}

	public boolean isLoggedIn() {
		return token != null;
	}

	public static void setContext(Context c) {
		context = c;
	}

	public static void setLogin(String email, String pass) {
		loginEmail = email;
		loginPassword = pass;
	}

	public static Connection getInstance() {
		if (instance == null)
		{
			instance = new Connection();
		}

		return instance;
	}

	// HTTP Client
	private class MuHttpClient extends DefaultHttpClient {
		final Context	context;

		public MuHttpClient()
		{
			this.context = Connection.context;
		}

		@Override
		protected ClientConnectionManager createClientConnectionManager() {
			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			// Register for port 443 our SSLSocketFactory with our keystore
			// to the ConnectionManager
			// registry.register(new Scheme("https", newSslSocketFactory(),
			// 443));
			return new SingleClientConnManager(getParams(), registry);
		}

		// private SSLSocketFactory newSslSocketFactory()
		// {
		// try
		// {
		// // Get an instance of the Bouncy Castle KeyStore format
		// KeyStore trusted = KeyStore.getInstance("BKS");
		// // Get the raw resource, which contains the keystore with
		// // your trusted certificates (root and any intermediate certs)
		// InputStream in =
		// context.getResources().openRawResource(R.raw.keystore);
		// try
		// {
		// // Initialize the keystore with the provided trusted certificates
		// // Also provide the password of the keystore
		// trusted.load(in, "miniunis".toCharArray());
		// }
		// finally
		// {
		// in.close();
		// }
		// // Pass the keystore to the SSLSocketFactory. The factory is
		// responsible
		// // for the verification of the server certificate.
		// SSLSocketFactory sf = new SSLSocketFactory(trusted);
		// // Hostname verification from certificate
		// //
		// http://hc.apache.org/httpcomponents-client-ga/tutorial/html/connmgmt.html#d4e506
		// sf.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
		// return sf;
		// }
		// catch (Exception e)
		// {
		// throw new AssertionError(e);
		// }
		// }
	}
}