package com.Employee.EmployeeFront.Domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class transactiontable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int transactionid;
	
	private String msisdn;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date reqdate;
	
	private String sessionid;
	
	private String input;
	
	private String action;
	
	private String level;
	
	private String lang;

	public int getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public Date getReqdate() {
		return reqdate;
	}

	public void setReqdate(Date reqdate) {
		this.reqdate = reqdate;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	
	
	
	
}
