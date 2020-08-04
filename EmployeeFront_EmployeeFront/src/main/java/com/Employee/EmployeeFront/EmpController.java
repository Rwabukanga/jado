package com.Employee.EmployeeFront;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Employee.EmployeeFront.Domain.Registrant;
import com.Employee.EmployeeFront.Domain.SystemUser;

@Controller
public class EmpController {
	
	@RequestMapping(value="/page")
	public String getPages(@PathVariable("page")String page, HttpSession session, Model model) {
		
		if(session.getAttribute("a_user")!=null) {
			SystemUser user = (SystemUser) session.getAttribute("a_user");
			
			if(page.equalsIgnoreCase("dash")) {
				
				if(user.getRole().equalsIgnoreCase("Admin")) {
					
					return "Dashboard";
					
				}else {
					Registrant reg = (Registrant) session.getAttribute("a_reg");
					
					if(reg.getCategory().equals("Seller")){
						return "SelersDashboard";
						
					}else if(reg.getCategory().equals("Customer")){
						return "CustomerDashboard";
						
					}else {
						return "_noAccess";
					}
				}
				
			}else if(page.equalsIgnoreCase("users")) {
				return "UserManagement/Users";
			}else if(page.equalsIgnoreCase("new_user")) {
				return "UserManagement/NewUser";
			}else if(page.equalsIgnoreCase("memberships")) {
				return "Membership/Memberships";
			}else if(page.equalsIgnoreCase("new_membership")) {
				return "Membership/NewMembership";
			}else if(page.equalsIgnoreCase("membershiprequirements")) {
				return "Membership/Requirements";
			}
			
			else if(page.equalsIgnoreCase("add")) {
				if(user.getRole().equalsIgnoreCase("Admin")) {
					return "branchadd";
				}else {
					return "_noAccess";
				}
			}
	       
			else if(page.equalsIgnoreCase("all")) {
				if(user.getRole().equalsIgnoreCase("Admin")) {
					return "adduser";
				}else {
					return "_noAccess";
				}
			}
			else {
				return "404";
			}
			
		}else {
			return "redirect:/login";
		}
	}

}
