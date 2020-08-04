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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Employee.EmployeeFront.Domain.District;
import com.Employee.EmployeeFront.Domain.Province;
import com.Employee.EmployeeFront.Service.DistrictService;
import com.Employee.EmployeeFront.Service.ProvinceService;
import com.Employee.EmployeeFront.Utility.Messages;
import com.Employee.EmployeeFront.Utility.ResponseBean;

@Controller
@RequestMapping(value="/location")
public class LocationnController {
	
	@Autowired
	private ProvinceService provinceservice;
	
	@Autowired
	private DistrictService districtservice;
	
	
	@CrossOrigin
	@RequestMapping(value="/province", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAll(){
		
		ResponseBean rb= new ResponseBean();
		
		try {
			List<Province> list= provinceservice.findall(Province.class);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription(Messages.save);
			rb.setObject(list);
		}catch(Exception ex) {
			
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed retrived list");
			
		}
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET,value="/province/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findOne(HttpServletRequest request, @PathVariable int id){
		
		ResponseBean rb= new ResponseBean();
		
		try {
			Optional<Province> pr= provinceservice.findById(id);
			Province province = pr.get();
			if(province == null) {
				rb.setCode(Messages.NOT_FOUND);
				rb.setDescription("failed");
			}else {
				rb.setCode(Messages.SUCCESS_CODE);
				rb.setDescription(Messages.save);
				rb.setObject(province);
			}
			
			
		}catch(Exception ex) {
			
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed retrived list");
			
		}
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	
	
	@CrossOrigin
	@RequestMapping(value="/district", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findallDistrict(){
		
		ResponseBean rb= new ResponseBean();
		
		try {
			List<District> list= districtservice.findall(District.class);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription(Messages.save);
			rb.setObject(list);
		}catch(Exception ex) {
			
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed retrived list");
			
		}
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value="/district/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findbydistrict(HttpServletRequest request, @PathVariable int id){
		
		ResponseBean rb= new ResponseBean();
		try {
			List<District> list= districtservice.findByProvinceId(id);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription("Retrive it");
			rb.setObject(list);			
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("Error to retrieve");
		}
				
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	/*@CrossOrigin
	@RequestMapping(value="/district/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findBydistrict(HttpServletRequest request ,@PathVariable int id){
		
		ResponseBean rb = new ResponseBean();
		
		try {
			List<District> district = districtservice.findbyProvince(id);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription("Find it");
			rb.setObject(district);
			
			
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed");
						
		}
		
		
		
		
		
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
*/

}
