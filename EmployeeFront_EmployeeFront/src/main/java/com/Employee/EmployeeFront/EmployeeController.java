package com.Employee.EmployeeFront;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Employee.EmployeeFront.Domain.Registrant;
import com.Employee.EmployeeFront.Domain.SystemUser;

@RestController
public class EmployeeController {

	
	@RequestMapping(value="/add_user/session", method=RequestMethod.POST)
	public Object add_user_to_session(HttpSession session, @RequestParam Map<String,String> param) {
		
		try {
			
			SystemUser user = new SystemUser();
			
			user.setUsername(param.get("username"));
			user.setFirstname(param.get("firstname"));
			user.setLastname(param.get("lastname"));
			//user.setRole("role");
			
			session.setAttribute("a_user", user);
			return "OK";
			
		}catch(Exception ex) {
			
			return ""+ex.getMessage();
		}
		
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public Object logout(HttpSession session, @RequestParam Map<String, String> param) {
		
		try {
			session.removeAttribute("a_user");
			session.removeAttribute("a_reg");
			return "OK";
		}catch(Exception e) {
			
			return ""+e.getMessage();
		}
	}
	
	@RequestMapping(value="/add_registrant/session", method= RequestMethod.GET, consumes= MediaType.APPLICATION_JSON_VALUE)
	public Object add_registrant_to_session(HttpSession session, @RequestBody Registrant reg ) {
		
		try {
			
			session.setAttribute("a_reg", reg);
			
		}catch(Exception e) {
			
			return ""+e.getMessage();
			
		}
		
		return new ResponseEntity<Object>(reg,HttpStatus.OK);
	}
	
}
