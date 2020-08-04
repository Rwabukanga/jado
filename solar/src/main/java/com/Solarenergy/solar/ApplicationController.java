package com.Solarenergy.solar;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.Solarenergy.solar.SolarController.Registrant;
import com.Solarenergy.solar.SolarController.SystemUser;

@Controller
public class ApplicationController {
	
	/*@RequestMapping("/dashboard")
	public String dashboard() {
		return "Dashboard";
	}
	*/
	@RequestMapping("/login")
	public String login() {
		return "index";
	}

	/*@RequestMapping("/contract")
	public String ContractForm() {
		return "ContractForm";
	}*/
	
	/*@RequestMapping("/contractlist")
	public String ContractFormL() {
		return "ContractFormList";
	}
	*/
	
	@RequestMapping("/page")
	public String ContractFormL() {
		return "HomePage";
	}
	
	
	@RequestMapping("/save")
	public String saveregistrar() {
		return "NewRegistration";
	}
	@RequestMapping("/loginn")
	public String loginn(){
		return "login";
	}
	
	/* @RequestMapping("/list")
	 public String List() {
		 return"BranchList";
	 }*/
	@RequestMapping("/account")
	public String acc() {
		return "Account";
	}
	
	/*@RequestMapping("/branch")
	public String CreateeBranch(){
		return "CreateBranch";
	}*/
	
	@RequestMapping("/branchUser")
	public String createeBranchUser() {
		return "createBranchUser";
	}
	
	@RequestMapping("/registrantlist")
	public String RegistrantList() {
		return "Registrar";
	}
	
	/*@RequestMapping("/contcus")
	public String contractcustomer() {
		return "ContractCustomer";
	}
	*/
	
	/*@RequestMapping("/userlist")
	public String UserList() {
		return "BranchUserList";
	}*/

	/*@RequestMapping("/enlist")
	public String energyList() {
		return "solarenergylist";
	}*/
	
	
	@RequestMapping("/savecustomer")
	public String rgistCustomer() {
		return "RegisterCustomer";
	}
	
	@RequestMapping("/orderlist")
	public String order() {
		return "ListofOrder";
	}
	
	@RequestMapping("/pforder")
	public String registrarorder() {
		return "OrderlistRegistrar";
	}
	
	@RequestMapping("/contractl")
	public String contractcust() {
		return "ContractFormcust";
	}
	
	@RequestMapping("/payment")
	public String pay() {
		return "Paymentcustomer";
	}
	
	/*@RequestMapping("/send")
	public String order() {
		return "OrderPage";
	}*/
	
	/*@RequestMapping("/seller")
	public String createseller() {
		return "createsellertobranch";
	}*/
	
	/*@RequestMapping("/energy")
	public String solarEnergy() {
		return "CreateSolarenergy";
	}
	*/
	/*@RequestMapping("/solar")
	public String solarBranch() {
		return "createsolarBranch";
	}*/
	 
	/*@RequestMapping("/order")
	public String makingOrder() {
		return "MakingOrder";
	}*/
	
	@RequestMapping("/pay")
	public String makingOrder() {
		return "PaymentForm";
	}
	
