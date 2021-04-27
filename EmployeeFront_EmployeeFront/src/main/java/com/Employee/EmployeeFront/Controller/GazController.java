package com.Employee.EmployeeFront.Controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Employee.EmployeeFront.Domain.Branchuser;
import com.Employee.EmployeeFront.Domain.Gaz;
import com.Employee.EmployeeFront.Service.Branchservice;
import com.Employee.EmployeeFront.Service.BranchuserService;
import com.Employee.EmployeeFront.Service.GazService;
import com.Employee.EmployeeFront.Utility.Messages;
import com.Employee.EmployeeFront.Utility.ResponseBean;

@Controller
@RequestMapping(value="/gaz")
@CrossOrigin
public class GazController{

	@Autowired
	private GazService gazservice;
	
	@Autowired
	private Branchservice branchservice;
	
	@Autowired
	private BranchuserService branchuserservice;
	
	@CrossOrigin
	@RequestMapping(value="/save/{uuid}", method=RequestMethod.POST,  consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createGazorder(HttpServletRequest request,@RequestBody InnerGaz gaz,@PathVariable String uuid){
		ResponseBean rb = new ResponseBean();
		try {
			
			Optional<Branchuser> branch = branchuserservice.findByUuid(uuid);
			Branchuser br = branch.get();
			Gaz gg = new Gaz();
 			gg.setBranchuser(br);
			gg.setDescription(gaz.getDescription());
			gg.setQuality(gaz.getQuality());
			gg.setQuantity(gaz.getQuantity());
			gg.setCost(gaz.getCost());
			gazservice.createGaz(gg);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription(Messages.save);
			rb.setObject(gg);
			
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("fail to save branch");
		
		}
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	/*@CrossOrigin
	@RequestMapping(value="/savegaz" ,method= RequestMethod.POST)
	public ResponseEntity<Object> event_self_reg (HttpServletRequest request,@RequestParam Map<String, String> params,HttpSession session,@RequestParam(name = "attachment-0", required = false) MultipartFile file){
	
		ResponseBean rb = new ResponseBean();
		try {
			
			String filePath ="";
			
			String uploadFilePath="";
			
			if(file != null) {
				
				long fileSizeInBytes = file.getSize();
				
				double fileSizeInKB = fileSizeInBytes / 1024;
				
				double fileSizeInMB = fileSizeInKB / 1024;
				
				if(fileSizeInMB <= 5) {
					
					Gaz gaz = new Gaz();
					String branchuuid= params.get("branchid");
					Optional<Branchuser> br = branchuserservice.findByUuid(branchuuid);
					Branchuser branch = br.get();
					gaz.setQuality(params.get("quality"));
					gaz.setDescription(params.get("description"));
					gaz.setQuantity(Integer.parseInt("quantity"));
					gaz.setCost(Double.parseDouble("cost"));
					gaz.setBranchuser(branch);
					
					gazservice.createGaz(gaz);
					
					   uploadFilePath = gaz.getId() + "-"
				              + file.getOriginalFilename().replaceAll("\\s", "_");
				          filePath = "jado/school book/photos clipart";
				          File f = new File(filePath);
				          if (!f.exists()) {
				            f.mkdirs();
				          }
				          byte[] bytes = file.getBytes();
				          Path path = Paths.get(filePath + "/" + uploadFilePath);
				          Files.write(path, bytes);
			              gaz.setFile(path.toString()); 
				          gazservice.updateGaz(gaz);
				          
				          rb.setCode(Messages.SUCCESS_CODE);
				          rb.setDescription(Messages.save);
				          rb.setObject(gaz);
				          
				}else {
					rb.setCode(Messages.ERROR_CODE);
					rb.setDescription("Error Occured");
					rb.setObject(null);
				}
					
			}else {
				 rb.setCode(Messages.ERROR_CODE);
			     rb.setDescription("Please Attach Photo");
			     rb.setObject(null);
				
			}
			
		}catch(Exception ex) {
		
			 rb.setCode(Messages.ERROR_CODE);
		     rb.setDescription(Messages.error);
		     rb.setObject(null);

			
		}
		
		
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}*/
	
	@CrossOrigin
	@RequestMapping(value="/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAll(){
		
		ResponseBean rb = new ResponseBean();
		try {
			
			List<Gaz> list = gazservice.findAll(Gaz.class);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription(Messages.save);
			rb.setObject(list);
			
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("Failed to return list");
			rb.setObject(ex);
		}
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.DELETE, value="/delete/{id}")
	public ResponseEntity<Object> deletegaz(HttpServletRequest request, @PathVariable int id){
		ResponseBean rb = new ResponseBean();
		try {
			Optional<Gaz> br= gazservice.findById(id);
			Gaz cz= br.get();
			if(cz == null) {
				rb.setCode(Messages.NOT_FOUND);
				rb.setDescription("not deleted");
				
			}else {
				gazservice.delete(cz);
				rb.setCode(Messages.SUCCESS_CODE);
				rb.setDescription("deleted successfully");
				rb.setObject(cz);		
			}
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to delete branch");
			
		}
		return new ResponseEntity<Object>(rb,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.PUT, value= "/update/{uuid}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateBranch(HttpServletRequest request,@PathVariable String uuid,@RequestBody Gaz gaz){
		
		ResponseBean rb = new ResponseBean();
		
		try {
			Optional<Gaz> br= gazservice.findByUuid(uuid);
			Gaz or= br.get(); 
			//or.setBranch(gaz.getBranch());
			or.setCost(gaz.getCost());
			or.setDescription(gaz.getDescription());
			or.setQuality(gaz.getQuality());
			or.setQuantity(gaz.getQuantity());
			gazservice.updateGaz(or);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription(Messages.save);
			rb.setObject(or);
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("Failed to update branchuser");
		}
		return new ResponseEntity<Object>(rb,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET, value="/find/{uuid}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findOne(HttpServletRequest request,@PathVariable String uuid){
		
		ResponseBean rb= new ResponseBean();
		
		try {
			Optional<Gaz> gr = gazservice.findByUuid(uuid);
			Gaz gaz = gr.get();
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
	
	public static class InnerGaz{
		
		private String quality;
		private int quantity;
		private String description;
		private double cost;
		public String getQuality() {
			return quality;
		}
		public void setQuality(String quality) {
			this.quality = quality;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public double getCost() {
			return cost;
		}
		public void setCost(double cost) {
			this.cost = cost;
		}
		
		
		
	}
}
