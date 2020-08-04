package com.Employee.EmployeeFront.Controller;

import java.util.HashMap;
import java.util.Map;

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
import com.Employee.EmployeeFront.Utility.Encryption;
import com.Employee.EmployeeFront.Utility.Messages;
import com.Employee.EmployeeFront.Utility.ResponseBean;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class SystemuserController {

	@Autowired
	private ISystemUserService systemservice;
	
	@CrossOrigin
	@RequestMapping(value="/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createsystemuser( HttpServletRequest request, @RequestBody SystemUser systemuser){
		
		ResponseBean rb = new ResponseBean();
		try {
			systemservice.create(systemuser);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription(Messages.save);
			rb.setObject(systemuser);
			/*String userToken = request.getHeader("myum_token");
			String doneBy = request.getHeader("doneby");
			if (userToken != null) {

				if (userToken.equalsIgnoreCase(Msg.token)) {
					systemUser.setDoneBy(doneBy);
					String message = systemservice.create(systemUser);
					System.out.println("SAVING SYSTEM USER CALLLED+++++++++++++++========");
					if (message.contains(Msg.save)) {
					
						responseBean.setCode(Msg.SUCCESS_CODE);
						responseBean.setDescription(Msg.save);
						responseBean.setObject(systemUser);
					} else {
						responseBean.setCode(Msg.ERROR_CODE);
						responseBean.setDescription("SOMETHING WENT WRONG TRY AGAIN ");
						responseBean.setObject(null);
					}
				} else {
					responseBean.setCode(Msg.INCORRECT_TOKEN);
					responseBean.setDescription("INCCORECT TOKEN ");
					responseBean.setObject(null);
				}

			} else {

				responseBean.setCode(Msg.TOKEN_NOT_FOUND);
				responseBean.setDescription(" TOKEN NOT FOUND ");
				responseBean.setObject(null);

			}*/
		}catch(Exception ex) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to register");
		}
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value="/login", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> login(@RequestBody Userrr reg){
		
		ResponseBean rb = new ResponseBean();
		try {
		if(reg != null) {
			Map<String, Object> map = new HashMap<>();
			
			if((systemservice.findByUsernameAndPassword(reg.getUsername(), Encryption.md5(reg.getPassword())) != null)) {
				
				SystemUser systemuser = systemservice.findByUsernameAndPassword(reg.getUsername(), Encryption.md5(reg.getPassword()));
				rb.setCode(Messages.SUCCESS_CODE);
				rb.setDescription(Messages.save);
				map.put("user", systemuser);
				rb.setObject(map);
				
			}else {
				rb.setCode(Messages.ERROR_CODE);
				rb.setDescription("Failed to match username and password");
				rb.setObject(null);
			}
		}else {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("Failed to match it");
			rb.setObject(null);
		}
		}catch(Exception e) {
			rb.setDescription("failed to pass it");
			rb.setObject(null);
		}
		return new ResponseEntity<Object>(rb,HttpStatus.OK);
		
	}
	
	
	public static class Userrr {
		
		private String username;
		private String password;
		
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
	}
}





