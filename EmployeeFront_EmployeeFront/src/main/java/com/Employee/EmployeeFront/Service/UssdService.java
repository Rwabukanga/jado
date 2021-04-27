package com.Employee.EmployeeFront.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Employee.EmployeeFront.Domain.Ussd;

@Service
public interface UssdService {

	
	public void createUssd(Ussd  ussd);
	public List<Ussd> findAll(Class c);
}
