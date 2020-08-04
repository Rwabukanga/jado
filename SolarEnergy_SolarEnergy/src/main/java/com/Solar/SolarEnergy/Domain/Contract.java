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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Contract {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String uuid=UUID.randomUUID().toString();
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date startdate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date closingdate;
	private int quantity;
	private double amount;
	private boolean confirmservice;
	private boolean confirmpayment;
	private String attachment;
	
	@ManyToOne
	private Solarorder order;
	
	@Enumerated(EnumType.STRING)
	private ContractStatus contractstatus;
	
	@Enumerated(EnumType.STRING)
	private PaymentTerm paymentterm;
	
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
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getClosingdate() {
		return closingdate;
	}
	public void setClosingdate(Date closingdate) {
		this.closingdate = closingdate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public boolean isConfirmservice() {
		return confirmservice;
	}
	public void setConfirmservice(boolean confirmservice) {
		this.confirmservice = confirmservice;
	}
	public boolean isConfirmpayment() {
		return confirmpayment;
	}
	public void setConfirmpayment(boolean confirmpayment) {
		this.confirmpayment = confirmpayment;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public Solarorder getOrder() {
		return order;
	}
	public void setOrder(Solarorder order) {
		this.order = order;
	}
	public ContractStatus getContractstatus() {
		return contractstatus;
	}
	public void setContractstatus(ContractStatus contractstatus) {
		this.contractstatus = contractstatus;
	}
	public PaymentTerm getPaymentterm() {
		return paymentterm;
	}
	public void setPaymentterm(PaymentTerm paymentterm) {
		this.paymentterm = paymentterm;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
