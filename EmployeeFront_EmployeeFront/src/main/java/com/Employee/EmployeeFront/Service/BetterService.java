package com.Employee.EmployeeFront.Service;





import java.util.Optional;

import com.Employee.EmployeeFront.Domain.Better;

public interface BetterService {

	public Better create(Better bt);
	public Optional<Better> findById(int id);
	
}
