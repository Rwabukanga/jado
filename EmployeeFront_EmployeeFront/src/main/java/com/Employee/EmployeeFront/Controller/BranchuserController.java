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
import org.springframework.web.bind.annotation.RestController;

import com.Employee.EmployeeFront.Domain.Branch;
import com.Employee.EmployeeFront.Domain.Branchuser;
import com.Employee.EmployeeFront.Domain.District;
import com.Employee.EmployeeFront.Domain.Gender;
import com.Employee.EmployeeFront.Domain.Province;
import com.Employee.EmployeeFront.Service.Branchservice;
import com.Employee.EmployeeFront.Service.BranchuserService;
import com.Employee.EmployeeFront.Service.DistrictService;
import com.Employee.EmployeeFront.Service.ProvinceService;
import com.Employee.EmployeeFront.Utility.Messages;
import com.Employee.EmployeeFront.Utility.ResponseBean;

@RestController
@Controller
@RequestMapping(value="/branchuser")
public class BranchuserController {

	@Autowired
	private BranchuserService branchuserservice;
	
	@Autowired
	private ProvinceService provinceservice;
	
	@Autowired
	private DistrictService districtservice;
	
	@Autowired
	private Branchservice branchservice;
	
	@CrossOrigin
	@RequestMapping(value="/save", method=RequestMethod.POST,  consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createBranchuser(HttpServletRequest request,@RequestBody Branchuser branchuser){
		ResponseBean rb = new ResponseBean();
		try {
			
			/*Optional<Province> province= provinceservice.findById(id);
			Province p= province.get();
			Optional<District> district= districtservice.findById(id);
			District d= district.get();*/
			/*Optional<Branch> branch= branchservice.findById(id);
			Branch b= branch.get();*/
			/*branchuser.setDistrict(d);
			branchuser.setProvince(p);*/
			/*branchuser.setBranch(b);*/
			branchuserservice.createbranchuser(branchuser);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription("branchuser saved");
			rb.setObject(branchuser);
			
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("fail to save branch");
			
		}
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value= "/all", method =RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findall(){
		
		ResponseBean rb = new ResponseBean();
		try {			
					List<Branchuser> list = branchuserservice.findAll(Branchuser.class);
					rb.setCode(Messages.SUCCESS_CODE);
					rb.setDescription("branchuser retrieved");
					rb.setObject(list);


		} catch (Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("error occured while retrieving branchuser");
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.DELETE, value="/delete/{id}")
	public ResponseEntity<Object> deletebranchuser(HttpServletRequest request, @PathVariable int id){
		ResponseBean rb= new ResponseBean();
		try {
			Optional<Branchuser> br= branchuserservice.findById(id);
			Branchuser branchuser= br.get();
			if(branchuser == null) {
				rb.setCode(Messages.NOT_FOUND);
				rb.setDescription("not deleted");
				
			}else {
				branchuserservice.delete(branchuser);
				rb.setCode(Messages.SUCCESS_CODE);
				rb.setDescription(Messages.save);
				rb.setObject(branchuser);		
			}
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to delete branch");
			
		}
		return new ResponseEntity<Object>(rb,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.PUT, value= "/update/{uuid}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateBranchuser(HttpServletRequest request,@PathVariable String uuid,@RequestBody Branchuser brn){
		
		ResponseBean rb = new ResponseBean();
		
		try {
			Optional<Branchuser> br= branchuserservice.findByUuid(uuid);
			Branchuser branchuser = br.get();
			/*branch.setDistrict(brn.getDistrict());
			branch.setProvince(brn.getProvince());*/
			branchuser.setFirstname(brn.getFirstname());
			branchuser.setLastname(brn.getLastname());
			branchuser.setEmail(brn.getEmail());
			branchuser.setDob(brn.getDob());
			branchuser.setPhonenumber(brn.getPhonenumber());
			branchuser.setGender(brn.getGender());
			
			branchuserservice.updatebranchuser(branchuser);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription("updated successfully");
			rb.setObject(branchuser);
			
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
			Optional<Branchuser> br= branchuserservice.findByUuid(uuid);
			Branchuser branchuser= br.get();
			if(branchuser == null) {
				rb.setCode(Messages.NOT_FOUND);
				rb.setDescription("Branchuser not found");
			}else {
				rb.setCode(Messages.SUCCESS_CODE);
				rb.setDescription(Messages.save);
				rb.setObject(branchuser);
			}
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to found it");
		}
		return new ResponseEntity<Object>(rb,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value="/saveuser/branch/{uuid}", method=RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createhuser(HttpServletRequest request,@RequestBody Branchuser branchuser,@PathVariable String uuid){
		ResponseBean rb = new ResponseBean();
	    Branchuser user = new Branchuser();
		try {
			
			/*Optional<Province> province= provinceservice.findById(id);
			Province p= province.get();
			Optional<District> district= districtservice.findById(id);
			District d= district.get();*/
			/*Optional<Branch> branch = branchservice.findByUuid(branchuser.getUuid());*/
			Optional<Branch> branch = branchservice.findByUuid(uuid);
			Branch b= branch.get();
			user.setFirstname(branchuser.getFirstname());
			user.setLastname(branchuser.getLastname());
			user.setDob(branchuser.getDob());
			user.setEmail(branchuser.getEmail());
			user.setPhonenumber(branchuser.getPhonenumber());
			user.setGender(branchuser.getGender());
			user.setBranch(b);
			branchuserservice.updatebranchuser(user);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription("branchuser saved");
			rb.setObject(user);
			
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("fail to save branchuser");
			
		}
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
}
