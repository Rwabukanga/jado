package com.Solar.SolarEnergy.Controller;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Solar.SolarEnergy.Controller.SolarOrderController.contractas;
import com.Solar.SolarEnergy.Domain.Branch;
import com.Solar.SolarEnergy.Domain.Contract;
import com.Solar.SolarEnergy.Domain.ContractStatus;
import com.Solar.SolarEnergy.Domain.OrderStatus;
import com.Solar.SolarEnergy.Domain.Payment;
import com.Solar.SolarEnergy.Domain.PaymentStatus;
import com.Solar.SolarEnergy.Domain.Solarenergy;
import com.Solar.SolarEnergy.Domain.Solarorder;
import com.Solar.SolarEnergy.Service.ContractService;
import com.Solar.SolarEnergy.Service.PaymentService;
import com.Solar.SolarEnergy.Service.RegistrarService;
import com.Solar.SolarEnergy.Service.SolarEnergyService;
import com.Solar.SolarEnergy.Utitlity.BrainTreeConfiguration;
import com.Solar.SolarEnergy.Utitlity.Messages;
import com.Solar.SolarEnergy.Utitlity.ResponseBean;
import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.CreditCard;
import com.braintreegateway.Customer;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.Transaction.Status;
import com.braintreegateway.TransactionRequest;
import com.braintreegateway.ValidationError;



/**
 * @author student
 *
 */
