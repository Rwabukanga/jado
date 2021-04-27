/*package com.Employee.EmployeeFront.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Employee.EmployeeFront.Domain.transactiontable;
import com.Employee.EmployeeFront.Domain.menutable;
import com.Employee.EmployeeFront.Service.MenuService;
import com.Employee.EmployeeFront.Service.TransactionService;

@CrossOrigin
@Controller
@RequestMapping(value="/ussd")
public class TransactionController {
	
	@Autowired
	private  TransactionService transactionservice;
	
	@Autowired
	private  MenuService menuservice;

	@CrossOrigin
	@RequestMapping(value="/add/{id}", method=RequestMethod.GET, consumes=MediaType.TEXT_PLAIN_VALUE)
	public void createtransaction(HttpServletRequest request, HttpServletResponse response,@PathVariable int id) throws ParseException, IOException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		transactiontable trs = new transactiontable();
		Optional<menutable> mt = menuservice.FinById(id);
		menutable mn = mt.get();
		trs.setSessionid(request.getParameter("sessionid"));
		trs.setInput(request.getParameter("input"));
		trs.setLang(request.getParameter("lang"));
		trs.setMsisdn(request.getParameter("msisdn"));
	    trs.setDate_modified(sdf.parse(request.getParameter("date_modified")));
		trs.setReqdate(sdf.parse(request.getParameter("reqdate")));
		
		String FreeFlow = "FC";
		
		response.setHeader("FreeFlow", FreeFlow);
		trs.setNewrquest(request.getParameter("newreq"));
		trs.setDate_modified(request.getParameter("date"));
		trs.setAction(request.getParameter("action"));
		trs.setLevel(request.getParameter("level"));
		trs.setReqdate(request.getParameter("madedate"));
		trs.setMenu(mn);
		transactionservice.createtransaction(trs);
		
		String Msg = "My phonenumber is "  + trs.getMsisdn() + " mySessionid " + trs.getSessionid() + " it's new request with value = "  + trs.getLang()+ ". My short code is " +trs.getInput();

		PrintWriter out = response.getWriter();
		out.println(Msg);
		
	
		
	}
}
*/