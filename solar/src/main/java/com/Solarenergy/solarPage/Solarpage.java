/*package com.Solarenergy.solarPage;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Solarenergy.solar.SolarController.Registrant;
import com.Solarenergy.solar.SolarController.SystemUser;

public class Solarpage {

	
	 * Method to return Dashboard page
	 
	@RequestMapping("/")
	public String landingPage() {

		return "landing/index";
		//return "DashBoard";
	}
	
	@RequestMapping("/login")
	public String login() {

		return "login";
		//return "DashBoard";
	}
	
	@RequestMapping("/registration")
	public String Registration() {

		return "Registration/NewRegistrant";
		//return "DashBoard";
	}
	
	@RequestMapping("/dashboard")
	public String Dashboard() {

		//return "Registration/NewRegistrant";
		return "DashBoard";
	}
	
	
@RequestMapping("/{page}")
  public String getPages(@PathVariable("page")String page,HttpSession session, Model model) {
	 if (session.getAttribute("a_user") != null) {
	        SystemUser user = (SystemUser) session.getAttribute("a_user");
	        
	        if (page.equalsIgnoreCase("dashboard")) {
	            
	            if(user.getRole().equalsIgnoreCase("SuperAdmin")){
	            return "Dashboard";
	            }else{
	                Registrant r=(Registrant) session.getAttribute("a_reg");
	                
	                if(r.getRegistrantCategory().equalsIgnoreCase("CITIZEN")){
	                    return "CitizenDashBoard";
	                }else if(r.getRegistrantCategory().equalsIgnoreCase("LAWYER")){
	                    return "lawyerDashboard";
	                }else if(r.getRegistrantCategory().equalsIgnoreCase("UPCOMING_LAWYER")){
	                    return "UpcomingDashboard";
	                }else if(r.getRegistrantCategory().equalsIgnoreCase("COUNCIL")){
                     return "redirect:/CouncilDashboard";
					}else{
	                    return "_noAccess";
	                }
	            }
	        } else if (page.equalsIgnoreCase("users")) {
	            return "UserManagement/Users";
	        } else if (page.equalsIgnoreCase("new_user")) {
	            return "UserManagement/NewUser";
	        } else if (page.equalsIgnoreCase("memberships")) {
	            return "Membership/Memberships";
	        } else if (page.equalsIgnoreCase("new_membership")) {
	            return "Membership/NewMembership";
	        } else if (page.equalsIgnoreCase("membershiprequirements")) {
	            return "Membership/Requirements";
	        }
	        else if (page.equalsIgnoreCase("province")) {
	            if (user.getRole().equalsIgnoreCase("SuperAdmin")) {
	                return "Location/Province";
	            } else {
	                return "_noAccess";
	            }
	        } else if (page.equalsIgnoreCase("new_province")) {
	            if (user.getRole().equalsIgnoreCase("SuperAdmin")) {
	                return "Location/NewProvince";
	            } else {
	                return "_noAccess";
	            }
	        }
	        else if (page.equalsIgnoreCase("district")) {
	            if (user.getRole().equalsIgnoreCase("SuperAdmin")) {
	                return "Location/District";
	            } else {
	                return "_noAccess";
	            }
	        }
	        else if (page.equalsIgnoreCase("new_district")) {
	            if (user.getRole().equalsIgnoreCase("SuperAdmin")) {
	                return "Location/NewDistrict";
	            } else {
	                return "_noAccess";
	            }
	        }
	        else if (page.equalsIgnoreCase("profile")) {
	            if (user.getRole().equalsIgnoreCase("Entity Manager")) {
	                return "Registration/Profile";
	            } else {
	                return "_noAccess";
	            }
	        }

	    else{
	    return "_404";
	}
	    } else {
	        return "redirect:/";

	    }

	}		
}
*/