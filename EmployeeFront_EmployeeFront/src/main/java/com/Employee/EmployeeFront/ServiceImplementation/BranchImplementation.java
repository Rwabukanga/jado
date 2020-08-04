package com.Employee.EmployeeFront.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employee.EmployeeFront.Dao.BranchRepository;
import com.Employee.EmployeeFront.Domain.Branch;
import com.Employee.EmployeeFront.Service.Branchservice;

@Service
public class BranchImplementation implements Branchservice {

	@Autowired
	private BranchRepository branchrepository;
	
	@Override
	public void createbranch(Branch branch) {
	    
		branchrepository.save(branch);
	}

	@Override
	public void updatebranch(Branch branch) {
	
		branchrepository.save(branch);
		
	}
	@Override
	public void delete(Branch branch) {
		
		branchrepository.delete(branch);
		
	}

	@Override
	public Optional<Branch> findById(int id) {
		
		return branchrepository.findById(id);
	}

	@Override
	public Optional<Branch> findByUuid(String uuid) {
		
		return branchrepository.findByUuid(uuid);
	}

	@Override
	public List<Branch> findAll(Class c) {
		
		return branchrepository.findAll();
	}

	

}
