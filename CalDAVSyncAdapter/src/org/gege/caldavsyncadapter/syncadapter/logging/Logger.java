package org.gege.caldavsyncadapter.syncadapter.logging;

import java.util.ArrayList;

import org.gege.caldavsyncadapter.syncadapter.notifications.NotificationsHelper;

import android.content.Context;

public class Logger {
	private ArrayList<LogEntry> mList = new ArrayList<LogEntry>();
	private Context mContext = null;
	private int mCount = 0;
	
	private boolean mNotify = true;
		
	public Logger(Context context) {
		this.mContext = context;
	}
	
	public boolean NewEntry(String Title, String Text, int SourceID) {
		boolean Result = false;
		mCount += 1;
		
		if (this.mNotify)
			if (this.mContext != null)
				NotificationsHelper.signalSyncErrors(this.mContext, Title, Text);
		
		LogEntry le = new LogEntry(Title, Text, mCount, SourceID);
		mList.add(le);
		
		return Result;
	}
}
