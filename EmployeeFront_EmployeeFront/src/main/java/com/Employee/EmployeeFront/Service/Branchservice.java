package com.Employee.EmployeeFront.Service;

import java.util.List;
import java.util.Optional;

import com.Employee.EmployeeFront.Domain.Branch;

public interface Branchservice {

	public void createbranch(Branch branch);
	public void updatebranch(Branch branch);
	public void delete(Branch branch);
	public Optional<Branch> findById(int id);
	public Optional<Branch> findByUuid(String uuid);
	public List<Branch> findAll(Class c);
}
