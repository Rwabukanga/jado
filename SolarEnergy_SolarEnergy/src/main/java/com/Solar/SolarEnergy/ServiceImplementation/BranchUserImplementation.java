package com.Solar.SolarEnergy.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Solar.SolarEnergy.Dao.BranchUserDao;
import com.Solar.SolarEnergy.Domain.Branchuser;
import com.Solar.SolarEnergy.Domain.Category;
import com.Solar.SolarEnergy.Service.BranchUserService;

@Service
public class BranchUserImplementation implements BranchUserService {

	@Autowired
	private BranchUserDao branchuserdao;
	
	@Override
	public void createBranchUser(Branchuser branch) {
		
		branchuserdao.createBranchUSERR(branch);
	}

	@Override
	public void updateBranchUser(Branchuser branch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBranchUser(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Branchuser findByid(String uuid) {
		// TODO Auto-generated method stub
		return branchuserdao.findByUuid(uuid);
	}

	@Override
	public List<Branchuser> findAll() {
		
		return branchuserdao.findAll(Branchuser.class);
	}

	@Override
	public Branchuser findByusername(String username) {
		
		return branchuserdao.findByusername(username);
	}
	
	@Override
	public Category checkBranchUserCategory(String registrant){
		for (Category re : Category.values()) {
			if (re.name().equalsIgnoreCase(registrant))
				return re;
		}
		return null;
	}
	
}
