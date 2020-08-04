package com.Solar.SolarEnergy.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Solar.SolarEnergy.Domain.Branch;
import com.Solar.SolarEnergy.Domain.Registrar;
import com.Solar.SolarEnergy.Domain.SolarBranch;
import com.Solar.SolarEnergy.Service.SolarBranchService;
import com.Solar.SolarEnergy.Utitlity.Messages;
import com.Solar.SolarEnergy.Utitlity.ResponseBean;

@RestController
@RequestMapping(value="/solarbranch")
public class SolarBranchController {
	
	@Autowired
	private SolarBranchService solarbranchservice;

	@RequestMapping(value="/save", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createSolarBranch(HttpServletRequest request, @RequestBody SolarBranch solarbranch) {
		ResponseBean rb = new ResponseBean();
	
		try {
			            solarbranchservice.createSolarBranch(solarbranch);           
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription(Messages.save);
						rb.setObject(solarbranch);
					
		
			
} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to create registrar");
			rb.setObject(solarbranch);
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.GET, value= "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findOne(HttpServletRequest request, @PathVariable int id) {

		ResponseBean rb = new ResponseBean();
		try {

			
				
					SolarBranch solar = solarbranchservice.findByid(id);
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
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public ResponseEntity<Object> delete(HttpServletRequest request, @PathVariable int id) {
        
		ResponseBean rb = new ResponseBean();
		try {

					SolarBranch solar = solarbranchservice.findByid(id);

					if (solar == null) {
						rb.setCode(Messages.NOT_FOUND);
						rb.setDescription("The Season you want  Delete does not exit");
					} else {

						solarbranchservice.deleteSolarBranch(id);
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
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> viewAllSolarBranch() {
		ResponseBean rb = new ResponseBean();
		try {


					List<SolarBranch> list = solarbranchservice.findAll();
					rb.setCode(Messages.SUCCESS_CODE);
					rb.setDescription("The seasons are successfuly retrieved");
					rb.setObject(list);


		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("error occured while retrieving seasons");
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}

}
