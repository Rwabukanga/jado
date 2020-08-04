package com.Employee.EmployeeFront.Controller;

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

import com.Employee.EmployeeFront.Domain.BK;
import com.Employee.EmployeeFront.Service.BkService;
import com.Employee.EmployeeFront.Utility.Messages;
import com.Employee.EmployeeFront.Utility.ResponseBean;

@Controller
@CrossOrigin
@RequestMapping("/bkk")
public class BkController {
	
	@Autowired
	private BkService bkservice;
	
	@CrossOrigin
	@RequestMapping(value="/save", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(HttpServletRequest request, @RequestBody BK bk){
		
		ResponseBean rb = new ResponseBean();
		
		try {
			bkservice.create(bk);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription("Success to register");
			rb.setObject(bk);
			
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("Failed to Register");
		}
		
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
		
	}

}
