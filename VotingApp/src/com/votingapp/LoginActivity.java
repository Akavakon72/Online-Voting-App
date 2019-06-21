package com.votingapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.bt.utils.Connection;

import encdec.EncDecClass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * 
 * @author RocKHaZarD, SHINE
 * 
 */
public class LoginActivity extends Activity {
	String response = null;
	static Socket client;
	private static int port = Connection.port;
	private static String ip = Connection.ip;
	public DataOutputStream dos;
	public DataInputStream dis;
	// Ref
	private EditText userEditText, passEditText;
	private Button loginButton, regButton, forgotpassButton;
	private Spinner typeSpinner;

	private boolean isLoginTrue = false;
	private boolean isThreadComplete = false;

	private String username;
	private String password;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		userEditText = (EditText) findViewById(R.id.editTextUser);
		passEditText = (EditText) findViewById(R.id.editTextPassLogin);
		loginButton = (Button) findViewById(R.id.buttonLogin);
		regButton = (Button) findViewById(R.id.buttonRegister);
		forgotpassButton = (Button) findViewById(R.id.buttonForgotPass);

		WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiInfo wInfo = wifiManager.getConnectionInfo();
		Connection.macAddress = wInfo.getMacAddress();
		Log.e("MAC ADDRESS==========", "" + Connection.macAddress);
		Log.e("MAC ADDRESS---------", "" + wInfo.getMacAddress());

		Log.e("Connecting to IP", "" + Connection.ip);
		Log.e(" PORT", "" + Connection.port);

		loginButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				username = userEditText.getText().toString();
				password = passEditText.getText().toString();
				// String usertype = typeSpinner.getSelectedItem().toString();

				if (!username.equals("") || !password.equals("")) {
					Toast.makeText(getApplicationContext(),
							"Checking Access Please Wait!", Toast.LENGTH_LONG)
							.show();

					password = new EncDecClass().Encrypt(password);

					new AsyncGetLogin().execute(username, password);
				} else {
					Toast.makeText(getApplicationContext(), "Fill All Fields",
							Toast.LENGTH_LONG).show();
				}
			}

		});
		regButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(LoginActivity.this,
						RegistrationActivity.class);
				startActivity(intent);
				finish();
			}
		});

		forgotpassButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LoginActivity.this,
						ForgotPassActivity.class);
				startActivity(intent);

			}
		});
	}

	private class AsyncGetLogin extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {

			try {
				client = new Socket(ip, port);

				DataOutputStream dos = new DataOutputStream(
						client.getOutputStream());

				dos.writeUTF("LOGIN");
				dos.writeUTF(params[0]);
				dos.writeUTF(params[1]);

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
				DataInputStream dis = new DataInputStream(
						client.getInputStream());
				// System.out.println("input bana");

				response = dis.readUTF();

				Log.e("Server Response:", response);

				dis.close();
				dos.flush();
				dos.close();

			} catch (IOException e1) {

				System.out.println('\n' + "Message sending fail:Network Error"
						+ e1);
			} finally {
				if (client != null) {
					try {
						client.close();
					} catch (IOException e) {
						System.err.println(e);
					}
				}
			}

			// try {
			// // System.out.println("aya");
			// client = new Socket(ip, port);
			// // System.out.println("accept hoya");
			// DataInputStream dis = new DataInputStream(
			// client.getInputStream());
			// // System.out.println("input bana");
			//
			// response = dis.readUTF();
			//
			// Log.e("Server Response:", response);
			//
			// } catch (IOException e1) {
			// e1.printStackTrace();
			// System.out.println("Message sending fail:Network Error" + e1);
			//
			// } finally {
			// try {
			// client.close();
			// } catch (IOException e) {
			// System.err.println(e);
			// }
			// }

			return "Executed";
		}

		@Override
		protected void onPostExecute(String result) {

			loginButton.setEnabled(true);

			Toast.makeText(getApplicationContext(), "Response=" + response,
					Toast.LENGTH_SHORT).show();

			if (response.equals("Access Granted")) {

				Connection.username = username;

				Intent intent = new Intent(LoginActivity.this,
						VoteActivity.class);
				startActivity(intent);
				finish();
			}
		}

		@Override
		protected void onPreExecute() {

			loginButton.setEnabled(false);
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
	}
}