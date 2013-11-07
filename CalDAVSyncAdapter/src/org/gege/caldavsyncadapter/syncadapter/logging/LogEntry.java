package org.gege.caldavsyncadapter.syncadapter.logging;

import java.util.Date;

import org.json.JSONException;

public class LogEntry {
	private String mTitle = "";
	private String mText = "";
	private int mNr = 0;
	private Date mDate;
	private int mSourceID = 0;
	
	private static final String TAG_TITLE    = "Title";
	private static final String TAG_TEXT     = "Text";
	private static final String TAG_NR       = "Nr";
	private static final String TAG_DATE     = "Date";
	private static final String TAG_SOURCEID = "SourceID";
	
	public LogEntry(String Title, String Text, int Nr, int SourceID) {
		this.mTitle = Title;
		this.mText = Text;
		this.mNr = Nr;
		this.mDate = new Date();
		this.mSourceID = SourceID;
	}
	
	public LogEntry(org.json.JSONObject obj) {
		try {
			this.mTitle = obj.getString(TAG_TITLE);
			this.mText = obj.getString(TAG_TEXT);
			this.mNr = obj.getInt(TAG_NR);
			//this.mDate = obj.getString(TAG_DATE);
			this.mDate = new Date();
			this.mSourceID = obj.getInt(TAG_SOURCEID);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String getTitle() {
		return this.mTitle;
	}

	public String getText() {
		return this.mText;
	}

	public int getNr() {
		return this.mNr;
	}

	public Date getDate() {
		return this.mDate;
	}

	public int getSourceID() {
		return this.mSourceID;
	}
	
	public org.json.JSONObject getAsJson() {
		org.json.JSONObject Result = new org.json.JSONObject();
		try {
			Result.put(TAG_TITLE, this.mTitle);
			Result.put(TAG_TEXT, this.mText);
			Result.put(TAG_NR, this.mNr);
			Result.put(TAG_DATE, this.mDate.toString());
			Result.put(TAG_SOURCEID, this.mSourceID);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Result;
	}
}
