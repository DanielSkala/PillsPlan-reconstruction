package com.pillsplan.revolware.temppillsplan.data_access.model;

/**
 * @author Peter Grajcar
 * 14.12.2016
 */
public class Message {

	private String message;
	private Integer status;
	
	public Message() { }
	public Message(String message){
		this.message = message;
	}
	public Message(String message, int status){
		this.message = message;
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
