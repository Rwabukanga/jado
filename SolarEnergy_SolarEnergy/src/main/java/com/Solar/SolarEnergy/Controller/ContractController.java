package com.Solar.SolarEnergy.Controller;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Solar.SolarEnergy.Domain.Contract;
import com.Solar.SolarEnergy.Domain.ContractStatus;
import com.Solar.SolarEnergy.Domain.PaymentTerm;
import com.Solar.SolarEnergy.Domain.Solarorder;
import com.Solar.SolarEnergy.Service.ContractService;
import com.Solar.SolarEnergy.Service.RegistrarService;
import com.Solar.SolarEnergy.Service.SolarorderService;
import com.Solar.SolarEnergy.Utitlity.Messages;
import com.Solar.SolarEnergy.Utitlity.ResponseBean;

@RestController
@CrossOrigin
@RequestMapping(value="/contracts")
public class ContractController {

	@Autowired
	private ContractService contractservice;
	
	@Autowired
	private SolarorderService solarorderservice;
	
	  @Autowired
	    private RegistrarService registrarservice;
	    
    
/*	@CrossOrigin
	@RequestMapping(value="/save", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createSolarBranch(HttpServletRequest request, @RequestBody InerrContract contract) {
		ResponseBean rb = new ResponseBean();
	
		try {
					   Contract cont=new Contract();
					   cont.setAmount(contract.getAmount());
					   cont.setAttachment(contract.getAttachment());
					  // cont.setContractstatus(ContractStatus.Pending);
			            contractservice.createcontract(cont);       
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription(Messages.save);
						rb.setObject(contract);
					
		
			
} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription(e.getMessage());
			rb.setObject(null);
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	*/
	
