package com.Solar.SolarEnergy.Controller;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

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
import com.Solar.SolarEnergy.Domain.OrderStatus;
import com.Solar.SolarEnergy.Domain.Payment;
import com.Solar.SolarEnergy.Domain.PaymentStatus;
import com.Solar.SolarEnergy.Domain.Registrar;
import com.Solar.SolarEnergy.Domain.Solarenergy;
import com.Solar.SolarEnergy.Domain.Solarorder;
import com.Solar.SolarEnergy.Service.BranchService;
import com.Solar.SolarEnergy.Service.ContractService;
import com.Solar.SolarEnergy.Service.PaymentService;
import com.Solar.SolarEnergy.Service.RegistrarService;
import com.Solar.SolarEnergy.Service.SolarEnergyService;
import com.Solar.SolarEnergy.Service.SolarorderService;
import com.Solar.SolarEnergy.Utitlity.Messages;
import com.Solar.SolarEnergy.Utitlity.ResponseBean;


@RestController
@CrossOrigin
@RequestMapping(value="/solarorders")
public class SolarOrderController {
	
    @Autowired
	private SolarorderService solarorderservice; 
    
    @Autowired
    private SolarEnergyService solarenergyservice;
    
    @Autowired
    private BranchService branchservice;
    
    @Autowired
    private RegistrarService registrarservice;
    
    @Autowired
    private ContractService contractservice;
    
    @Autowired
    private PaymentService paymentservice;
    
    @CrossOrigin
    @RequestMapping(value= "/save", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createSolarBranch(HttpServletRequest request, @RequestBody Solarorder solarorder) {
		ResponseBean rb = new ResponseBean();
	
		try {
			
			            solarorderservice.createorder(solarorder);
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription(Messages.save);
						rb.setObject(solarorder);
					
		
			
} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to create registrar");
			rb.setObject(solarorder);
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
    
    @CrossOrigin
    @RequestMapping(value= "/saveorder/{suuid}/{ruuid}", method =RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createSolarEnergytobranch(HttpServletRequest request, @RequestBody Solarorder solarorder,@PathVariable  String suuid,@PathVariable String ruuid) {
		ResponseBean rb = new ResponseBean();
		
		try {
         int minus=solarorder.getQuantity();
         
           Solarenergy solar=solarenergyservice.findByUuid(suuid);
           int currentq=solar.getQuantity();
           int remain=currentq-minus;
           if(remain<0) {
        	   solar.setQuantity(0);
           }else {
        	   solar.setQuantity(remain);
           }
          
		   Registrar rg=registrarservice.findByUuid(ruuid);
           solarorder.setSolarenergy(solar);
		   solarorder.setRegistrar(rg);    
		   solarorder.setOrderstatus(OrderStatus.Pending);
		   solarorderservice.createorder(solarorder);
		   solarenergyservice.updateSolarenergy(solar);
			
} catch (Exception e) {
	    
	    System.out.println(e.getMessage()+"=============================");
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to create registrar");
			rb.setObject(solarorder);
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}

    @CrossOrigin
    @RequestMapping(value= "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAllSolarOrder() {
		ResponseBean rb = new ResponseBean();
		try {


					List<Solarorder> list = solarorderservice.findAll();
					rb.setCode(Messages.SUCCESS_CODE);
					rb.setDescription("The solarorder are successfuly retrieved");
					rb.setObject(list);


		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			System.out.println(e.getMessage()+"===========");
			rb.setDescription("error occured while retrieving solarenergy");
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
    
    @CrossOrigin
    @RequestMapping(value= "/all/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAllSolarOrderbyregistrar(@PathVariable String uuid) {
		ResponseBean rb = new ResponseBean();
		try {

                    Registrar registrar=registrarservice.findByUuid(uuid);
					List<Solarorder> list = solarorderservice.findAllbyRegistrar(registrar.getId());
					rb.setCode(Messages.SUCCESS_CODE);
					rb.setDescription("The solarorder are successfuly retrieved");
					rb.setObject(list);


		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			System.out.println(e.getMessage()+"===========");
			rb.setDescription("error occured while retrieving solarenergy");
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
    
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value= "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findOne(HttpServletRequest request, @PathVariable int id) {

		ResponseBean rb = new ResponseBean();
		try {

					Solarorder solar = solarorderservice.findByid(id);
					if (solar == null) {
						rb.setCode(Messages.NULLS_FOUND);
						rb.setDescription("The season was not found");
					} else {
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription("The branch is successfuly found");
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

					Solarorder solar= solarorderservice.findByid(id);

					if (solar == null) {
						rb.setCode(Messages.NOT_FOUND);
						rb.setDescription("The Season you want  Delete does not exit");
					} else {

						solarorderservice.deleteorder(id);
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
    @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(HttpServletRequest request, @RequestBody Solarorder season) {

		ResponseBean rb = new ResponseBean();
		try {

					Solarorder seasonp=solarorderservice.findByid(season.getOrderid());
					seasonp.setOrderid(season.getOrderid());
					seasonp.setOrderdate(season.getOrderdate());
					solarorderservice.updateorder(seasonp);
					rb.setCode(Messages.SUCCESS_CODE);
					rb.setDescription("Season Update Successfuly");

				
		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("Season not well Updated");
		}
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
    
    
    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, value = "/approve/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> approveorder(HttpServletRequest request, @PathVariable String uuid) {

		ResponseBean rb = new ResponseBean();
		try {

			        contractas n=new contractas();
					Solarorder seasonp = solarorderservice.findByUuid(uuid);
					seasonp.setOrderstatus(OrderStatus.Terminated);
					solarorderservice.updateorder(seasonp);
					Contract contract= new Contract();
				/*	Payment payment=new Payment();*/
					/*contract.setStartdate(n.getStartdate());
					contract.setClosingdate(n.getClosingdate());
					contract.setQuantity(n.getQuantity());
					contract.setAmount(n.getAmount());*/
					contract.setOrder(seasonp);
					contract.setContractstatus(ContractStatus.Pending);
					contractservice.createcontract(contract);	
					/*payment.setPaymentstatus(PaymentStatus.Pending);
					paymentservice.createpayment(payment);*/
       				rb.setCode(Messages.SUCCESS_CODE);
					rb.setDescription("Season Update Successfuly");

				
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
                
                ByteArrayInputStream bis =new ByteArrayInputStream(solarorderservice.OrderDetailsPDF(solarorderservice.findByUuid(uuid)));
                
                return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/pdf"))
                    .body(new InputStreamResource(bis));
        
        } catch (Exception ex) {
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error);
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }
    
    public static class contractas {
    	private Date startdate;
    	private Date closingdate;
    	private int quantity;
    	private double amount;
    	private boolean confirmservice;
    	private boolean confirmpayment;
    	private String attachment;
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
    	
    	
    }

}
