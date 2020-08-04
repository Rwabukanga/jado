package com.Solar.SolarEnergy.Domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Payment {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int paymentid;
    private String uuid= UUID.randomUUID().toString();
    
    @JsonFormat(pattern="dd/MM/YYYY")
	private Date paymentdate;
	private double amount;
	
	@ManyToOne
	private Branch branch;
	
	@ManyToOne
	private Registrar registrar;
	
	@ManyToOne
	private Solarorder order;
	
	@ManyToOne
	private Contract contract;
	
	@Enumerated(EnumType.STRING)
	private Paymenttype typepayment;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentstatus;
	
	public int getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(int paymentid) {
		this.paymentid = paymentid;
	}

	public Date getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
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

	public Solarorder getOrder() {
		return order;
	}

	public void setOrder(Solarorder order) {
		this.order = order;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Paymenttype getTypepayment() {
		return typepayment;
	}

	public void setTypepayment(Paymenttype typepayment) {
		this.typepayment = typepayment;
	}

	public PaymentStatus getPaymentstatus() {
		return paymentstatus;
	}

	public void setPaymentstatus(PaymentStatus paymentstatus) {
		this.paymentstatus = paymentstatus;
	}
	
	
}