@RestController
@RequestMapping(value="/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentservice;
	
	@Autowired
	private BrainTreeConfiguration gateway;
	
	@Autowired
	private SolarEnergyService solarenergyservice;
	
	@Autowired
	private RegistrarService registrarservice;
	
	@Autowired
	private ContractService contractservice;
	
	@Autowired
	private BrainTreeConfiguration braintree;
    
	@RequestMapping(value="/save/uuid/uuuid", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createPayment(HttpServletRequest request, @RequestBody InnerPayment payment,@PathVariable String uuid) {
		ResponseBean rb = new ResponseBean();
	
		try {
			
			         /*   solarenergyservice.findByUuid(uuid);*/
			            registrarservice.findByUuid(uuid);
			            BrainTreeConfiguration.ResponceObject br=BrainTreeConfiguration.createTransaction(payment);
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription(Messages.save);
						rb.setObject(br);
					
		
			
} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to create registrar");
			rb.setObject(payment);
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	@CrossOrigin
	@RequestMapping(method = RequestMethod.PUT, value = "/approved/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> approvepaymet(HttpServletRequest request, @RequestBody InnerPayment payment,@PathVariable String uuid) {
		ResponseBean rb = new ResponseBean();
	
		try {
			
			InnerPayment p= new InnerPayment();
			Contract s=contractservice.findByUuid(uuid);
			s.setContractstatus(ContractStatus.Confirmationbyall);
			contractservice.updatecontract(s);
			
			//Payment pay= new Payment();
			//pay.setContract(s);
			//pay.setPaymentstatus(PaymentStatus.Pending);
			//paymentservice.createpayment(pay);
			BrainTreeConfiguration.ResponceObject br=BrainTreeConfiguration.createTransaction(payment);
			Map<String,Object> map=new HashMap();
			 map.put("payment", br);
			 map.put("contract", s);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription(Messages.save);
			rb.setObject(map);
					
		
			
} catch (Exception e) {
	          e.printStackTrace();
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to create registrar");
			rb.setObject(payment);
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/client_token", method = RequestMethod.GET)
	public ResponseEntity<Object> checkout(Model model) {
	    //get the token
		ResponseBean rb = new ResponseBean();
		   
	        rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription(Messages.save);
			rb.setObject(BrainTreeConfiguration.brainTreeAnvironment());
	     
	    //serve new.html
	   return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value= "/allpayment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAllPayment() {
		ResponseBean rb = new ResponseBean();
		try {

			
					List<Payment> list= paymentservice.findAll();
					rb.setCode(Messages.SUCCESS_CODE);
					rb.setDescription("The paymnet are successfuly retrieved");
					rb.setObject(list);


		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("error occured while retrieving payment");
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value="/paymentransaction",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveTransaction(@RequestBody PaymentObject po){
		ResponseBean rb = new ResponseBean();
		try {
			 Contract c=contractservice.findByUuid(po.uuid);
			 if(c!=null) {
				 Payment pay= new Payment();
				 pay.setAmount(po.amount);
				 pay.setContract(c);
				 pay.setPaymentstatus(PaymentStatus.valueOf(po.paymentStatus));
				 paymentservice.createpayment(pay);
				
				 rb.setCode(200);
				 rb.setDescription("DONE SUCCESFULLY");
				 rb.setObject(pay);
			 }else {
				 rb.setCode(300);
				 rb.setDescription("Contract not found");
				 ///rb.setObject(pay);
			 }
			
		}catch(Exception ex) {
			ex.printStackTrace();
			rb.setCode(301);
			rb.setDescription("Error Occured try again");
			
		}
		  return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	/*
    @RequestMapping(method = RequestMethod.PUT, value = "/approve/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> approvepayment(HttpServletRequest request, @PathVariable String uuid) {

		ResponseBean rb = new ResponseBean();
		try {

			        InnerPayment n=new InnerPayment();
					Payment seasonp=paymentservice.findByUuid(uuid);
					seasonp.setPaymentstatus(PaymentStatus.Pending);
					paymentservice.updatepayment(seasonp);
				Payment pay= new Payment();
					
					pay.setAmount(Double.parseDouble(n.getAmount()));
					SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
					pay.setPaymentdate(sdf.parse(n.getPaymentdate()));
					
					contractservice.createcontract(contract);	
       				rb.setCode(Messages.SUCCESS_CODE);
					rb.setDescription("Season Update Successfuly");

				
		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("Season not well Updated");
		}
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}*/
	
	
	    @CrossOrigin
	    @RequestMapping(value = "/{uuid}/report", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Object> quotationDetailsReport(@PathVariable String uuid, HttpServletRequest request) {
	        ResponseBean responseBean = new ResponseBean();
	        try {
	           
	                HttpHeaders headers = new HttpHeaders();
	                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	                headers.add("Pragma", "no-cache");
	                headers.add("Expires", "0");
	                headers.add("Content-Disposition", "inline; filename=QuotationDetails.pdf");
	                
	                ByteArrayInputStream bis =new ByteArrayInputStream(paymentservice.PaymentDetailsPDF(paymentservice.findByUuid(uuid)));
	                
	                return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/pdf"))
	                    .body(new InputStreamResource(bis));
	        
	        } catch (Exception ex) {
	            responseBean.setCode(Messages.ERROR_CODE);
	            responseBean.setDescription(Messages.error);
	            responseBean.setObject(null);
	        }
	        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	    }

	
	
	public static class InnerPayment{
		private String firstname;
		private String lastname;
		private String phonenumber;
		private String email;
		private Date expireddate;
		private String clientNonce;
        private String cardType;
        private String lastFour;
        private String lastTwo;
        private String bin;
        private String amount;
        private String paymentdate;
        private String clientName;
        @Enumerated(EnumType.STRING)
    	private PaymentStatus paymentstatus;
        
		public String getClientNonce() {
			return clientNonce;
		}
		public void setClientNonce(String clientNonce) {
			this.clientNonce = clientNonce;
		}
		public String getCardType() {
			return cardType;
		}
		public void setCardType(String cardType) {
			this.cardType = cardType;
		}
		public String getLastFour() {
			return lastFour;
		}
		public void setLastFour(String lastFour) {
			this.lastFour = lastFour;
		}
		public String getLastTwo() {
			return lastTwo;
		}
		public void setLastTwo(String lastTwo) {
			this.lastTwo = lastTwo;
		}
		public String getBin() {
			return bin;
		}
		public void setBin(String bin) {
			this.bin = bin;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		public String getPaymentdate() {
			return paymentdate;
		}
		public void setPaymentdate(String paymentdate) {
			this.paymentdate = paymentdate;
		}
		public String getClientName() {
			return clientName;
		}
		public void setClientName(String clientName) {
			this.clientName = clientName;
		}
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String getPhonenumber() {
			return phonenumber;
		}
		public void setPhonenumber(String phonenumber) {
			this.phonenumber = phonenumber;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Date getExpireddate() {
			return expireddate;
		}
		public void setExpireddate(Date expireddate) {
			this.expireddate = expireddate;
		}
		public PaymentStatus getPaymentstatus() {
			return paymentstatus;
		}
		public void setPaymentstatus(PaymentStatus paymentstatus) {
			this.paymentstatus = paymentstatus;
		}
        
        
	}
	
	public static class PaymentObject{
		private String uuid;
		private double amount;
		private String paymentStatus;
		public String getUuid() {
			return uuid;
		}
		public void setUuid(String uuid) {
			this.uuid = uuid;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public String getPaymentStatus() {
			return paymentStatus;
		}
		public void setPaymentStatus(String paymentStatus) {
			this.paymentStatus = paymentStatus;
		}
		
		
	}
	
}
