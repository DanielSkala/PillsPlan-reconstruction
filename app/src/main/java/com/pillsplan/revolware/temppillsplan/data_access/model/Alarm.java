package com.pillsplan.revolware.temppillsplan.data_access.model;

import java.sql.Timestamp;

/**
 * @author Peter Grajcar
 * 14.12.2016
 */
public class Alarm {

	private String id;
	private String userId;
	private String medicine;
	private Integer quantity;
	private String doctor;
	private String frequency;
	private Boolean active;
	private Timestamp beginning;
	private Timestamp created;
	private Timestamp updated;
	
	public Alarm() { }

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
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public Timestamp getBeginning() {
		return beginning;
	}
	public void setBeginning(Timestamp beginning) {
		this.beginning = beginning;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Timestamp getUpdated() {
		return updated;
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
}
