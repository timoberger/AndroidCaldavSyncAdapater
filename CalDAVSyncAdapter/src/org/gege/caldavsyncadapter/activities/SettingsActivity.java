package org.gege.caldavsyncadapter.activities;


import org.gege.caldavsyncadapter.R;
import org.gege.caldavsyncadapter.authenticator.AuthenticatorActivity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends Activity {
	private static final String TAG = "PrefsActivity";
	
	private AccountManager mAccountManager;
	private Account mAccount = null;
	private String mOldAccountname = "";
	private String mOldUsername = "";
	private String mOldPassword = "";
	private String mOldURL = "";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		//addPreferencesFromResource(R.xml.prefs);
		setContentView(R.layout.activity_settings);
		
		mAccountManager = AccountManager.get(this);
		Bundle Extras = this.getIntent().getExtras();
		mAccount = (Account) Extras.get("account");
		Log.v(TAG, "account name: " + mAccount.name);
		Log.v(TAG, "account type: " + mAccount.type);
		mOldAccountname = mAccount.name;
		
		//TODO: account name is invisible
		this.findViewById(R.id.tableRow1).setVisibility(android.view.View.INVISIBLE);
		
		
		EditText mAccountView = (EditText) findViewById(R.id.editAccountName);
		EditText mUserView = (EditText) findViewById(R.id.editUserName);
		EditText mPassView = (EditText) findViewById(R.id.editPassword);
		EditText mUrlView = (EditText) findViewById(R.id.editUrl);

		String UserDataVersion = mAccountManager.getUserData(mAccount, AuthenticatorActivity.USER_DATA_VERSION);
		if (UserDataVersion == null) {
			mOldUsername = mAccount.name;
		} else {
			mOldUsername = mAccountManager.getUserData(mAccount, AuthenticatorActivity.USER_DATA_USERNAME);
		}
		mOldPassword = mAccountManager.getPassword(mAccount);
		mOldURL = mAccountManager.getUserData(mAccount, AuthenticatorActivity.USER_DATA_URL_KEY);
		
		mAccountView.setText(mAccount.name);
		mUserView.setText(mOldUsername);
		mPassView.setText(mOldPassword);
		mUrlView.setText(mOldURL);
				
		findViewById(R.id.editOK).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						SaveSettings();
					}
				});

	}
	
	private boolean SaveSettings() {
		boolean Result = false;
		
		EditText mAccountView = (EditText) findViewById(R.id.editAccountName);
		EditText mUserView = (EditText) findViewById(R.id.editUserName);
		EditText mPassView = (EditText) findViewById(R.id.editPassword);
		EditText mUrlView = (EditText) findViewById(R.id.editUrl);

		String NewAccountname = mAccountView.getText().toString();
		String NewUsername = mUserView.getText().toString();
		String NewPassword = mPassView.getText().toString();
		String NewURL = mUrlView.getText().toString();
		
		if (!mOldUsername.equals(NewUsername)) {
			mAccountManager.setUserData(mAccount, AuthenticatorActivity.USER_DATA_USERNAME, NewUsername);
			mAccountManager.setUserData(mAccount, AuthenticatorActivity.USER_DATA_VERSION, AuthenticatorActivity.CURRENT_USER_DATA_VERSION);
		}
		if (!mOldPassword.equals(NewPassword)) {
			mAccountManager.setPassword(mAccount, NewPassword);
		}
		if (!mOldURL.equals(NewURL)) {
			mAccountManager.setUserData(mAccount, AuthenticatorActivity.USER_DATA_URL_KEY, NewURL);
		}
		if (!mOldAccountname.equals(NewAccountname)) {
			//not implemented
		}
		
		
		this.finish();
		return Result;
	}
	
	/*
	 * http://androidresearch.wordpress.com/2012/03/09/creating-a-preference-activity-in-android/
	 * 
	 * http://stackoverflow.com/questions/11860551/deprecated-preference-activity-method
	 * 
	 * http://stackoverflow.com/questions/10658330/show-settings-under-accounts-sync-menu-for-android-app
	 * http://stackoverflow.com/questions/5486228/how-do-we-control-an-android-sync-adapter-preference
	 * 
	 * http://stackoverflow.com/questions/14810393/start-activity-from-preference-screen-intent-defined-in-xml-file
	 * http://stackoverflow.com/questions/3727249/preferencescreen-intent-exception-flag-activity-new-task/3751306#3751306
	 */

}
