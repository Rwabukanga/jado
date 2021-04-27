package com.Employee.EmployeeFront.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Employee.EmployeeFront.Service.UssdService;
import com.Employee.EmployeeFront.Utility.Msg;
import com.Employee.EmployeeFront.Utility.ResponseBean;

@CrossOrigin
@Controller
@RequestMapping(value="/menu")
public class menuController {
	
	@Autowired
	private UssdService ussdservice;
	
	
	@CrossOrigin
	@RequestMapping(value="/add" ,method= RequestMethod.GET, produces= MediaType.TEXT_PLAIN_VALUE)
	public void createussd(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		/*Ussd ussd = new Ussd();
		ussd.setMsisdn(request.getParameter("msisdn"));
		ussd.setSessionid(request.getParameter("sessionid"));
		ussd.setInput(request.getParameter("input"));
		ussd.setNewreq(request.getParameter("newreq"));
		ussdservice.createUssd(ussd);*/
		
		String msisdn = request.getParameter("msisdn");
		String sessionid = request.getParameter("sessionid");
		String input = request.getParameter("input");
		String newreq = request.getParameter("newreq");
		/*ussdservice.findAll(Ussd.class);*/
		/*String Msg = "My phonenumber is "  + ussd.getMsisdn() + " mySessionid " + ussd.getSessionid() + " it's new request with value = "  + ussd.getNewreq()+ ". My short code is " +ussd.getInput();*/
		
		
		
		String Msg = null;
		
		String FreeFlow = "FB";
		
		String Action= "0";
		String[] result = GetMenu(msisdn,sessionid,input,newreq);
		
		if(!result.equals(null))
		{
			Msg = result[0];
			FreeFlow = result[1];
			Action = result[2];
		}
		
		if(Action.equals("1"))
		{
			
		}
		response.setHeader("FreeFlow", FreeFlow);
		PrintWriter out = response.getWriter();
		out.println(Msg);
	  
		
	}
	
	public static String[] GetMenu(String msisdn, String sessionid, String input, String newreq) {
	
		String[] result = null;
		result = new String[3];
		try {
			Connection con = DriverManager.getConnection("connectionUrl");
			System.out.println("successfully connected");
			Statement st = con.createStatement();
			
		String Lang = GetLang(msisdn);
		String updatequery = "";
		String getmenuquery = null;
		
		if(newreq.equals("1")) {
			
			
			updatequery = "insert into transactiontable(msisdn, sessionid, input, level, lang)" + "Values('"+msisdn+"', '"+sessionid+"','"+input+"','1','"+Lang+"');";
			getmenuquery = "Select msg, freeflow, action from menutable where level = '1' AND lang = '"+Lang+"';";
		}
		else if(newreq.equals("0"))
		{
			String level = GetLevel(msisdn,sessionid);
			if(level.equals("1") && input.matches("[1-3]+"))
			{
				if(input.equals("3"))
				{
					Lang = (Lang.equals("ENG"))? "FRA" : "ENG";
					String updateuser = "update user_registrant set lang = '"+Lang+"' WHERE msisdn= '"+msisdn+"';";
					updatequery = "UPDATE transactiontable Set input = '"+input+"', Lang = '"+Lang+"', level = '1' WHERE MSISDN = '"+msisdn+"' AND SESSIONID = '"+sessionid+"';";
					getmenuquery = "SELECT msg,freeflow, action from menutable where level = '1' AND Lang = '"+Lang+"';";
                    st.executeUpdate(updateuser);
                    
				}
				else {
					level = level + "#" + input;
					updatequery = "UPDATE transactiontable SET Input = '"+input+"', Lang = '"+Lang+"'";
					
				}
			}
			else if(level.equals("1#1") && input.matches("[1-2]+"))
			{
				level = level + "#" + input;
				updatequery = "UPDATE transactiontable SET input = '"+input+"', Lang = '"+Lang+"', LEVEL= '"+level+"' WHERE MSISDN = '"+msisdn+"' AND SESSIONID = '"+sessionid+"';";
				getmenuquery = "SELECT msg,freeflow, action from menutable where level = '1' AND Lang = '"+Lang+"';";
			}
			
			else {
				getmenuquery = "SELECT CONCAT ('Wrong Input /Entree Incorrect', CHAR(10 using utf 8), Msg), FreeFlow, ACTION FROM menutable WHERE level = '"+level+"';";
				
			}
			
		}
		if(!updatequery.equals("")) {
			st.executeUpdate(updatequery);
		}
		
		ResultSet rs = st.executeQuery(getmenuquery);
		
		if(rs.next()) {
			result[0] = rs.getString(1);
			result[1] = rs.getString(2);
			result[2] = rs.getString(3);
			
			System.out.println("GET MENU PARAMETRS" +result[0]+"|" +result + "|"+ result[2]);
		}
		else {
			
			result[0] = "No output";
			result[1] = "FB";
			result[2] = "0";
		}
		con.close();
		
		}catch(Exception e) {
			
		}
		return result;
	}

	public static String GetLevel(String msisdn, String sessionid) {
	
		String result = "English";
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Connection con = DriverManager.getConnection("connectionUrl");
			Statement st = con.createStatement();
			
			System.out.println("connected Successfully");
			String query = "Select from Transactiontable where MSISDN = '"+msisdn+"' AND SESSIONID='"+sessionid+"';";
			
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				result = rs.getString(1);
				System.out.println("Level "+msisdn+"" + result);
				con.close();
				
			}
			
		}catch(Exception e) {
			
		}
		return null;
	}

	public  static String GetLang(String msisdn) {
		String result = "ENGLISH";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Connection con = DriverManager.getConnection("connectionUrl");
			Statement st = con.createStatement();
			
			String query = "Select Lang from user_registrant where MSISDN ="+ msisdn;
			
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {
				result = rs.getString(1);
				System.out.println("Language :" +msisdn+"" +result);
				con.close();
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return result;
	}

	/*@CrossOrigin
	@RequestMapping(value="/all", method=RequestMethod.GET, produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Object> findAll(String msisdn, String sessionid, String newreq, String input){
		
		ResponseBean rb = new ResponseBean();
		try {
			String list= "select msg from menutable where input = "+1+"";
			rb.setCode(Msg.SUCCESS_CODE);
			rb.setDescription("Well Received");
			rb.setObject(list);
		}catch(Exception ex) {
			rb.setDescription("Failed to retrieve it");
			rb.setObject("nothing");
		}
		
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
	*/
	
	



}
