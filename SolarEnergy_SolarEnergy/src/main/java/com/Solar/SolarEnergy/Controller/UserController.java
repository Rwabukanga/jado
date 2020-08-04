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

import com.Solar.SolarEnergy.Domain.Solarenergy;
import com.Solar.SolarEnergy.Domain.Solarorder;
import com.Solar.SolarEnergy.Domain.User;
import com.Solar.SolarEnergy.Service.UserService;
import com.Solar.SolarEnergy.Utitlity.Messages;
import com.Solar.SolarEnergy.Utitlity.ResponseBean;

@RestController
@RequestMapping(value= "/user")
public class UserController {

	@Autowired
	private UserService userservice;
	
    @RequestMapping(value= "/save", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createSolarBranch(HttpServletRequest request, @RequestBody User user) {
		ResponseBean rb = new ResponseBean();
	
		try {
			
			            userservice.createUser(user);
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription(Messages.save);
						rb.setObject(user);
					
		
			
} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to create registrar");
			rb.setObject(user);
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
    @RequestMapping(value= "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAllUser() {
		ResponseBean rb = new ResponseBean();
		try {


					List<User> list = userservice.findAll();
					rb.setCode(Messages.SUCCESS_CODE);
					rb.setDescription("The user are successfuly retrieved");
					rb.setObject(list);


		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("error occured while retrieving solarenergy");
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
    
    @RequestMapping(method = RequestMethod.GET, value= "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findOne(HttpServletRequest request, @PathVariable int id) {

		ResponseBean rb = new ResponseBean();
		try {

			
				
					User user = userservice.findByid(id);
					if (user == null) {
						rb.setCode(Messages.NULLS_FOUND);
						rb.setDescription("The season was not found");
					} else {
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription("The branch is successfuly found");
						rb.setObject(user);
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

   					User user = userservice.findByid(id);

   					if (user == null) {
   						rb.setCode(Messages.NOT_FOUND);
   						rb.setDescription("The Season you want  Delete does not exit");
   					} else {

   						userservice.deleteUser(id);
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
    
}
