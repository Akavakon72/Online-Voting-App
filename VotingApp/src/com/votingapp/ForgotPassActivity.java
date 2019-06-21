package com.votingapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.bt.utils.Connection;

import encdec.EncDecClass;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ForgotPassActivity extends Activity {

	Button changepassButton;
	EditText unameEditText, passEditText, confrimpassEditText, answerEditText;
	Spinner queSpinner;
	String uname, pass, confirmpass, question, answer;

	String response = null;
	static Socket client;
	private static int port = Connection.port;
	private static String ip = Connection.ip;
	public DataOutputStream dos;
	public DataInputStream dis;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgot_pass);

		// ref
		changepassButton = (Button) findViewById(R.id.buttonchangepass);
		unameEditText = (EditText) findViewById(R.id.editTextUserForgot);
		passEditText = (EditText) findViewById(R.id.editTextPassForgot);
		confrimpassEditText = (EditText) findViewById(R.id.editTextPassForgotConfirm);

		queSpinner = (Spinner) findViewById(R.id.spinnerSecurityQueForgot);
		answerEditText = (EditText) findViewById(R.id.editTextsecqueansForgot);

		// Set Adaptor to Spinner
		String questions[] = { "What was your childhood friend name?",
				"What was your  nickname?", "What is your best friend name?",
				"What is your pet’s name?",
				"What is your favorite Teacher name?" };
		ArrayAdapter<String> adapterDept = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.spineritem, questions);
		adapterDept.setDropDownViewResource(R.layout.spinnerdropdown);
		queSpinner.setAdapter(adapterDept);

		changepassButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				uname = unameEditText.getText().toString();
				pass = passEditText.getText().toString();
				confirmpass = confrimpassEditText.getText().toString();
				answer = answerEditText.getText().toString();
				question = queSpinner.getSelectedItem().toString();

				if (uname.equals("") || pass.equals("")
						|| confirmpass.equals("") || answer.equals("")) {

					Toast.makeText(getApplicationContext(), "Fill All Fields",
							Toast.LENGTH_SHORT).show();
					return;
				} else {
					if (pass.length() < 8) {
						// characters
						Toast.makeText(getApplicationContext(),
								"Passwords minimun 8 characters",
								Toast.LENGTH_LONG).show();
						passEditText.setError("Passwords minimun 8 characters");

						return;
					} else if (pass.equals(confirmpass)) {

						Toast.makeText(getApplicationContext(),
								"Checking User Info Please Wait",
								Toast.LENGTH_SHORT).show();

						new AsyncGetPass().execute();
					} else {
						Toast.makeText(getApplicationContext(),
								"Confirm Password Not Match!",
								Toast.LENGTH_SHORT).show();

						confrimpassEditText
								.setError("Confirm Password Not Match!");
					}
				}
			}
		});
	}

	private class AsyncGetPass extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {

			try {
				client = new Socket(ip, port);

				DataOutputStream dos = new DataOutputStream(
						client.getOutputStream());

				dos.writeUTF("FORGOT_PASS");
				dos.writeUTF(uname);

				pass = new EncDecClass().Encrypt(pass);
				dos.writeUTF(pass);

				dos.writeUTF(question.trim());
				dos.writeUTF(answer);

				try {
					Thread.sleep(1000);
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

			Toast.makeText(getApplicationContext(), "Response=" + response,
					Toast.LENGTH_LONG).show();

			if (response.trim().equals("Password Change Succssfully")) {
				Intent intent = new Intent(ForgotPassActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			}

			changepassButton.setEnabled(true);

		}

		@Override
		protected void onPreExecute() {
			changepassButton.setEnabled(true);
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
	}

}
