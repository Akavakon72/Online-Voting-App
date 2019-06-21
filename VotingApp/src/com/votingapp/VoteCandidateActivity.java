package com.votingapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.bt.utils.AllDepartments;
import com.bt.utils.Connection;
import com.bt.utils.DataHolder;
import com.bt.utils.DepartmentBean;
import com.bt.utils.PostBean;

import encdec.EncDecClass;

public class VoteCandidateActivity extends Activity {

	String response = null;
	static Socket client;
	private static int port = Connection.port;
	private static String ip = Connection.ip;
	public DataOutputStream dos;
	public DataInputStream dis;

	private boolean isVoted, isThreadComplete = false;
	private Spinner spinnerDepartment, spinnerPost, spinnerOptions;

	private AllDepartments allDepartments = DataHolder.allDepartments;
	private DepartmentBean bean;

	private String department;
	private String post;
	private String option;

	Button vote;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vote_candidate);

		spinnerDepartment = (Spinner) findViewById(R.id.spinnerDepartment);
		spinnerPost = (Spinner) findViewById(R.id.spinnerPost);
		spinnerOptions = (Spinner) findViewById(R.id.spinnerOptions);

		vote = (Button) findViewById(R.id.buttonVote);
		Button cancel = (Button) findViewById(R.id.buttonCancel);
		vote.setEnabled(false);
		String depts[] = new String[allDepartments.getSize()];

		for (int i = 0; i < allDepartments.getSize(); i++) {
			depts[i] = allDepartments.get(i).getDepartmentName();
		}

		ArrayAdapter<String> adapterDept = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.spineritem, depts);
		adapterDept.setDropDownViewResource(R.layout.spinnerdropdown);
		spinnerDepartment.setAdapter(adapterDept);

		spinnerDepartment
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					ArrayAdapter<String> adapterPost;

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {

						String item = spinnerDepartment.getSelectedItem()
								.toString();

						setBean(allDepartments.get(item));

						String posts[] = new String[bean.getPosts().size()];

						for (int i = 0; i < bean.getPosts().size(); i++) {
							posts[i] = bean.get(i).getPostName();
						}

						adapterPost = new ArrayAdapter<String>(
								getApplicationContext(), R.layout.spineritem,
								posts);
						adapterPost
								.setDropDownViewResource(R.layout.spinnerdropdown);
						spinnerPost.setAdapter(adapterPost);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});

		spinnerPost.setOnItemSelectedListener(new OnItemSelectedListener() {
			ArrayAdapter<String> adapterCand;

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				String item = spinnerPost.getSelectedItem().toString();

				DepartmentBean bean = getBean();
				PostBean postBean = bean.get(item);

				String cands[] = new String[postBean.getCandidates().size()];

				for (int i = 0; i < postBean.getCandidates().size(); i++) {
					cands[i] = postBean.get(i);
				}

				adapterCand = new ArrayAdapter<String>(getApplicationContext(),
						R.layout.spineritem, cands);
				adapterCand.setDropDownViewResource(R.layout.spinnerdropdown);
				spinnerOptions.setAdapter(adapterCand);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		spinnerOptions.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				vote.setEnabled(true);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		vote.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				vote.setEnabled(false);
				voteAction();
			}
		});

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				cancelAction();
			}
		});

	}

	private void voteAction() {

		Toast.makeText(getApplicationContext(), "Sending Your Vote",
				Toast.LENGTH_SHORT).show();

		new AsyncDoVote().execute();

	}

	private void cancelAction() {
		startActivity(new Intent(VoteCandidateActivity.this, VoteActivity.class));
		finish();
	}

	public DepartmentBean getBean() {
		return bean;
	}

	public void setBean(DepartmentBean bean) {
		this.bean = bean;
	}

	private class AsyncDoVote extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {

			department = spinnerDepartment.getSelectedItem().toString();
			post = spinnerPost.getSelectedItem().toString();
			option = spinnerOptions.getSelectedItem().toString();

			try {
				client = new Socket(ip, port);

				DataOutputStream dos = new DataOutputStream(
						client.getOutputStream());

				option = new EncDecClass().Encrypt(option);

				dos.writeUTF("VOTE");
				dos.writeUTF(department);
				dos.writeUTF(post);
				dos.writeUTF(option);
				dos.writeUTF(Connection.username);

				try {
					Thread.sleep(3000);
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

			if (response.equals("Voting Success")) {

				Toast.makeText(getApplicationContext(),
						"Voted successfully Done", Toast.LENGTH_LONG).show();

			} else {
				Toast.makeText(getApplicationContext(),
						"Voting failed.. Please try again..", Toast.LENGTH_LONG)
						.show();
			}

			vote.setEnabled(true);
		}

		@Override
		protected void onPreExecute() {

		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
	}

}
