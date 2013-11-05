package org.gege.caldavsyncadapter.syncadapter.logging;

import java.util.Date;

public class LogEntry {
	private String mTitle = "";
	private String mText = "";
	private int mNr = 0;
	private Date mDate;
	private int mSourceID = 0;
	
	public LogEntry(String Title, String Text, int Nr, int SourceID) {
		this.mTitle = Title;
		this.mText = Text;
		this.mNr = Nr;
		this.mDate = new Date();
		this.mSourceID = SourceID;
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
}
