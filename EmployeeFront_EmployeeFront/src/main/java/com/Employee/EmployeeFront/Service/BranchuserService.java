package com.Employee.EmployeeFront.Service;

import java.util.List;
import java.util.Optional;

import com.Employee.EmployeeFront.Domain.Branchuser;

public interface BranchuserService {

	public void createbranchuser(Branchuser branchuser);
	public void updatebranchuser(Branchuser branchuser);
	public void delete(Branchuser branch);
	public Optional<Branchuser> findById(int id);
	public Optional<Branchuser> findByUuid(String uuid);
	public List<Branchuser> findAll(Class<Branchuser> c);
}
