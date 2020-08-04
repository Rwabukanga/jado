package com.Solar.SolarEnergy.Service;

import java.util.List;

import com.Solar.SolarEnergy.Domain.Branch;


public interface BranchService {
	
	public void createBranch(Branch branch);
	public void updateBranch(Branch branch);
	public void deleteBranch(int id);
	public Branch findByid(int id);
	public Branch findByUuid(String uuid);
	public List<Branch> findAll();
	

}
