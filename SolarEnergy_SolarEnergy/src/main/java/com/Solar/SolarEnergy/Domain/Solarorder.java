package com.Solar.SolarEnergy.Domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Solarorder {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int orderid;
	private String uuid= UUID.randomUUID().toString();
	
	private int quantity;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderstatus;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date orderdate;
	
		
	@ManyToOne
	private Solarenergy solarenergy;
	
	@ManyToOne
	private Registrar registrar;

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}


	public Solarenergy getSolarenergy() {
		return solarenergy;
	}

	public void setSolarenergy(Solarenergy solarenergy) {
		this.solarenergy = solarenergy;
	}

	public Registrar getRegistrar() {
		return registrar;
	}

	public void setRegistrar(Registrar registrar) {
		this.registrar = registrar;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderStatus getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(OrderStatus orderstatus) {
		this.orderstatus = orderstatus;
	}

}
