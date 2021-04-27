package com.Employee.EmployeeFront.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Employee.EmployeeFront.Domain.UserRegistrant;

@Service
public interface UserRegistrantService {

	public void createuserreg(UserRegistrant reg);
	public List<UserRegistrant> findall(Class c);
	
}

