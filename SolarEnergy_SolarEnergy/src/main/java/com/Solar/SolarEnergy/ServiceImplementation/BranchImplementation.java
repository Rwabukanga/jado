package com.Solar.SolarEnergy.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Solar.SolarEnergy.Dao.BranchDao;
import com.Solar.SolarEnergy.Dao.BranchRepository;
import com.Solar.SolarEnergy.Domain.Branch;
import com.Solar.SolarEnergy.Service.BranchService;

@Service
public class BranchImplementation implements BranchService {
	
	@Autowired
	private BranchRepository branchrepository;
	
	@Autowired
	private BranchDao branchdao;
	
	@Override
	public void createBranch(Branch branch) {
		
		branchdao.createRegistrar(branch);
	}
	
	@Override
	public void updateBranch(Branch branch) {
		
		branchdao.Update(branch);
	}
	
	@Override
	public void deleteBranch(int id) {
		branchrepository.delete(id);
	}
	
	@Override
	public List<Branch> findAll(){
		
		return branchrepository.findAll();
	}

	@Override
	public Branch findByid(int id) {
		
		return branchdao.findByid(id);
	}
	
	 @Override
	 public Branch findByUuid(String uuid) {
		 
		 return branchdao.findByUuid(uuid);
	 }

}
