package com.Solar.SolarEnergy.Domain;

import java.util.Set;
import java.util.UUID;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Solarenergy {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String uuid= UUID.randomUUID().toString();
	private String solarcapacity;
	private String solarquality;
	private int  quantity;
	private String description;
	private double cost;
	private String file;
	
	@ManyToOne
	private Branch branch;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSolarcapacity() {
		return solarcapacity;
	}
	public void setSolarcapacity(String solarcapacity) {
		this.solarcapacity = solarcapacity;
	}
	public String getSolarquality() {
		return solarquality;
	}
	public void setSolarquality(String solarquality) {
		this.solarquality = solarquality;
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
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
	

	
	
	
	
}
