package com.votingapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bt.utils.Connection;

import encdec.EncDecClass;

public class RegistrationActivity extends Activity {

	String response = null;
	static Socket client;
	private static int port = Connection.port;
	private static String ip = Connection.ip;
	public DataOutputStream dos;
	public DataInputStream dis;

	private EditText nameEdit;
	private EditText usernameEdit;
	private EditText emailEdit;
	private EditText fatherEdit;
	private EditText motherEdit;
	private EditText aadharEdit;
	private EditText voterEdit;
	private EditText addressEdit;
	private TextView dobEdit;
	private EditText passwordEdit;
	private EditText cpasswordEdit;
	private EditText secqueansEdit;
	private Spinner secqueSpinner;

	private Button saveButton;
	private Button cancelButton;
	private Button dateButton;

	private DatePicker date_picker;
	private int year = 1997;
	private int month = 0;
	private int day = 1;
	TableLayout table_layout;
	static final int DATE_DIALOG_ID = 100;

	private String name;
	private String user;
	private String email;
	private String father;
	private String mother;
	private String aadhar;
	private String voter;
	private String address;
	private String dob;
	private String pass;
	private String cpass;
	private String question;
	private String answer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);

		initialize();
		setHandlers();
	}

	// @Override
	// public void onBackPressed() {
	// onPause();
	// }

	@Override
	public void onPause() {
		super.onPause();
	}

	private void initialize() {
		// Edit Text
		nameEdit = (EditText) findViewById(R.id.editTextName);
		usernameEdit = (EditText) findViewById(R.id.editTextUserName);
		emailEdit = (EditText) findViewById(R.id.editTextEmail);
		fatherEdit = (EditText) findViewById(R.id.editTextFatherName);
		motherEdit = (EditText) findViewById(R.id.editTextMotherName);
		aadharEdit = (EditText) findViewById(R.id.editTextAadhar);
		voterEdit = (EditText) findViewById(R.id.editTextVoterID);
		addressEdit = (EditText) findViewById(R.id.editTextAddress);
		dobEdit = (TextView) findViewById(R.id.editTextDOB);
		passwordEdit = (EditText) findViewById(R.id.editTextPass);
		cpasswordEdit = (EditText) findViewById(R.id.editTextConfirmPass);
		secqueansEdit = (EditText) findViewById(R.id.editTextsecqueans);
		// Spinner
		secqueSpinner = (Spinner) findViewById(R.id.spinnerSecurityQue);
		// Button
		saveButton = (Button) findViewById(R.id.buttonSave);
		cancelButton = (Button) findViewById(R.id.buttonCancel);
		dateButton = (Button) findViewById(R.id.buttonDate);
		// Date Picker
		date_picker = (DatePicker) findViewById(R.id.date_picker);

		// Set Adaptor to Spinner
		String questions[] = { "What was your childhood friend name?",
				"What was your  nickname?", "What is your best friend name?",
				"What is your pet’s name?",
				"What is your favorite Teacher name?" };
		ArrayAdapter<String> adapterDept = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.spineritem, questions);
		adapterDept.setDropDownViewResource(R.layout.spinnerdropdown);
		secqueSpinner.setAdapter(adapterDept);

	}

	private void setHandlers() {
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				saveAction();
			}
		});
		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				cancelAction();
			}
		});

		dateButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});

	}

	private void saveAction() {

		name = nameEdit.getText().toString();
		user = usernameEdit.getText().toString();
		email = emailEdit.getText().toString();
		father = fatherEdit.getText().toString();
		mother = motherEdit.getText().toString();
		aadhar = aadharEdit.getText().toString();
		voter = voterEdit.getText().toString();
		address = addressEdit.getText().toString();
		dob = dobEdit.getText().toString();

		pass = passwordEdit.getText().toString();
		cpass = cpasswordEdit.getText().toString();

		question = secqueSpinner.getSelectedItem().toString();
		answer = secqueansEdit.getText().toString();

		if (name.equals("") || user.equals("") || email.equals("")
				|| father.equals("") || mother.equals("") || aadhar.equals("")
				|| voter.equals("") || address.equals("") || dob.equals("")
				|| cpass.equals("") || answer.equals("")) {

			Toast.makeText(getApplicationContext(), "Fill All Fields!",
					Toast.LENGTH_LONG).show();
			return;
		} else if (!isValidEmail(email)) {
			// emailEditText.setError("Invalid Email");
			Toast.makeText(getApplicationContext(), "Invalid Email Address",
					Toast.LENGTH_SHORT).show();
			emailEdit.setError("Invalid Email Address");
			return;
		} else if (aadhar.length() < 12) {
			Toast.makeText(getApplicationContext(), "Invalid Aadhar Number ",
					Toast.LENGTH_LONG).show();

			aadharEdit.setError("Invalid Aadhar Number");
			return;

		} else if (user.length() < 8) {
			Toast.makeText(getApplicationContext(), "User Name too short",
					Toast.LENGTH_LONG).show();

			usernameEdit.setError("User Name too short minimum 8 character");
			return;
		} else if (pass.length() < 8) {
			// characters
			Toast.makeText(getApplicationContext(),
					"Passwords minimun 8 characters", Toast.LENGTH_LONG).show();
			passwordEdit.setError("Passwords minimun 8 characters");

			return;
		} else if (!pass.equals(cpass)) {
			Toast.makeText(getApplicationContext(),
					"Passwords do not match..!!", Toast.LENGTH_LONG).show();
			cpasswordEdit.setError("Passwords do not match..!!");
			return;

		} else if (dob.equals("DOB")) {
			Toast.makeText(getApplicationContext(), "Select DOB..!",
					Toast.LENGTH_LONG).show();
			dateButton.setError("Select DOB..!!");
			return;

		} else {
			pass = new EncDecClass().Encrypt(pass);
			Toast.makeText(getApplicationContext(), "Wait For Response",
					Toast.LENGTH_LONG).show();
			new AsyncGetRegistered().execute();
		}

	}

	private void cancelAction() {
		startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
		finish();
	}

	private class AsyncGetRegistered extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {

			try {
				client = new Socket(ip, port);

				DataOutputStream dos = new DataOutputStream(
						client.getOutputStream());

				dos.writeUTF("REGISTER");
				dos.writeUTF(name);
				dos.writeUTF(user);
				dos.writeUTF(email);
				dos.writeUTF(father);
				dos.writeUTF(mother);
				dos.writeUTF(aadhar);
				dos.writeUTF(voter);
				dos.writeUTF(address);
				dos.writeUTF(dob);
				dos.writeUTF(pass);
				dos.writeUTF(Connection.macAddress);

				dos.writeUTF(question);
				dos.writeUTF(answer);

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

			return "Executed";
		}

		@Override
		protected void onPostExecute(String result) {

			Toast.makeText(getApplicationContext(), "Response=" + response,
					Toast.LENGTH_SHORT).show();

			if (response.trim().equals("Registration Successfull")) {
				Intent intent = new Intent(RegistrationActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			}
		}

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
	}

	private boolean isValidEmail(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case DATE_DIALOG_ID:
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener, year, month,
					day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth + 1;
			day = selectedDay;

			// set selected date into Text View
			dobEdit.setText(new StringBuilder().append(day).append("-")
					.append(month).append("-").append(year).append(" "));

			// set selected date into Date Picker
			date_picker.init(year, month, day, null);

		}
	};
}
