package com.votingapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.UUID;

import org.w3c.dom.Text;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bt.utils.AllDepartments;
import com.bt.utils.Connection;
import com.bt.utils.DataHolder;

public class VoteActivity extends Activity {

	String response = null;
	static Socket client;
	private static int port = Connection.port;
	private static String ip = Connection.ip;
	public DataOutputStream dos;
	public DataInputStream dis;

	Button buttonVote;
	// private final String TAG = RegisterActivity.class.getName();
	private boolean isVoteDataCollected, isThreadComplete = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vote);

		buttonVote = (Button) findViewById(R.id.buttonVote);

		TextView unameTextView = (TextView) findViewById(R.id.TVunamevote);

		unameTextView.setText("Welcome " + Connection.username);

		buttonVote.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				voteAction();
			}
		});
	}

	private void voteAction() {

		Toast.makeText(getApplicationContext(), "Collecting Data Please Wait!",
				Toast.LENGTH_LONG).show();

		new AsyncGetVotingData().execute();

		while (!isThreadComplete) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (isVoteDataCollected) {
			Toast.makeText(getApplicationContext(), "Data Collected..",
					Toast.LENGTH_LONG).show();
			startActivity(new Intent(VoteActivity.this,
					VoteCandidateActivity.class));
			finish();
		} else {
			Toast.makeText(getApplicationContext(), "Data Collection Error..",
					Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * Thread that handles an incoming connection. Adapted from
	 * http://developer.android.com/guide/topics/wireless/bluetooth.html
	 */
	private class AsyncGetVotingData extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			try {
				client = new Socket(ip, port);
				// If a connection was accepted
				// Do work to manage the connection (in a separate thread)

				// Read the incoming string.
				DataOutputStream outputStream = new DataOutputStream(
						client.getOutputStream());

				outputStream.writeUTF("GET_ALL_DATA");
				outputStream.flush();

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}

				ObjectInputStream inputStream = new ObjectInputStream(
						client.getInputStream());

				collectData(inputStream);

				outputStream.close();
				client.close();

				isVoteDataCollected = true;
				isThreadComplete = true;
			} catch (UnknownHostException e) {
				Log.e("Ellol Ala..", "Error obtaining InputStream from socket");
				isVoteDataCollected = false;
				e.printStackTrace();
			} catch (IOException e) {
				Log.e("Ellol Ala..", "Error obtaining InputStream from socket");
				isVoteDataCollected = false;
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {

		}

		@Override
		protected void onPreExecute() {

		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
	}

	private void collectData(ObjectInputStream inputStream) {
		try {

			DataHolder.allDepartments = (AllDepartments) inputStream
					.readObject();
			inputStream.close();
		} catch (OptionalDataException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
