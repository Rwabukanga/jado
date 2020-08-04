package com.Employee.EmployeeFront.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BK {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	
	@OneToOne
	private Better bt;
	
	
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Better getBt() {
		return bt;
	}
	public void setBt(Better bt) {
		this.bt = bt;
	}
	
	
}
