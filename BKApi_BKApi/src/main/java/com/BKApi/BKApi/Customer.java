package com.BKApi.BKApi;

import java.util.Date;



public class Customer {

	
	private int id;
	private String name;
	private String dateofbirth;
	private String phonenumber;
	private String address;
	
	
	public Customer (int id,String name,String phonenumber,String dateofbirth,String address) {
		
		this.id = id;
		this.name = name;
		this.dateofbirth = dateofbirth;
		this.phonenumber = phonenumber;
		this.address = address;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	
	
	
}
