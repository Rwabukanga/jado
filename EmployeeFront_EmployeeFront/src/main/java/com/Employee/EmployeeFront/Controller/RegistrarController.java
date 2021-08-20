package com.Employee.EmployeeFront.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.Employee.EmployeeFront.Domain.BK;
import com.Employee.EmployeeFront.Domain.District;
import com.Employee.EmployeeFront.Domain.Gender;
import com.Employee.EmployeeFront.Domain.Province;
import com.Employee.EmployeeFront.Domain.Registrant;
import com.Employee.EmployeeFront.Domain.SystemUser;
import com.Employee.EmployeeFront.Service.Branchservice;
import com.Employee.EmployeeFront.Service.DistrictService;
import com.Employee.EmployeeFront.Service.ISystemUserService;
import com.Employee.EmployeeFront.Service.ProvinceService;
import com.Employee.EmployeeFront.Service.RegistrarService;
import com.Employee.EmployeeFront.Utility.Messages;
import com.Employee.EmployeeFront.Utility.ResponseBean;




@RestController
@CrossOrigin
@RequestMapping(value="/registrar")
public class RegistrarController {
	
	@Autowired
	private RegistrarService registrarservice;
	
	/*@Autowired
	private JavaEmail javaemail;*/
	
	/*
	 * @Autowired private Branchservice bservice;
	 * 
	 * 
	 * @Autowired private DistrictService districtservice;
	 * 
	 * @Autowired private ProvinceService provinceservice;
	 */
	@Autowired
	private ISystemUserService systemservice;
	/*@Autowired
    private JavaMailSender javaMailSender;
	*/
	 @Autowired
	    //@Qualifier("clientService")
	    public void setISystemUserService(ISystemUserService systemservice){
	        this.systemservice=systemservice;
	    }
	
	
	@RequestMapping(value="/save", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createRegistrar(HttpServletRequest request, @RequestBody Registrant reg) {
		ResponseBean rb = new ResponseBean();
	
		try {
			  String doneBy = request.getHeader("doneBy");
			            /*Optional<Province> pr = provinceservice.findById(id);
			            Province province = pr.get();
			            Optional<District>dis = districtservice.findById(pid);
			            District district = dis.get();
			            reg.setProvince(province);
			            reg.setDistrict(district);*/
			            registrarservice.createregistrar(reg);
			            rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription(Messages.save);
						rb.setObject(reg);
						
			            SystemUser user = new SystemUser();
			          
			           user.setFirstname(reg.getFirstname());
			           user.setLastname(reg.getLastname());
			           user.setEmailAddress(reg.getEmail());
			           user.setGender(reg.getGender());
			           user.setNationalId(reg.getIdnumber());
			           user.setPhone(reg.getPhone());
			           user.setUsername(reg.getUsername());
			           user.setPassword(reg.getPassword());
			        /*   user.setRole("user");*/
/*			           user.setCategory(reg.getCategory());*/
			           user.setDob(reg.getDob());
			            /*user.setUsername(reg.get);
			            user.setPassword(regAdmin.getPassword());*/
			            //user.setObjectId(reg.getId() + "");
			            user.setReg(reg);
			            systemservice.create(user);
			            /*final String uri = Messages.myum_url + "/registrar/save";
						RestTemplate restt = new RestTemplate();
						HttpHeaders headers = new HttpHeaders();
						headers.set("doneBy", doneBy);
						HttpEntity<SystemUser> en = new HttpEntity<SystemUser>(user, headers);
						ResponseBean res= restt.postForObject(uri,en, ResponseBean.class);
			            */
			         
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription(Messages.save);
						rb.setObject(user);
					    
		                
			
   } catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to create registrar");
			
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value="/savewithadmin", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createwithadmin(HttpServletRequest request, @RequestBody Registrant reg) {
		ResponseBean rb = new ResponseBean();
	
		try {
			  String doneBy = request.getHeader("doneBy");
			            /*Optional<Province> pr = provinceservice.findById(id);
			            Province province = pr.get();
			            Optional<District>dis = districtservice.findById(pid);
			            District district = dis.get();
			            reg.setProvince(province);
			            reg.setDistrict(district);*/
			            registrarservice.createregistrar(reg);
			            rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription(Messages.save);
						rb.setObject(reg);
						
			            SystemUser user = new SystemUser();
			          
			           user.setFirstname(reg.getFirstname());
			           user.setLastname(reg.getLastname());
			           user.setEmailAddress(reg.getEmail());
			           user.setGender(reg.getGender());
			           user.setNationalId(reg.getIdnumber());
			           user.setPhone(reg.getPhone());
			           user.setUsername(reg.getUsername());
			           user.setPassword(reg.getPassword());
			           user.setRole("Admin");
			           /*user.setCategory(reg.getCategory());*/
			           user.setDob(reg.getDob());
			            /*user.setUsername(reg.get);
			            user.setPassword(regAdmin.getPassword());*/
			            //user.setObjectId(reg.getId() + "");
			            user.setReg(reg);
			            systemservice.create(user);
			            /*final String uri = Messages.myum_url + "/registrar/save";
						RestTemplate restt = new RestTemplate();
						HttpHeaders headers = new HttpHeaders();
						headers.set("doneBy", doneBy);
						HttpEntity<SystemUser> en = new HttpEntity<SystemUser>(user, headers);
						ResponseBean res= restt.postForObject(uri,en, ResponseBean.class);
			            */
			         
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription(Messages.save);
						rb.setObject(user);
					    
		                
			
   } catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("failed to create registrar");
			
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	
	
	
	/*@CrossOrigin
	@RequestMapping(value = "/savewithadmin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createWithAdmin(@RequestBody RegAdmin regAdmin, HttpServletRequest request) {
        ResponseBean responseBean = new ResponseBean();
        try {
            String token = request.getHeader(Messages.token_name);
            
         //   if (token != null) {
            //    if(token.equalsIgnoreCase(Messages.token_name)){
                 Optional<Province> pr = provinceservice.findById(regAdmin.getProvinceid());
                 Province province = pr.get();
                 Optional<District> dis= districtservice.findById(regAdmin.getDistrictid());
                 District district = dis.get();
                 Registrant registrant = registrarservice.buildRegistrant(regAdmin);
                  
                  
                    *//**
                     * Checking if the Registrant with same Username exist
                     *//*

//                    registrant.setDistrict(district);

                    Optional<Registrant> optional = Optional
                         .ofNullable(registrarservice.findByid(registrant.getId()));
                    if (!optional.isPresent()) {
                        String doneBy = request.getHeader("doneBy");
//                        registrant.setProvince(province);
//                        registrant.setDistrict(district);
//                        
                        registrant.setProvince(province);
                        registrant.setDistrict(district);
                        registrant.setFirstname(regAdmin.getFirstname());
                        registrant.setLastname(regAdmin.getLastname());
                        registrant.setEmail(regAdmin.getEmail());
                        registrant.setDob(regAdmin.getDateofbirth());
                        registrant.setIdnumber(regAdmin.getIdnumber());
                        registrant.setPhone(regAdmin.getPhonenumber());
                        
                        registrarservice.createregistrar(registrant);
                        responseBean.setCode(Messages.SUCCESS_CODE);
                        responseBean.setObject(registrant);
                        responseBean.setDescription("saved successfuly");
                        
                        
                       SystemUser user = new SystemUser();
                       user.setUsername(regAdmin.getUsername());
                       user.setFirstname(regAdmin.getFirstname());
                       user.setLastname(regAdmin.getLastname());
                       user.setEmail(regAdmin.getEmail());
                       user.setGender(regAdmin.getGender());
                       user.setIdnumber(regAdmin.getIdnumber());
                       user.setRole(regAdmin.getRole());
                       user.setObjectName("registrant");
                       user.setPassword(regAdmin.getPassword());
                       user.setPhonenumber(regAdmin.getPhonenumber());
                       user.setApplicationName(regAdmin.getApplicationName());
                       user.setObjectId(registrant.getId() + "");
                    
                        
                        
                       
    
                        final String uri = Messages.myum_url + "/users/save";
                        RestTemplate restTemplate = new RestTemplate();
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.set("doneby", doneBy);
                        headers.set("myum_token", "MYUM" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
                        HttpEntity<SystemUser> entity = new HttpEntity<SystemUser>(user, headers);
                        ResponseBean response = restTemplate.postForObject(uri, entity, ResponseBean.class);
    
                    } else {
                        responseBean.setDescription("REGISTRANT with IDENTITY: " + registrant.getId() + " already exist");
                    }
                    
                }else{
                    responseBean.setCode(Messages.INCORRECT_TOKEN);
                    responseBean.setDescription("Incorrect token ");
                    responseBean.setObject(null);
                }
               
            } else {
                responseBean.setCode(Messages.TOKEN_NOT_FOUND);
                responseBean.setDescription(" TOKEN NOT FOUND ");
                responseBean.setObject(null);
            }
            
        } catch (Exception ex) {
           
           responseBean.setCode(Messages.ERROR_CODE);
           responseBean.setDescription(Messages.error);
           responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }
	*/
	/*@CrossOrigin
	@RequestMapping(value = "/savewithseller/{buuid}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createWithbranchuser(@RequestBody RegAdmin regAdmin, HttpServletRequest request, @PathVariable String buuid ) {
        ResponseBean responseBean = new ResponseBean();
        try {
            String token = request.getHeader(Messages.token_name);
            
         //   if (token != null) {
            //    if(token.equalsIgnoreCase(Messages.token_name)){
          System.out.println(buuid+"===========================");
                    Registrant registrant = registrarservice.buildRegistrant(regAdmin);
                    Optional<Branch> bt= bservice.findByUuid(buuid);
                    Branch b = bt.get();
                    Branchuser buser=new Branchuser();
                    buser.setFirstname(registrant.getFirstname());
                    buser.setLastname(registrant.getLastname());
                    buser.setGender(registrant.getGender());
                    buser.setRole(regAdmin.getRole());
                    buser.setUsername(regAdmin.getUsername());
                    buser.setPassword(regAdmin.getPassword());
                    buser.setEmail(regAdmin.getEmail());
                    buser.setIdnumber(regAdmin.getIdnumber());
                    buser.setPhonenumber(regAdmin.getPhonenumber());
                    buser.setDob(regAdmin.getDateofbirth());
                    buser.setCategory(regAdmin.getCategory());
                    buser.setBranch(b);
                    
                    userservice.createbranchuser(buser);
                    
                    
                   
                   
                    *//**
                     * Checking if the Registrant with same Username exist
                     *//*
                   
                    Optional<Registrar> optional = Optional
                         .ofNullable(registrarservice.findByid(registrant.getId()));
                    if (!optional.isPresent()) {
                        String doneBy = request.getHeader("doneBy");
                        registrarservice.createRegistrar(registrant);
                        responseBean.setCode(Messages.SUCCESS_CODE);
                        responseBean.setObject(registrant);
                        responseBean.setDescription("saved successfuly");
                        
                    
                        SystemUser user = new SystemUser();
                       user.setUsername(regAdmin.getUsername());
                       user.setFirstname(regAdmin.getFirstname());
                       user.setLastname(regAdmin.getLastname());
                       user.setEmail(regAdmin.getEmail());
                       user.setGender(regAdmin.getGender());
                       user.setRole(regAdmin.getRole());
                       user.setObjectName("registrant");
                       user.setPassword(regAdmin.getPassword());
                       user.setPhonenumber(regAdmin.getPhonenumber());
                       user.setApplicationName(regAdmin.getApplicationName());
                       user.setObjectId(registrant.getId() + "");
                       user.setDateofbirth(regAdmin.getDateofbirth());
                        user.setCategory(regAdmin.getCategory());
                        System.out.println(regAdmin.getBranchuuid());
                        
                       
    
                        final String uri = Messages.myum_url + "/users/save";
                        RestTemplate restTemplate = new RestTemplate();
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.set("doneby", doneBy);
                        headers.set("myum_token", "MYUM" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
                        HttpEntity<SystemUser> entity = new HttpEntity<SystemUser>(user, headers);
                        ResponseBean response = restTemplate.postForObject(uri, entity, ResponseBean.class);
    
                    } else {
                        responseBean.setDescription("REGISTRANT with IDENTITY: " + registrant.getId() + " already exist");
                    }
                    
                }else{
                    responseBean.setCode(Messages.INCORRECT_TOKEN);
                    responseBean.setDescription("Incorrect token ");
                    responseBean.setObject(null);
                }
               
            } else {
                responseBean.setCode(Messages.TOKEN_NOT_FOUND);
                responseBean.setDescription(" TOKEN NOT FOUND ");
                responseBean.setObject(null);
            }
            
                        
                    
                        
        } catch (Exception ex) {
            // Todo: correct the error
            System.out.println("RegistrarController (createWithAdmin) " + ex.getMessage());
            responseBean.setCode(Messages.ERROR_CODE);
            responseBean.setDescription(Messages.error+ex.getMessage());
            responseBean.setObject(null);
        }
        return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
    }
	*/
	
	
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value= "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findOne(HttpServletRequest request, @PathVariable int id) {

		ResponseBean rb = new ResponseBean();
		try {

			
				
					Optional<Registrant> reg = registrarservice.findByid(id);
					Registrant registrar = reg.get();
					if (registrar == null) {
						rb.setCode(Messages.NULLS_FOUND);
						rb.setDescription("The season was not found");
					} else {
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription("The registrar is successfuly found");
						rb.setObject(registrar);
					}

		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("error occured please try again!!");
		}

		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	
}

/*@CrossOrigin
@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
public ResponseEntity<Object> delete(HttpServletRequest request, @PathVariable String uuid, @PathVariable int id) {
        
		ResponseBean rb = new ResponseBean();
		try {

					Optional<Registrant> reg= registrarservice.findByid(id);
                    Registrant registrar = reg.get();
					if (registrar == null) {
						rb.setCode(Messages.NOT_FOUND);
						rb.setDescription("The Season you want  Delete does not exit");
					} else {

						registrarservice.deleteRegistrar(id);
						rb.setCode(Messages.SUCCESS_CODE);
						rb.setDescription("registrar Deleted ");
						rb.setObject(null);
					}

				
		} catch (Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("registrar not well Deleted");
		}
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
}
*/
@CrossOrigin
@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Object> viewAllSeason() {
	ResponseBean rb = new ResponseBean();
	try {


				List<Registrant> list = registrarservice.findAll();
				rb.setCode(Messages.SUCCESS_CODE);
				rb.setDescription("The registrar are  retrieved");
				rb.setObject(list);


	} catch (Exception e) {
		rb.setCode(Messages.ERROR_CODE);
		rb.setDescription("error occured while retrieving registrar");
	}

	return new ResponseEntity<Object>(rb, HttpStatus.OK);
}

public static class RegAdmin {
	 private String Names;
     private String username;
     private String role;
     private String applicationName;
     private String objectName;
     private String objectId;
     private String nationalId;
     private String password;
	 private String branchuuid;
	 private int districtid;
	 private int provinceid;
     
    public String getBranchuuid() {
		return branchuuid;
	}
	public void setBranchuuid(String branchuuid) {
		this.branchuuid = branchuuid;
	}
	private String firstname;
 	private String lastname;
 	@Enumerated(EnumType.STRING)
 	private Gender gender;
 	private Date dateofbirth;
 	private String phonenumber;
 	private String email;
 	private String idnumber;
 	
 	private String category;
 	
	public String getNames() {
		return Names;
	}
	public void setNames(String names) {
		Names = names;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getNationalId() {
		return nationalId;
	}
	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getDistrictid() {
		return districtid;
	}
	public void setDistrictid(int districtid) {
		this.districtid = districtid;
	}
	public int getProvinceid() {
		return provinceid;
	}
	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}
	
	
	
}



}
