package com.Employee.EmployeeFront.Controller;

import java.sql.Date;
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

import com.Employee.EmployeeFront.Domain.Branch;
import com.Employee.EmployeeFront.Domain.District;
import com.Employee.EmployeeFront.Domain.Province;
import com.Employee.EmployeeFront.Service.Branchservice;
import com.Employee.EmployeeFront.Service.DistrictService;
import com.Employee.EmployeeFront.Service.ProvinceService;
import com.Employee.EmployeeFront.ServiceImplementation.BranchImplementation;
import com.Employee.EmployeeFront.Utility.Messages;
import com.Employee.EmployeeFront.Utility.ResponseBean;

@Controller
@RequestMapping(value="/branch")
public class BranchController {

	@Autowired
	private Branchservice branchservice;
	
	@Autowired
	private ProvinceService provinceservice;
	
	@Autowired
	private DistrictService districtservice;
	
	@Autowired
	private BranchImplementation  branchserviceimpl;
	
	@CrossOrigin
	@RequestMapping(value="/save", method=RequestMethod.POST,  consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createBranch(HttpServletRequest request,@RequestBody InnerBranch branch){
		
		ResponseBean rb = new ResponseBean();
		try {
			
			Branch b = new Branch();
			
			/*Optional<District> district = districtservice.findById(branch.getDistrict());
			District dis = district.get();*/
			b.setBranchname(branch.getBranchname());
			b.setEmail(branch.getEmail());
			b.setStartdate(branch.getStartdate());
			b.setPhonenumber(branch.getPhonenumber());
		/*	b.setDistrict(dis);
			*/
			branchservice.createbranch(b);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription("branch saved");
			rb.setObject(branch);
			
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("fail to save branch");
		}
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value="/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAll(){
		
		ResponseBean rb = new ResponseBean();
		try {
			
			List<Branch> list= branchservice.findAll(Branch.class);
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
	public ResponseEntity<Object> deletebranch(HttpServletRequest request, @PathVariable int id){
		ResponseBean rb= new ResponseBean();
		try {
			Optional<Branch> br= branchservice.findById(id);
			Branch branch= br.get();
			if(branch == null) {
				rb.setCode(Messages.NOT_FOUND);
				rb.setDescription("not deleted");
				
			}else {
				branchservice.delete(branch);
				rb.setCode(Messages.SUCCESS_CODE);
				rb.setDescription("deleted successfuly");
				rb.setObject(branch);		
			}
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to delete branch");
			
		}
		return new ResponseEntity<Object>(rb,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.PUT, value= "/update/{uuid}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateBranch(HttpServletRequest request,@PathVariable String uuid,@RequestBody Branch brn){
		
		ResponseBean rb = new ResponseBean();
		
		try {
			Optional<Branch> br= branchservice.findByUuid(uuid);
			Branch branch = br.get();
			branch.setBranchname(brn.getBranchname());
			branch.setStartdate(brn.getStartdate());
			branch.setEmail(brn.getEmail());
			branch.setPhonenumber(brn.getPhonenumber());
			/*brn.setDistrict(branch.getDistrict());
			brn.setProvince(branch.getProvince());*/
		
			branchservice.updatebranch(branch);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription(Messages.save);
			rb.setObject(branch);
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("Failed to update branch");
		}
		return new ResponseEntity<Object>(rb,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET, value="/find/{uuid}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findOne(HttpServletRequest request,@PathVariable String uuid){
		
		ResponseBean rb= new ResponseBean();
		
		try {
			Optional<Branch> br= branchservice.findByUuid(uuid);
			Branch branch= br.get();
			if(branch == null) {
				rb.setCode(Messages.NOT_FOUND);
				rb.setDescription("Branch not found");
			}else {
				rb.setCode(Messages.SUCCESS_CODE);
				rb.setDescription(Messages.save);
				rb.setObject(branch);
			}
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to found it");
		}
		return new ResponseEntity<Object>(rb,HttpStatus.OK);
	}
	
	public static class InnerBranch{
		
		private String branchname;
		
		private Date startdate;
		
		private String email;
		
		private String phonenumber;
		
		private int district;

		public String getBranchname() {
			return branchname;
		}

		public void setBranchname(String branchname) {
			this.branchname = branchname;
		}

		public Date getStartdate() {
			return startdate;
		}

		public void setStartdate(Date startdate) {
			this.startdate = startdate;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public int getDistrict() {
			return district;
		}

		public void setDistrict(int district) {
			this.district = district;
		}

		public String getPhonenumber() {
			return phonenumber;
		}

		public void setPhonenumber(String phonenumber) {
			this.phonenumber = phonenumber;
		}
		
		
		
		
	}
	
}
