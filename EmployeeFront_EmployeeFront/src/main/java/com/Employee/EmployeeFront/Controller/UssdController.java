package com.Employee.EmployeeFront.Controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Employee.EmployeeFront.Service.UssdService;

@RequestMapping("/ussd")
@Controller
public class UssdController {

	/*@Autowired
	private UssdService ussdservice;*/
	
	@CrossOrigin
	@RequestMapping(value="/add" ,method= RequestMethod.GET, produces= MediaType.TEXT_PLAIN_VALUE)
	public void createussd(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
	/*	Ussd ussd = new Ussd();
		ussd.setMsisdn(request.getParameter("msisdn"));
		ussd.setSessionid(request.getParameter("sessionid"));
		ussd.setInput(request.getParameter("input"));
		ussd.setNewreq(request.getParameter("newreq"));*/
		
		/* Declaring the Parameters */
		
		String msisdn = request.getParameter("msisdn");
		String sessionid = request.getParameter("sessionid");
		String newreq = request.getParameter("newreq");
		String input = request.getParameter("input");

		
		/*String Msg = "My phonenumber is "  + ussd.getMsisdn() + " mySessionid " + ussd.getSessionid() + " it's new request with value = "  + ussd.getNewreq()+ ". My short code is " +ussd.getInput();*/
		String Msg = null;
		
		/*Setting FreeFlow (FC), (FB) */
		
		String FreeFlow = "FB";
		String Lang = "";
		String Action = "0";
		String[] result = GetMenu(msisdn, sessionid, newreq, input);
		if (!result.equals(null)) {
			
			 FreeFlow = result[0];          
			  Msg = result[1];
			Action = result[2];
			
		}
		/*System.out.println("Data" + result[0] + "|" + result[1] + "|" + result[2]);*/
		response.setHeader("FreeFlow", FreeFlow);
		PrintWriter out = response.getWriter();
		out.println(Msg);
		
	}
	
