package com.Solarenergy.solar;

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





@RestController
public class SolarController {

	@RequestMapping(value="/hello")
	public String hello() {
		return "hello jado";
	}
	
	/*
	 * Add user to session
	 * */
	@RequestMapping(value="/add_user/session", method=RequestMethod.POST)
	public Object add_user_to_session(HttpSession session, @RequestParam Map<String,String>param){
		try{
		SystemUser user=new SystemUser();
		user.setUsername(param.get("username"));
		
		user.setFname(param.get("fname"));
		user.setLname(param.get("lname"));
		user.setRole(param.get("role"));
		/*user.setId(Integer.parseInt(param.get("id")));
*/
		session.setAttribute("a_user", user); 		
		return "OK";
		}catch (Exception e) {
			return ""+e.getMessage();
		}
		
	}
	
	/*
	 * 
	 * Log out
	 * */
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public Object logout(HttpSession session, @RequestParam Map<String,String>param){
		try{		
		session.removeAttribute("a_user"); 
		session.removeAttribute("a_reg"); 		
		return "OK";
		}catch (Exception e) {
			return ""+e.getMessage();
		}
		
	}
	
	/*
	 * Add registrant to session
	 * */
	@RequestMapping(value="/add_registrant/session", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Object add_registrant_to_session(HttpSession session, @RequestBody Registrant registrant){
		try{
			
		   session.setAttribute("a_reg", registrant);

		}catch (Exception e) {
			//return ""+e.getMessage();
		}
		
		return new ResponseEntity<Object>(registrant,HttpStatus.OK);
		
	}
	
	public static class SystemUser{
		private String username;
		private String fname;
		private String lname;
		private String role;
	/*	private int id;*/
		
		/*public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}*/
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getFname() {
			return fname;
		}
		public void setFname(String fname) {
			this.fname = fname;
		}
		public String getLname() {
			return lname;
		}
		public void setLname(String lname) {
			this.lname = lname;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
	
				
	}
	
	
	public static class Registrant{
		private String firstname;
		
		private String lastname;
		
		private String email;
		
		private String phone;
		private String identity;
		
		
		private String uuid;
		
		
		private String address;
		
		private String sector;
		 
		private String category;

	
		private String gender;
        private String file;
		
	

		/**
		 * @return the startingYear
		 */
		

		

		/**
		 * @return the file
		 */
		public String getFile() {
			return file;
		}

		/**
		 * @param file the file to set
		 */
		public void setFile(String file) {
			this.file = file;
		}

		
		
		
		
		

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
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

		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getIdentity() {
			return identity;
		}
		public void setIdentity(String identity) {
			this.identity = identity;
		}
		
		public String getUuid() {
			return uuid;
		}
		public void setUuid(String uuid) {
			this.uuid = uuid;
		}
		
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getSector() {
			return sector;
		}
		public void setSector(String sector) {
			this.sector = sector;
		}
		
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		
	}

}
