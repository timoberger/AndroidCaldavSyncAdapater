package org.gege.caldavsyncadapter.activities;


import org.gege.caldavsyncadapter.R;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class StatsActivity extends PreferenceActivity {
	private static final String TAG = "StatsActivity";
	
	private AccountManager mAccountManager;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Account account = null;
		
		super.onCreate(savedInstanceState);
		
		mAccountManager = AccountManager.get(this);
		Bundle Extras = this.getIntent().getExtras();
		account = (Account) Extras.get("account");
		Log.v(TAG, "account name: " + account.name);
		Log.v(TAG, "account type: " + account.type);
		   
		//SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(PreferenceDemoActivity.this);
		//textView = (TextView) findViewById(R.id.txtPrefs);
		//mUserView.setText(account.name);
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
