package com.Employee.EmployeeFront.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employee.EmployeeFront.Dao.UssdRepository;
import com.Employee.EmployeeFront.Domain.Ussd;
import com.Employee.EmployeeFront.Service.UssdService;

@Service
public class UssdImplementation implements UssdService {

	@Autowired
	private UssdRepository ussdrepo;
	
	
	@Override
	public void createUssd(Ussd ussd) {
		ussdrepo.save(ussd);
		
	}

	@Override
	public List<Ussd> findAll(Class c) {
		// TODO Auto-generated method stub
		return ussdrepo.findAll();
	}

	
}
