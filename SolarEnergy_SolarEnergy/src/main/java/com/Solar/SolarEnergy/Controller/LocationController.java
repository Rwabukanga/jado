package com.Solar.SolarEnergy.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Solar.SolarEnergy.Domain.Location;
import com.Solar.SolarEnergy.Service.LocationService;
import com.Solar.SolarEnergy.Utitlity.Messages;
import com.Solar.SolarEnergy.Utitlity.ResponseBean;

@RestController
@RequestMapping(value="/location")
public class LocationController {

@Autowired	
private LocationService locationservice;
	
	@RequestMapping(value ="/save", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createLocation(HttpServletRequest request, @RequestBody Location loc ) {
		ResponseBean rb = new ResponseBean();
	
		try {
			            locationservice.createLocation(loc);
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription(Messages.save);
						rb.setObject(loc);
					
		
			
} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to create Season");
			rb.setObject(loc);
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
}
}
