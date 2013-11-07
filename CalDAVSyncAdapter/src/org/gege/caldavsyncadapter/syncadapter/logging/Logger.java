package org.gege.caldavsyncadapter.syncadapter.logging;

import java.util.ArrayList;

import org.gege.caldavsyncadapter.syncadapter.notifications.NotificationsHelper;
import org.json.JSONException;

import android.content.Context;

public class Logger {
	public ArrayList<LogEntry> Entries = new ArrayList<LogEntry>();
	private Context mContext = null;
	
	private boolean mNotify = false;
		
	public Logger(Context context) {
		this.mContext = context;
	}
	
	public Logger(Context context, String jsonString) {
		this.mContext = context;
		try {
			org.json.JSONArray Array = new org.json.JSONArray(jsonString);
			
			for (int i = 0; i < Array.length(); i++) { 
				org.json.JSONObject le = Array.getJSONObject(i);
				this.Entries.add(new LogEntry(le));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	
	public boolean NewEntry(String Title, String Text, int SourceID) {
		boolean Result = false;
		
		if (this.mNotify)
			if (this.mContext != null)
				NotificationsHelper.signalSyncErrors(this.mContext, Title, Text);
		
		LogEntry le = new LogEntry(Title, Text, this.Entries.size() + 1, SourceID);
		Entries.add(le);
		
		return Result;
	}
	
	public org.json.JSONArray getAsJson() {
		org.json.JSONArray Result = new org.json.JSONArray();

		for (LogEntry le : this.Entries) {
			Result.put(le.getAsJson());
		}
		
		return Result;
	}
}
