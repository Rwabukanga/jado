package com.Employee.EmployeeFront.Domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class menutable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int menuid;
	
	private String Msg;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date createdat;
	
	private String level;
	
	private String FreeFlow;
	
	private String action;
	
	private String lang;

	public int getMenuid() {
		return menuid;
	}

	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public Date getCreatedat() {
		return createdat;
	}

	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getFreeFlow() {
		return FreeFlow;
	}

	public void setFreeFlow(String freeFlow) {
		FreeFlow = freeFlow;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	
}