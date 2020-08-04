package com.Solar.SolarEnergy.Service;

import java.util.List;


import com.Solar.SolarEnergy.Domain.Branchuser;
import com.Solar.SolarEnergy.Domain.Category;

public interface BranchUserService {
	
	public void createBranchUser(Branchuser branch);
	public void updateBranchUser(Branchuser branch);
	public void deleteBranchUser(int id);
	public Branchuser findByid(String uuid);
	public List<Branchuser> findAll();
	public Branchuser findByusername(String username);
	public Category checkBranchUserCategory(String registrant);
}
