/*******************************************************************************
 * Author: Peter Grajcar
 * 2016
 *******************************************************************************/
package com.pillsplan.revolware.pillsplan_reconstruction.data_access.model;

import java.sql.Timestamp;


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