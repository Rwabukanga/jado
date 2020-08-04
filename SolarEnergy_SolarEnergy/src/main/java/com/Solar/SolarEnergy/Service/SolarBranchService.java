package com.Solar.SolarEnergy.Service;

import java.util.List;

import com.Solar.SolarEnergy.Domain.Branch;
import com.Solar.SolarEnergy.Domain.SolarBranch;

public interface SolarBranchService {

	public void createSolarBranch(SolarBranch solar);
	public void updateSolarBranch(SolarBranch solar);
	public void deleteSolarBranch(int id);
	public SolarBranch findByid(int id);
	public List<SolarBranch> findAll();
	
	
}
