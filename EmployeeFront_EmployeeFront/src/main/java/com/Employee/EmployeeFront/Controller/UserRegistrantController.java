package com.Employee.EmployeeFront.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Employee.EmployeeFront.Domain.UserRegistrant;
import com.Employee.EmployeeFront.Service.UserRegistrantService;

@CrossOrigin
@Controller
@RequestMapping(value="/userregistrant")
public class UserRegistrantController {
    	
	@Autowired
	private UserRegistrantService userregservice;
   
   @CrossOrigin
   @RequestMapping(value="/adde", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
   public void createuser(HttpServletRequest request,HttpServletResponse response) throws ParseException, IOException {
	   
	  
	   
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   
	   UserRegistrant us = new UserRegistrant();
	   
	   us.setSessionid(request.getParameter("sessionid"));
	   us.setMsisdn(request.getParameter("msisdn"));
	   us.setEmail(request.getParameter("email"));
	   us.setIdentitynumber(request.getParameter("identitynumber"));
	   us.setLang(request.getParameter("lang"));
	   us.setLocation(request.getParameter("location"));
	   us.setCreatedat(sdf.parse(request.getParameter("createdat")));
	   
	   userregservice.createuserreg(us);
	   
	   String Msg = "My phonenumber is "  + us.getMsisdn() + " mySessionid " + us.getSessionid() + " My Email = "  + us.getEmail()+ ". My identity cards " +us.getIdentitynumber()+ ". The Language is" +us.getLang()+ ".The Location is"+us.getLocation()+ ". The created date is"+us.getCreatedat();
        
		PrintWriter out = response.getWriter();
		out.println(Msg);
	   
   }
   
}