	  @RequestMapping("/{page}")
	  public String getPages(@PathVariable("page")String page,HttpSession session, Model model) {
		 if (session.getAttribute("a_user") != null) {
		        SystemUser user = (SystemUser) session.getAttribute("a_user");
		        
		        if (page.equalsIgnoreCase("dashboard")) {
		            
		            if(user.getRole().equalsIgnoreCase("superAdmin")){
		            return "Dashboard";
		            
		            }else{
		                Registrant r=(Registrant) session.getAttribute("a_reg");
		                
		                if(r.getCategory().equalsIgnoreCase("Seller")){
		                    return "BranchDashboard";
		                }else if(r.getCategory().equalsIgnoreCase("Customer")){
		                    return "CustomerDashboard";
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
		        else if (page.equalsIgnoreCase("seller")) {
		            if (user.getRole().equalsIgnoreCase("superAdmin")) {
		                return "sellerDashboard";
		            } else {
		                return "_noAccess";
		            }
		        }
		        
		        
		        else if (page.equalsIgnoreCase("userlist")) {
		            if (user.getRole().equalsIgnoreCase("superAdmin")) {
		                return "BranchuserlistDashboard";
		            } else {
		                return "_noAccess";
		            }
		        }
		        
		        else if (page.contains("contractlist")) {
		            if (user.getRole().equalsIgnoreCase("user")) {
		            	String uuid[]=page.split("_");
		            	String buuid=uuid[0];
		            	model.addAttribute("contractuuid", buuid);
		                return "Sellercontract";
		            } else {
		                return "_noAccess";
		            }
		        }
		       
		        else if (page.contains("contract")) {
		            if (user.getRole().equalsIgnoreCase("user")) {
		            	String uuid[]=page.split("_");
		            	String buuid=uuid[1];
		            	model.addAttribute("contractuuid", buuid);
		                return "custdashboardcontract";
		            } else {
		                return "_noAccess";
		            }
		        }
		        
		        
		        else if (page.equalsIgnoreCase("contcus")) {
		            if (user.getRole().equalsIgnoreCase("user")) {
		                return "Customercontract";
		            } else {
		                return "_noAccess";
		            }
		        }
		        
		        
		        else if (page.contains("orderlist")) {
		            if (user.getRole().equalsIgnoreCase("user")) {
		            	String uuid[]=page.split("_");
		            	String buuid=uuid[1];
		            	model.addAttribute("branchuuid", buuid);
		                return "OrderlistDashboard";
		            } else {
		                return "_noAccess";
		            }
		        }
		        
		        else if (page.contains("pforder")) {
		            if (user.getRole().equalsIgnoreCase("user")) {
		            	String uuid[]=page.split("_");
		            	String buuid=uuid[1];
		            	model.addAttribute("branchuuid", buuid);
		                return "OrderlistRegistrar";
		            } else {
		                return "_noAccess";
		            }
		        }
		        
		        
		        
		        else if (page.equalsIgnoreCase("list")) {
		            if (user.getRole().equalsIgnoreCase("superAdmin")) {
		                return "BranchlistDashboard";
		            } else {
		                return "_noAccess";
		            }
		        }
		        
		        
		        else if (page.equalsIgnoreCase("enlist")) {
		            if (user.getRole().equalsIgnoreCase("user")) {
		                return "SolarenergyDashboard";
		            } else {
		                return "_noAccess";
		            }
		        }
		        
		        else if (page.contains("energy")) {
		            if (user.getRole().equalsIgnoreCase("user")) {
		            	String uuid[]=page.split("_");
		            	String buuid=uuid[1];
		            	model.addAttribute("branchuuid", buuid);
		                return "SolarinBranch";
		                
		            } else {
		                return "_noAccess";
		            }
		        } 
		        
		        else if (page.equalsIgnoreCase("branch")) {
		            if (user.getRole().equalsIgnoreCase("superAdmin")) {
		                return "NewBranch";
		            } else {
		                return "_noAccess";
		            }
		        }
		        
		      /*  else if (page.equalsIgnoreCase("branch")) {
		            if (user.getRole().equalsIgnoreCase("superAdmin")) {
		                return "NewBranch";
		            } else {
		                return "_noAccess";
		            }
		        }*/
		        
		        else if (page.contains("send")) {
		            if (user.getRole().equalsIgnoreCase("user")) {
		            	
		            	String uuid[]=page.split("_");
		            	String buuid=uuid[1];
		            	model.addAttribute("solaruuid", buuid);
		                return "Orderingproduct";
		            } else {
		                return "_noAccess";
		            }
		        }
		        
		        else if (page.equalsIgnoreCase("order")) {
		            if (user.getRole().equalsIgnoreCase("user")) {
		                return "ListofsolarDashboard";
		            } else {
		                return "_noAccess";
		            }
		        }
		        
		        else if (page.equalsIgnoreCase("new_province")) {
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

		    else if(page.contains("pay")){
		    	String uuid[]=page.split("_");
            	String buuid=uuid[1];
            	model.addAttribute("contractuuid", buuid);
                return "PaymentForm";
		    }else{
		    	
		    return "_404";
		}
		    } else {
		        return "redirect:/loginn";

		    }

		}		
}
