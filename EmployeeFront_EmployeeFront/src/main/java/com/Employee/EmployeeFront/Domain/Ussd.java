package com.Employee.EmployeeFront.Domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ussd {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String uuid = UUID.randomUUID().toString();
	private String msisdn;
	private String sessionid;
	private String newreq;
	private String input;
	
	
	
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String getNewreq() {
		return newreq;
	}
	public void setNewreq(String newreq) {
		this.newreq = newreq;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
