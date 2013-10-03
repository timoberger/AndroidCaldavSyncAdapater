package org.gege.caldavsyncadapter.activities;

import org.gege.caldavsyncadapter.R;
import org.gege.caldavsyncadapter.authenticator.AuthenticatorActivity;
import org.gege.caldavsyncadapter.caldav.entities.CalendarList;
import org.gege.caldavsyncadapter.caldav.entities.DavCalendar;
import org.gege.caldavsyncadapter.caldav.entities.DavCalendar.CalendarSource;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.ContentProviderClient;
import android.os.Bundle;
import android.provider.CalendarContract.Calendars;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TableLayout;
import android.widget.TableRow;

public class CalendarlistActivity  extends Activity {
	private static final String TAG = "CalendarlistActivity";

	private Account mAccount = null;
	private AccountManager mAccountManager = null;
	private ContentProviderClient mProvider = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		TableRow NewRow = null;
		CheckBox NewCheckbox = null;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendarlist);
		
		mProvider = this.getContentResolver().acquireContentProviderClient(Calendars.CONTENT_URI);
			
		mAccountManager = AccountManager.get(this);
		Bundle Extras = this.getIntent().getExtras();
		mAccount = (Account) Extras.get("account");
		String URL = mAccountManager.getUserData(mAccount, AuthenticatorActivity.USER_DATA_URL_KEY);
		
		TableLayout tblCalendarListView = (TableLayout) this.findViewById(R.id.tblCalendarList);
		
		CalendarList androidCalList = new CalendarList(mAccount, mProvider, CalendarSource.Android, URL);
		androidCalList.readCalendarFromClient();
		
		for (DavCalendar calendar : androidCalList.getCalendarList()) {
			NewRow = new TableRow(this);
			NewCheckbox = new CheckBox(this);
			NewCheckbox.setText(calendar.getCalendarDisplayName());
			NewCheckbox.setTag(calendar);
			NewCheckbox.setChecked(calendar.getAndroidCalendarIsSyncEnabled());
			NewRow.addView(NewCheckbox);
			tblCalendarListView.addView(NewRow);
			
			NewCheckbox.setOnCheckedChangeListener(
				new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						CalendarChanged((DavCalendar) buttonView.getTag(), isChecked);
					}
				}
			);
		}
	}
	
	private boolean CalendarChanged(DavCalendar calendar, boolean isChecked) {
		boolean Result = false;
		calendar.setAndroidCalendarIsSyncEnabled(isChecked, true);
		
		return Result;
	}
}
