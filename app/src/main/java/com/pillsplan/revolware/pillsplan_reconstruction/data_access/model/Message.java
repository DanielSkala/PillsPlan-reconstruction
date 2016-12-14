/*******************************************************************************
 * Author: Peter Grajcar
 * 2016
 *******************************************************************************/
package com.pillsplan.revolware.pillsplan_reconstruction.data_access.model;

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