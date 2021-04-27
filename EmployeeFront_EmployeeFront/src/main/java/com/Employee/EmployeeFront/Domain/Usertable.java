package com.Employee.EmployeeFront.Domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Usertable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userid;
	
	private String msisdn;
	
	private String lang;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date createdat;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Date getCreatedat() {
		return createdat;
	}

	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}
	
	
	
}

