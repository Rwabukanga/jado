package com.Employee.EmployeeFront.Controller;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Employee.EmployeeFront.Dao.BkRepository;
import com.Employee.EmployeeFront.Domain.BK;
import com.Employee.EmployeeFront.Domain.Better;
import com.Employee.EmployeeFront.Service.BetterService;
import com.Employee.EmployeeFront.Service.BkService;
import com.Employee.EmployeeFront.Utility.Messages;
import com.Employee.EmployeeFront.Utility.ResponseBean;

@RestController
@CrossOrigin
@RequestMapping("/bbt")
public class BetterController {

	@Autowired
	private BetterService btservice;
	
	@Autowired
	private BkRepository brepo;
	
	@Autowired
	private BkService bsservice;
	
	@CrossOrigin
	@PostMapping(path="/save",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(HttpServletRequest request, @RequestBody Better bt){
		
		ResponseBean rb = new ResponseBean();
		
		try {
			String doneBy = request.getHeader("doneBy");
			btservice.create(bt);
			rb.setCode(Messages.SUCCESS_CODE);
			rb.setDescription("save successfully");
			
			
			BK bk= new BK();
			
			bk.setBt(bt);
			bk.setFirstname(bt.getFirstname());
			bk.setLastname(bt.getLastname());
			bk.setUsername(bt.getUsername());
			bk.setPassword(bt.getPassword());
			bsservice.create(bk);
			
			final String uri = Messages.myum_url + "/bbt/save";
			RestTemplate restt = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.set("doneBy", doneBy);
			HttpEntity<BK> en = new HttpEntity<BK>(bk, headers);
			ResponseBean res= restt.postForObject(uri,en, ResponseBean.class);
			 
			
		}catch(Exception e) {
			rb.setCode(Messages.ERROR_CODE);
			rb.setDescription("Failure to save");
		}
		return new ResponseEntity<Object>(rb, HttpStatus.OK);
	}
}
