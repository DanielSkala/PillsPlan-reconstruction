/*******************************************************************************
 * Author: Peter Grajcar
 * 2016
 *******************************************************************************/
package com.pillsplan.revolware.pillsplan_reconstruction.data_access.model;

public class Medicine {
	
	private String code;
	private String name;
	private String pkg;
	private String dailyQuantity;
	private String holder;
	private String issue;
	private String registrationNumber;
	private String payment;
	private String atcCode;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPkg() {
		return pkg;
	}
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}
	public String getDailyQuantity() {
		return dailyQuantity;
	}
	public void setDailyQuantity(String dailyQuantity) {
		this.dailyQuantity = dailyQuantity;
	}
	public String getHolder() {
		return holder;
	}
	public void setHolder(String holder) {
		this.holder = holder;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getAtcCode() {
		return atcCode;
	}
	public void setAtcCode(String atcCode) {
		this.atcCode = atcCode;
	}
	
	@Override
	public String toString () {
		return getName();
	}
	
}
