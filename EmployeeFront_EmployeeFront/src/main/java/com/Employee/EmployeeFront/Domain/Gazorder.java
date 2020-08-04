package com.Employee.EmployeeFront.Domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Gazorder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String uuid = UUID.randomUUID().toString();
	private int quantity;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date orderdate;
	@Enumerated(EnumType.STRING)
	private Orderstatus orderstatus;
	
	
	
	@ManyToOne
	private Gaz gaz;
//	@ManyToOne
//	private Branch branch;
	
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	public Orderstatus getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(Orderstatus orderstatus) {
		this.orderstatus = orderstatus;
	}
	public Gaz getGaz() {
		return gaz;
	}
	public void setGaz(Gaz gaz) {
		this.gaz = gaz;
	}
	/*public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}*/
	
	
	
}
