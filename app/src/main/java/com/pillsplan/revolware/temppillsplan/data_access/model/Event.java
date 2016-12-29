package com.pillsplan.revolware.temppillsplan.data_access.model;

import java.sql.Timestamp;

/**
 * @author Peter Grajcar
 * 14.12.2016
 */
public class Event {

	private String sessionId;
	private String event;
	private String content;
	private Timestamp recorded;

	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRecorded() {
		return recorded;
	}
	public void setRecorded(Timestamp recorded) {
		this.recorded = recorded;
	}
	
	@Override
	public String toString() {
		return getRecorded() + ": " + getEvent() + " {" + content + "}";
	}
	
}
