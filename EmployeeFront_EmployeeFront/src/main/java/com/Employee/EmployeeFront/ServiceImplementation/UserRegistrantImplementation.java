package com.Employee.EmployeeFront.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employee.EmployeeFront.Dao.UserRegistrantRepository;
import com.Employee.EmployeeFront.Domain.UserRegistrant;
import com.Employee.EmployeeFront.Service.UserRegistrantService;

@Service
public class UserRegistrantImplementation implements UserRegistrantService {

	@Autowired
	private UserRegistrantRepository  userregrepo;

	@Override
	public void createuserreg(UserRegistrant reg) {
		
		userregrepo.save(reg);
		
	}

	@Override
	public List<UserRegistrant> findall(Class c) {

		return userregrepo.findAll();
	}
	
	
	
}
