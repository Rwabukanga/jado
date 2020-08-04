package com.Solar.SolarEnergy.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Solar.SolarEnergy.Domain.District;
import com.Solar.SolarEnergy.Domain.Province;
import com.Solar.SolarEnergy.Service.DistrictService;
import com.Solar.SolarEnergy.Service.Provinceservice;
import com.Solar.SolarEnergy.ServiceImplementation.DistrictImplementation;
import com.Solar.SolarEnergy.ServiceImplementation.ProvinceImplementation;

@RestController
@CrossOrigin
@RequestMapping(value="locationn")
public class LocationControllerr {

	@Autowired
	private ProvinceImplementation pimp;
	@Autowired
	private DistrictImplementation dimp;
	

	@CrossOrigin
	@GetMapping("/province")
	public List<Province> findProvince(){
		return (List<Province>) pimp.findAll(Province.class);
	}
	
	@CrossOrigin
	@GetMapping("/district")
	public List<District> findDistrict(){
		return (List<District>) dimp.findAll(District.class);
		
	}
	
	@CrossOrigin
	@GetMapping("/locations")
	public Object findAll() {
		Map<String,Object> dr=new HashMap<String,Object>();
		
		dr.put("provinces", pimp.findAll(Province.class));
		dr.put("districts", dimp.findAll(District.class));
		
		return dr;
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
