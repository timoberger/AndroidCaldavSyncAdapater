package org.gege.caldavsyncadapter.activities;


import org.gege.caldavsyncadapter.R;
import org.gege.caldavsyncadapter.authenticator.AuthenticatorActivity;
import org.gege.caldavsyncadapter.syncadapter.logging.LogEntry;
import org.gege.caldavsyncadapter.syncadapter.logging.Logger;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class StatsActivity extends Activity {
	private static final String TAG = "StatsActivity";
	
	private AccountManager mAccountManager;
	private Account mAccount = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);

		findViewById(R.id.btnClearLogger).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						clearLogger();
					}
				});
		
		
		mAccountManager = AccountManager.get(this);
		Bundle Extras = this.getIntent().getExtras();
		mAccount = (Account) Extras.get("account");
		Log.v(TAG, "account name: " + mAccount.name);
		Log.v(TAG, "account type: " + mAccount.type);
		
		TableRow NewRow = null;
		TextView EntryNrView = null;
		TextView EntryTitleView = null;
		TextView EntryTextView = null;
				TableLayout tblStatsListView = (TableLayout) this.findViewById(R.id.tblStatsList);
		String jsonLogger = mAccountManager.getUserData(mAccount, AuthenticatorActivity.USER_DATA_LOGGER);
		Logger logger = new Logger(this, jsonLogger);
		for (LogEntry le : logger.Entries) {
			NewRow = new TableRow(this);
			
			EntryNrView = new TextView(this);
			EntryNrView.setText(String.valueOf(le.getNr()));
			NewRow.addView(EntryNrView);
			
			EntryTitleView = new TextView(this);
			EntryTitleView.setText(le.getTitle());
			NewRow.addView(EntryTitleView);

			EntryTextView = new TextView(this);
			EntryTextView.setText(le.getText());
			NewRow.addView(EntryTextView);
			
			tblStatsListView.addView(NewRow);
		}

		//SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(PreferenceDemoActivity.this);
		//textView = (TextView) findViewById(R.id.txtPrefs);
		//mUserView.setText(account.name);
	}
	
	private boolean clearLogger() {
		boolean Result = false;
		
		mAccountManager.setUserData(mAccount, AuthenticatorActivity.USER_DATA_LOGGER, "");
		
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