	/* Get Menu */
public static  String[] GetMenu(String msisdn, String sessionid, String newreq, String input) {
		
	String[] result = null;
	result = new String[3];
    

	  try {
		  
		  Class.forName("com.mysql.jdbc.Driver");
	  
	  } catch (ClassNotFoundException ex) {
	  
	  ex.printStackTrace();
	  
	
	  
	  System.out.println(ex.getMessage());
	  
	  }
	  
	  Connection conn = null;
	  
	  
	  
	  try {
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sepro", "root", "jado");

			if (conn != null) {

				System.out.println("You are successfully connected to Mysql server.");
				
			} else {
				System.out.println("Failed to connect");
			}
			Statement st = conn.createStatement();

			String Lang = GetLang(msisdn);
			String updatequery = "";
			
			String updateuser = "";
			
			/*String level = GetLevel(msisdn, sessionid);*/
			
			String getmenuquery = null;
			
			/* New Request*/
			
			if (newreq.equals("1")) {
				/* updateuser = "Insert into usertable (msisdn,lang)" + "VALUES('" + msisdn+
							 "','" + Lang +"');";*/
				updatequery = "Insert into transactiontable (msisdn, sessionid,input,level,lang)" + "VALUES('" + msisdn
						+ "','" + sessionid +"','"+input+"', '1', '" + Lang +"');";
		     	getmenuquery = "Select free_flow, msg, action from menutable where level='1' and lang = '" + Lang
						+ "';";
		     	/* Request Continue */
		     	
			} else if (newreq.equals("0")) {
				
				String level = GetLevel(msisdn, sessionid);
				
				if(level.equals("1") && input.matches("[1-4]+")) {
					
					level = level + "#" + input;
		
					/*	updateuser = "UPDATE usertable SET lang = '"+Lang+"' where msisdn = '"+msisdn+"';";*/
						updatequery =  "UPDATE transactiontable SET input = '"+input+"', lang = '"+Lang+"', level = '"+level+"' WHERE msisdn = '"+msisdn+"' AND sessionid = '"+sessionid+"';";
						getmenuquery = "Select free_flow, msg, action from menutable where level = '"+level+"' and lang = '"
								+ Lang + "';";
					
						System.out.println("OK");
						
				/*		st.executeUpdate(updatequery);*/
						
					 /*else {
						level = level + "#" + input;
						updatequery =    "UPDATE SET input = '"+input+"', lang = '"+Lang+"', level = '"+level+"' WHERE msisdn = '"+msisdn+"' AND sessionid = '"+sessionid+"';";
						getmenuquery = "Select free_flow, msg, action from menutable where level = '"+level+"' and lang = '"
								+ Lang + "';";
					 }*/
					
				
				}
				
             else if (level.equals("1#2") && input.matches("[1-2]+")) {

					
					level = level + "#" + input;
					
					updatequery =  "UPDATE transactiontable SET input = '"+input+"', lang = '"+Lang+"', level ='"+level+"' WHERE msisdn = '"+msisdn+"' AND sessionid = '"+sessionid+"';";
					getmenuquery = "Select free_flow, msg, action from menutable where level =  '"+level+"' and lang = '" + Lang
							+ "';";
					
					/*if (level.equals("1#1") && input.matches("2")) {
						level = level + "#" + input;
						updatequery = "UPDATE transactiontable SET input = '"+input+"', lang = '"+Lang+"', level ='"+level+"' WHERE msisdn = '"+msisdn+"' AND sessionid = '"+sessionid+"';";
						getmenuquery = "Select free_flow, msg, action from menutable where level =  '"+level+"' and lang = '" + Lang
								+ "';";
					}*/ /*else {
						level = level + "#" + input;
						updatequery = "UPDATE transactiontable SET input = '"+input+"', lang = '"+Lang+"', level ='"+level+"' WHERE msisdn = '"+msisdn+"' AND sessionid = '"+sessionid+"';";
						getmenuquery = "Select free_flow, msg, action from menutable where level =  '"+level+"' and lang = '" + Lang
								+ "';";
					}*/
					
					
					
				}
				
			
				else if (level.equals("1#1") && input.matches("[1-2]+")) {

					
					level = level + "#" + input;
					
					updatequery =  "UPDATE transactiontable SET input = '"+input+"', lang = '"+Lang+"', level ='"+level+"' WHERE msisdn = '"+msisdn+"' AND sessionid = '"+sessionid+"';";
					getmenuquery = "Select free_flow, msg, action from menutable where level =  '"+level+"' and lang = '" + Lang
							+ "';";
					
					/*if (level.equals("1#1") && input.matches("2")) {
						level = level + "#" + input;
						updatequery = "UPDATE transactiontable SET input = '"+input+"', lang = '"+Lang+"', level ='"+level+"' WHERE msisdn = '"+msisdn+"' AND sessionid = '"+sessionid+"';";
						getmenuquery = "Select free_flow, msg, action from menutable where level =  '"+level+"' and lang = '" + Lang
								+ "';";
					}*/ /*else {
						level = level + "#" + input;
						updatequery = "UPDATE transactiontable SET input = '"+input+"', lang = '"+Lang+"', level ='"+level+"' WHERE msisdn = '"+msisdn+"' AND sessionid = '"+sessionid+"';";
						getmenuquery = "Select free_flow, msg, action from menutable where level =  '"+level+"' and lang = '" + Lang
								+ "';";
					}*/
					
					
					
				}
				
				
				/*else {
				level = level + "#" + input;
				updatequery =    "UPDATE SET input = '"+input+"', lang = '"+Lang+"', level = '"+level+"' WHERE msisdn = '"+msisdn+"' AND sessionid = '"+sessionid+"';";
				getmenuquery = "Select free_flow, msg, action from menutable where level = '"+level+"' and lang = '"
						+ Lang + "';";
			 }*/
				
				else if (level.equals("1#1#1") && input.matches("[1]+")) {
					
					level = level + "#" + input;
					
					updatequery =  "UPDATE transactiontable SET input = '"+input+"', lang = '"+Lang+"', level = '"+level+"' WHERE msisdn = '"+msisdn+"' AND sessionid = '"+sessionid+"';";
					getmenuquery = "Select free_flow, msg, action from menutable where level = '"+level+"' and lang = '" + Lang
							+ "';";
					
					
				}else if (level.equals("1#2#1") && input.matches("[1]+")) {

					
					level = level + "#" + input;
					
					updatequery =  "UPDATE transactiontable SET input = '"+input+"', lang = '"+Lang+"', level ='"+level+"' WHERE msisdn = '"+msisdn+"' AND sessionid = '"+sessionid+"';";
					getmenuquery = "Select free_flow, msg, action from menutable where level =  '"+level+"' and lang = '" + Lang
							+ "';";
					
					/*if (level.equals("1#1") && input.matches("2")) {
						level = level + "#" + input;
						updatequery = "UPDATE transactiontable SET input = '"+input+"', lang = '"+Lang+"', level ='"+level+"' WHERE msisdn = '"+msisdn+"' AND sessionid = '"+sessionid+"';";
						getmenuquery = "Select free_flow, msg, action from menutable where level =  '"+level+"' and lang = '" + Lang
								+ "';";
					}*/ /*else {
						level = level + "#" + input;
						updatequery = "UPDATE transactiontable SET input = '"+input+"', lang = '"+Lang+"', level ='"+level+"' WHERE msisdn = '"+msisdn+"' AND sessionid = '"+sessionid+"';";
						getmenuquery = "Select free_flow, msg, action from menutable where level =  '"+level+"' and lang = '" + Lang
								+ "';";
					}*/
					
					
					
				}
				

				else if (input.matches("[1-3]+") && level.equals("1#1#1#1")) {

					level = level + "#" + input;
					updatequery =  "UPDATE transactiontable SET input = '"+input+"', lang = '"+Lang+"', level = '"+level+"' WHERE msisdn = '"+msisdn+"' AND sessionid = '"+sessionid+"';";
					getmenuquery = "Select free_flow, msg, action from menutable where level = '"+level+"' and lang = '" + Lang
							+ "';";
				
				}else if (level.equals("1#2#1#1") && input.matches("[1-3]+")) {

					
					level = level + "#" + input;
					
					updatequery =  "UPDATE transactiontable SET input = '"+input+"', lang = '"+Lang+"', level ='"+level+"' WHERE msisdn = '"+msisdn+"' AND sessionid = '"+sessionid+"';";
					getmenuquery = "Select free_flow, msg, action from menutable where level =  '"+level+"' and lang = '" + Lang
							+ "';";
					
					
				}

				else if (level.equals("1#1#1#1#1") && input.matches("[1]+")) {
					
					level = level + "#" + input;
					updatequery =  "UPDATE transactiontable SET input = '"+input+"', lang = '"+Lang+"', level = '"+level+"' WHERE msisdn = '"+msisdn+"' AND sessionid = '"+sessionid+"';";
					getmenuquery = "Select free_flow, msg, action from menutable where level = '"+level+"' and lang = '" + Lang
							+ "';";
				}else if (level.equals("1#2#1#1#1") && input.matches("[1]+")) {

					
					level = level + "#" + input;
					
					updatequery =  "UPDATE transactiontable SET input = '"+input+"', lang = '"+Lang+"', level ='"+level+"' WHERE msisdn = '"+msisdn+"' AND sessionid = '"+sessionid+"';";
					getmenuquery = "Select free_flow, msg, action from menutable where level =  '"+level+"' and lang = '" + Lang
							+ "';";
					
					
				}
		
				else if (level.equals("1#1#1#1#1#1") && input.matches("[1]+")) {
					level = level + "#" + input;
					/*System.out.println("Enter the amount:" +input);*/
					updatequery =  "UPDATE transactiontable SET input = '"+input+"', lang = '"+Lang+"', level = '"+level+"' WHERE msisdn = '"+msisdn+"' AND sessionid = '"+sessionid+"';";
					getmenuquery = "Select free_flow, msg, action from menutable where level = '"+level+"' and lang = '" + Lang
							+ "';";
				} else if (level.equals("1#2#1#1#1#1") && input.matches("[1]+")) {

					
					level = level + "#" + input;
					
					updatequery =  "UPDATE transactiontable SET input = '"+input+"', lang = '"+Lang+"', level ='"+level+"' WHERE msisdn = '"+msisdn+"' AND sessionid = '"+sessionid+"';";
					getmenuquery = "Select free_flow, msg, action from menutable where level =  '"+level+"' and lang = '" + Lang
							+ "';";
					
					
				}
				
				
				else {
 					getmenuquery = "SELECT CONCAT('Wrong input error', char(10 using utf8), msg), free_flow, action from menutable where level = '"
							+ level + "' AND lang = '" + Lang + "';";
				}
			}
			if (!updatequery.equals("")) {
				st.executeUpdate(updatequery);

			}
			ResultSet rs = st.executeQuery(getmenuquery);

			if (rs.next()) {
				result[0] = rs.getString(1);
				result[1] = rs.getString(2);
				result[2] = rs.getString(3);
				    

				System.out.println("Data" + result[0] + "|" + result[1] + "|" + result[2]);
			}

			else {
				result[0] = "FB";
				result[1] = "No Output";
				result[2] = "0";
			}

			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();

			System.out.println(ex.getMessage());

		}
		return result;
	
	}

/* Get Level */
public static String GetLevel(String msisdn, String sessionid) {
	
	String result = "0";

	
	  try { 
		  
		  Class.forName("com.mysql.jdbc.Driver");
	  
	  } catch (ClassNotFoundException ex) {
	  
	  ex.printStackTrace();
	  
	  }
	 
	try {
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sepro", "root", "jado");

		System.out.println("You are successfully connected to Mysql.");

		Statement st = conn.createStatement();

		String query = "Select level from transactiontable where msisdn='" + msisdn + "' AND sessionid='"
				+ sessionid + "';";
		ResultSet rs = st.executeQuery(query);

		if (rs.next()) {
			result = rs.getString(1);

		}

	} catch (Exception ex) {
		ex.printStackTrace();

		System.out.println("error" + ex.getMessage());

	}

	return result;

}

/* Get Languages */
public  static String GetLang(String msisdn) {
	
	String result = "ENG";

	
	  try { 
		  
		  Class.forName("com.mysql.jdbc.Driver");
		  
	  } catch (ClassNotFoundException ex) {
	  
	  ex.printStackTrace(); 
	  
	  }
	 

	try {

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sepro", "root", "jado");

		System.out.println("You are successfully connected to Mysql server.");

		Statement st = conn.createStatement();

		String query = "SELECT lang from usertable where msisdn = " + msisdn;

		ResultSet rs = st.executeQuery(query);

		if (rs.next()) {
			result = rs.getString(1);

		}

		System.out.println("language:" + msisdn + "_" + result);
		conn.close();

	} catch (Exception ex) {
		ex.printStackTrace();
	}
	return result;
}


	
}



