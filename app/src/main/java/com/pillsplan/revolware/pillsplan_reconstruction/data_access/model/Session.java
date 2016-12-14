package com.pillsplan.revolware.pillsplan_reconstruction.data_access.model;

import com.pillsplan.revolware.pillsplan_reconstruction.data_access.model.Event;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Peter Grajcar
 * 14.12.2016
 */
public class Session {

	private String id;
	private String userId;
	private Double latitude;
	private Double longitude;
	private List<Event> events = new ArrayList<>();

	private Timestamp created;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	
	@Override
	public String toString () {
		return getCreated() + ", " + getLatitude() + ", " + getLongitude() + "\n{\n\t" + getEvents() + "\n}";
	}
}
