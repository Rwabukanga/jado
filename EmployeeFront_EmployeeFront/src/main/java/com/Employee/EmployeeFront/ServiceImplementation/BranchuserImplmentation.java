package com.Employee.EmployeeFront.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employee.EmployeeFront.Dao.BracnhuserRepository;
import com.Employee.EmployeeFront.Domain.Branchuser;
import com.Employee.EmployeeFront.Service.BranchuserService;

@Service
public class BranchuserImplmentation implements BranchuserService {

	@Autowired
	private BracnhuserRepository branchuserrepo;
	
	@Override
	public void createbranchuser(Branchuser branchuser) {
		
		branchuserrepo.save(branchuser);
		
	}

	@Override
	public void updatebranchuser(Branchuser branchuser) {
		
		branchuserrepo.save(branchuser);
		
	}	

	@Override
	public void delete(Branchuser branch) {
		
		branchuserrepo.delete(branch);
		
	}

	@Override
	public Optional<Branchuser> findById(int id) {
		
		return branchuserrepo.findById(id);
	}

	@Override
	public Optional<Branchuser> findByUuid(String uuid) {
		
		return branchuserrepo.findByUuid(uuid);
	}

	@Override
	public List<Branchuser> findAll(Class<Branchuser> c) {
		
		return branchuserrepo.findAll();
	}

	/*@Override
	public List<Branchuser> findAll() {
		// TODO Auto-generated method stub
		return branchuserrepo.findAll();
	}
*/
}