	@CrossOrigin
	@RequestMapping(value="/update/{cuuid}", method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(HttpServletRequest request, @RequestBody InerrContract contract,@PathVariable String cuuid) {
		ResponseBean rb = new ResponseBean();
	
		try {
			System.out.println(cuuid+"==================================");
					   Contract cont= contractservice.findByUuid(cuuid);
					   cont.setAmount(contract.getAmount());
			/*		   cont.setAttachment(contract.getAttachment());*/
					   cont.setStartdate(contract.getStartdate());
					   cont.setClosingdate(contract.getClosingdate());
					   cont.setQuantity(contract.getQuantity());
					   cont.setPaymentterm(contract.getPaymentterm());
					/*   cont.setContractstatus(ContractStatus.Pending);*/
			            contractservice.updatecontract(cont);   
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription(Messages.save);
						rb.setObject(cont);
					
		
			
} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription(e.getMessage());
			rb.setObject(null);
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	
	 @CrossOrigin
	 @RequestMapping(value= "/savecontract/{ouuid}", method =RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Object> createSolarEnergytobranch(HttpServletRequest request, @RequestBody Contract contract,@PathVariable  String ouuid) {
			ResponseBean rb = new ResponseBean();
			
			try {

	           Solarorder ord= solarorderservice.findByUuid(ouuid) ;
	           contract.setOrder(ord);
			   contract.setContractstatus(ContractStatus.Pending);
			   contractservice.createcontract(contract);
			/*   solarorderservice.updateorder(ord);*/
				
	} catch (Exception e) {
		    
		    System.out.println(e.getMessage()+"=============================");
				rb.setCode(Messages.ERROR_CODE);
				rb.setDescription("failed to create registrar");
				rb.setObject(contract);
			}

			return new ResponseEntity<Object>(rb, HttpStatus.OK);
		}
	
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value= "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findOne(HttpServletRequest request, @PathVariable int id) {

		ResponseBean rb = new ResponseBean();
		try {

			
				
					Contract solar = contractservice.findByid(id);
					if (solar == null) {
						rb.setCode(Messages.NULLS_FOUND);
						rb.setDescription("The season was not found");
					} else {
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription("The registrar is successfuly found");
						rb.setObject(solar);
					}

		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("error occured please try again!!");
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	
}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value= "/uid/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findByuuid(HttpServletRequest request, @PathVariable String uuid) {

		ResponseBean rb = new ResponseBean();
		try {

			
				
					Contract solar = contractservice.findByUuid(uuid);
					if (solar == null) {
						rb.setCode(Messages.NULLS_FOUND);
						rb.setDescription("The contract was not found");
					} else {
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription("The contract is successfuly found");
						rb.setObject(solar);
					}

		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("error occured please try again!!");
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	
}
	
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public ResponseEntity<Object> delete(HttpServletRequest request, @PathVariable int id) {
        
		ResponseBean rb = new ResponseBean();
		try {

					Contract s = contractservice.findByid(id);

					if (s == null) {
						rb.setCode(Messages.NOT_FOUND);
						rb.setDescription("The Season you want  Delete does not exit");
					} else {

						contractservice.deletecontract(id);
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription("Branch Deleted Successfuly");
						rb.setObject(null);
					}

				
		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("Branch not well Deleted");
		}
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
}
	@CrossOrigin
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> viewAllSolarBranch() {
		ResponseBean rb = new ResponseBean();
		try {


					List<Contract> list = contractservice.findAll();
					rb.setCode(Messages.SUCCESS_CODE);
					rb.setDescription("The seasons are successfuly retrieved");
					rb.setObject(list);


		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("error occured while retrieving seasons");
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
    @CrossOrigin
	@RequestMapping(value = "/all/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> viewAllSolarContract(@PathVariable String uuid ) {
		ResponseBean rb = new ResponseBean();
		try {

                    Solarorder order = solarorderservice.findByUuid(uuid);
					Contract list = contractservice.findAllbyContract(order.getOrderid());
					rb.setCode(Messages.SUCCESS_CODE);
					rb.setDescription("The contract are successfuly retrieved");
					rb.setObject(list);


		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("error occured while retrieving contract");
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
    
    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, value = "/approvebycustomer/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> confirmbycustomer(HttpServletRequest request, @PathVariable String uuid) {

		ResponseBean rb = new ResponseBean();
		try {
			        
                    Contract cont= contractservice.findByUuid(uuid);
					cont.setContractstatus(ContractStatus.Confirmationbycustomer);
					contractservice.updatecontract(cont);;
					rb.setCode(Messages.SUCCESS_CODE);
					rb.setDescription("Contract Update Successfuly");

				
		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("Contract not well Updated");
		}
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, value = "/approvebyseller/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Object> confirmbyseller(HttpServletRequest request, @PathVariable String uuid) {

   		ResponseBean rb = new ResponseBean();
   		try {
                    Contract cont= contractservice.findByUuid(uuid);
   					cont.setContractstatus(ContractStatus.Confirmationbyall);;
                  /*  if(ContractStatus.Pending != null){
                    	cont.setContractstatus(ContractStatus.Confirmationbyseller);
                    }else {
                    	if(ContractStatus.Confirmationbycustomer != null );
                    	cont.setContractstatus(ContractStatus.Confirmationbyall);
                    }*/
   					contractservice.updatecontract(cont);
   					rb.setCode(Messages.SUCCESS_CODE);
   					rb.setDescription("Contract Update Successfuly");

   				
   		} catch (Exception e) {
   			rb.setCode(Messages.ERROR_CODE);
   			rb.setDescription("Season not well Updated");
   		}
   		return new ResponseEntity<Object>(rb, HttpStatus.OK);
   	}
    
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
                
                ByteArrayInputStream bis =new ByteArrayInputStream(contractservice.ContractDetailsPDF(contractservice.findByUuid(uuid)));
                
                return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/pdf"))
                    .body(new InputStreamResource(bis));
        
        } catch (Exception ex) {
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }
    
    public static class InerrContract{
    	private Date startdate;
    	private Date closingdate;
    	private int quantity;
    	/*private boolean confirmservice;
    	private boolean confirmpayment;*/
    	private double amount;
    	
    	@Enumerated(EnumType.STRING)
    	private PaymentTerm paymentterm;
    	
    	private String attachment;
    	
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public String getAttachment() {
			return attachment;
		}
		public void setAttachment(String attachment) {
			this.attachment = attachment;
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
		/*public boolean isConfirmservice() {
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
	
		
    	
    }*/
		public PaymentTerm getPaymentterm() {
			return paymentterm;
		}
		public void setPaymentterm(PaymentTerm paymentterm) {
			this.paymentterm = paymentterm;
		}

}
}