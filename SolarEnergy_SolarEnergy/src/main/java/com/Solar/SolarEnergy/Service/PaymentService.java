package com.Solar.SolarEnergy.Service;

import java.util.List;

import com.Solar.SolarEnergy.Domain.Payment;

public interface PaymentService {

	public void createpayment(Payment payment);
	public void updatepayment(Payment payment);
	public void deletepayment(Payment payment); 
	public Payment findByid(int id);
	public List<Payment> findAll();
	public Payment findByUuid(String uuid);
	public Payment findAllbyPayment(long id);
	public byte[] PaymentDetailsPDF(Payment pay);
}
