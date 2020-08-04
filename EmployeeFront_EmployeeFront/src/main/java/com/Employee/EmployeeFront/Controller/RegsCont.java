package com.Employee.EmployeeFront.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Employee.EmployeeFront.Domain.Registrant;
import com.Employee.EmployeeFront.Domain.SystemUser;
import com.Employee.EmployeeFront.Service.ISystemUserService;
import com.Employee.EmployeeFront.Service.RegistrarService;
import com.Employee.EmployeeFront.Utility.Messages;
import com.Employee.EmployeeFront.Utility.Msg;
import com.Employee.EmployeeFront.Utility.ResponseBean;

@RestController
@RequestMapping(value= "/reg")
public class RegsCont  {
   
	@Autowired
	private RegistrarService regservice;
	
	@Autowired
	private ISystemUserService systemservice;
	
	
    @CrossOrigin
	@RequestMapping(value="/save", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createReg(HttpServletRequest request, @RequestBody Registrant reg){
		
		ResponseBean rb = new ResponseBean();
		
		try {
			regservice.createregistrar(reg);
			/*rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription("Success");
			rb.setObject(reg);*/
			
			SystemUser user = new SystemUser();
			//user.setId(reg.getId());
			user.setFirstname(reg.getFirstname());
            user.setLastname(reg.getLastname());
            user.setEmailAddress(reg.getEmail());
            user.setGender(reg.getGender());
            user.setNationalId(reg.getIdnumber());
            user.setPhone(reg.getPhone());
            //user.setRole("Admin");
            user.setReg(reg);
			
			systemservice.create(user);
			
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription(Messages.save);
			rb.setObject(user);
			
		}catch(Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("Error in retrieving");
		}
		
		return new  ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	
	
	
}
