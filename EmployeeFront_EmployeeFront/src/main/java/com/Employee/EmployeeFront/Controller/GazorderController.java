package com.Employee.EmployeeFront.Controller;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Employee.EmployeeFront.Domain.Branch;
import com.Employee.EmployeeFront.Domain.Gaz;
import com.Employee.EmployeeFront.Domain.Gazorder;
import com.Employee.EmployeeFront.Domain.Orderstatus;
import com.Employee.EmployeeFront.Service.Branchservice;
import com.Employee.EmployeeFront.Service.GazService;
import com.Employee.EmployeeFront.Service.GazorderService;
import com.Employee.EmployeeFront.Utility.Messages;
import com.Employee.EmployeeFront.Utility.ResponseBean;


@Controller
@RequestMapping(value="/gazorder")
public class GazorderController {

	@Autowired
	private GazorderService gazorderservice;
	@Autowired
	private GazService gazservice;
	@Autowired
	private Branchservice branchservice;
	
	
	@CrossOrigin
	@RequestMapping(value="/save", method=RequestMethod.POST,  consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createGazorder(HttpServletRequest request,@RequestBody Gazorder order,@PathVariable int id){
		ResponseBean rb = new ResponseBean();
		try {
			
			Optional<Gaz> gaz= gazservice.findById(id);
			Gaz tr= gaz.get();
			Optional<Branch> branch= branchservice.findById(id);
			Branch br= branch.get();
			order.setGaz(tr);
			gazorderservice.creategazorder(order);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription(Messages.save);
			rb.setObject(order);
			
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("fail to save branch");
		
		}
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value="/order/{uuid}",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> creategazorders(HttpServletRequest request, @RequestBody Gazorder gazorder, @PathVariable String uuid){
		
		ResponseBean rb = new ResponseBean();
		try {
			int minus = gazorder.getQuantity();
			Optional<Gaz> g= gazservice.findByUuid(uuid);
			Gaz gz= g.get();
			
			int currentt = gz.getQuantity();
			int remain = currentt-minus;
			
			if(remain<0) {
				gz.setQuantity(0);
			}else {
				gz.setQuantity(remain);
			}
			gazorder.setGaz(gz);
			gazorder.setOrderstatus(Orderstatus.Pending);
			gazorderservice.creategazorder(gazorder);
			gazorderservice.updategazorder(gazorder);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription("Gaz order created");
			rb.setObject(gazorder);
			
		}catch(Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("Failed to create gaz order");
		}
		
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value="/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAll(){
		
		ResponseBean rb = new ResponseBean();
		try {
			
			List<Gazorder> list = gazorderservice.findAll(Gazorder.class);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription(Messages.save);
			rb.setObject(list);
			
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("Failed to return list");
		}
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.DELETE, value="/delete/{id}")
	public ResponseEntity<Object> deletegazorder(HttpServletRequest request, @PathVariable int id){
		ResponseBean rb = new ResponseBean();
		try {
			Optional<Gazorder> br= gazorderservice.findById(id);
			Gazorder order= br.get();
			if(order == null) {
				rb.setCode(Messages.NOT_FOUND);
				rb.setDescription("not deleted");
				
			}else {
				gazorderservice.delete(order);
				rb.setCode(Messages.SUCCESS_CODE);
				rb.setDescription(Messages.save);
				rb.setObject(order);		
			}
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to delete branch");
			
		}
		return new ResponseEntity<Object>(rb,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.PUT, value= "/update/{uuid}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateBranch(HttpServletRequest request,@PathVariable String uuid,@RequestBody Gazorder order){
		
		ResponseBean rb = new ResponseBean();
		
		try {
			Optional<Gazorder> br= gazorderservice.findByUuid(uuid);
			Gazorder orr= br.get(); 
			/*orr.setBranch(order.getBranch());*/
			orr.setGaz(order.getGaz());
			orr.setOrderdate(order.getOrderdate());
			orr.setOrderstatus(order.getOrderstatus());
			orr.setQuantity(order.getQuantity());
			gazorderservice.updategazorder(orr);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription("Order Updated");
			rb.setObject(orr);
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("Failed to update branch");
		}
		return new ResponseEntity<Object>(rb,HttpStatus.OK);
	}
	
	
    @CrossOrigin
	@RequestMapping(method=RequestMethod.PUT, value="/approve/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>orderapprv(HttpServletRequest request, @PathVariable String uuid ){
		
		ResponseBean rb = new ResponseBean();
		try {
			Optional<Gazorder> gr = gazorderservice.findByUuid(uuid);
			Gazorder order = gr.get();
			order.setOrderstatus(Orderstatus.Terminated);
			gazorderservice.updategazorder(order);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription("Order Updated");
			rb.setObject(order);
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("Order failed updated");
		}
		
		return new ResponseEntity<Object>(rb,HttpStatus.OK);
	}
	
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET, value="/find/{uuid}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findOne(HttpServletRequest request,@PathVariable String uuid){
		
		ResponseBean rb= new ResponseBean();
		
		try {
			Optional<Gazorder> gr= gazorderservice.findByUuid(uuid);
			Gazorder gaz = gr.get();
			if(gaz == null) {
				rb.setCode(Messages.NOT_FOUND);
				rb.setDescription("Branch not found");
			}else {
				rb.setCode(Messages.SUCCESS_CODE);
				rb.setDescription(Messages.save);
				rb.setObject(gaz);
			}
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to found it");
		}
		return new ResponseEntity<Object>(rb,HttpStatus.OK);
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
	                
	                Optional<Gazorder> gt=gazorderservice.findByUuid(uuid);
	                Gazorder gazorder = gt.get();
	                
               ByteArrayInputStream bis = new ByteArrayInputStream(gazorderservice.OrderGazDetailsPDF(gazorder));

	                return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/pdf"))
	                    .body(new InputStreamResource(bis));
	        
	        } catch (Exception ex) {
	            responseBean.setCode(Messages.ERROR_CODE);
	            responseBean.setDescription(Messages.error);
	            responseBean.setObject(null);
	        }
	        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	    }
	    
	
	
}
