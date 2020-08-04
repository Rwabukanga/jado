package com.Employee.EmployeeFront.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Employee.EmployeeFront.Domain.District;
import com.Employee.EmployeeFront.Domain.Province;
import com.Employee.EmployeeFront.ServiceImplementation.DistrictImplementation;
import com.Employee.EmployeeFront.ServiceImplementation.ProvinceImplementation;

@Controller
@CrossOrigin
@RequestMapping(value="locationn")
public class LocationController {

	@Autowired
	private ProvinceImplementation proimp;
	
	@Autowired
	private DistrictImplementation dimp;
	
	@CrossOrigin
	@GetMapping("/province" )
	public List<Province> findprovince(){
 		
		return(List<Province>) proimp.findall(Province.class);
	}
	@CrossOrigin
	@GetMapping("/district")
	public List<District> findallDistrict(){
		
		return(List<District>) dimp.findall(District.class);
	}
	
	@GetMapping("/locations")
	@CrossOrigin
	public Object findAll(){
		Map<String, Object> tr = new HashMap<String,Object>();
		
		tr.put("provinces", proimp.findall(Province.class));
		tr.put("districts", dimp.findall(District.class));
		
		return tr;
	}
	
	
	private static class InnerLocation{
		
		private Object object;

		public Object getObject() {
			return object;
		}

		public void setObject(Object object) {
			this.object = object;
		}
		
		
	}
}
