package com.Solar.SolarEnergy.Domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SolarBranch {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String uuid= UUID.randomUUID().toString();
	private Date entradate;
	@ManyToOne
	private Branch branch;
	
	@ManyToOne
	private Solarenergy Solarenergy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEntradate() {
		return entradate;
	}

	public void setEntradate(Date entradate) {
		this.entradate = entradate;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Solarenergy getSolarenergy() {
		return Solarenergy;
	}

	public void setSolarenergy(Solarenergy solarenergy) {
		Solarenergy = solarenergy;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
