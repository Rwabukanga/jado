package com.Employee.EmployeeFront.Service;

import java.util.List;
import java.util.Optional;

import com.Employee.EmployeeFront.Domain.BK;

public interface BkService {

	public String create(BK bb);
	public List<BK> findall(Class c);
	public Optional<BK> findById(long id);
	
}
