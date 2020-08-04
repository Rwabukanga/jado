package com.Solar.SolarEnergy.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Solar.SolarEnergy.Dao.BranchRepository;
import com.Solar.SolarEnergy.Dao.SolarBranchRepository;
import com.Solar.SolarEnergy.Domain.Branch;
import com.Solar.SolarEnergy.Domain.SolarBranch;
import com.Solar.SolarEnergy.Service.SolarBranchService;

@Service
public class SolarBranchImplementation implements SolarBranchService {

	@Autowired
	private SolarBranchRepository solarbranchrepository;
	
	@Override
	public void createSolarBranch(SolarBranch branch) {
		
		solarbranchrepository.save(branch);
	}
	
	@Override
	public void updateSolarBranch(SolarBranch branch) {
		
		solarbranchrepository.save(branch);
	}
	
	@Override
	public void deleteSolarBranch(int id) {
		solarbranchrepository.delete(id);
	}
	
	@Override
	public SolarBranch findByid(int id) {
		
	return solarbranchrepository.getOne(id);
	}
	
	public List<SolarBranch> findAll(){
		
		return solarbranchrepository.findAll();
	}

}
