package com.Solar.SolarEnergy.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Location {
	
	@Id
	private int locationid;
	private String locationname;
	private String parentid;
	
	
	public int getLocationid() {
		return locationid;
	}
	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}
	public String getLocationname() {
		return locationname;
	}
	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	
}
