package com.Employee.EmployeeFront.Domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class Gaz {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String uuid=UUID.randomUUID().toString();
	private String quality;
	private int quantity;
	private String description;
	private double cost;
	private String file;
	
	@ManyToOne
	private Branchuser branchuser;
	
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
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public Branchuser getBranchuser() {
		return branchuser;
	}
	public void setBranchuser(Branchuser branchuser) {
		this.branchuser = branchuser;
	}
	
	
	
	
}
