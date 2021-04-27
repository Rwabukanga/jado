package com.Employee.EmployeeFront.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employee.EmployeeFront.Dao.menurepo;
import com.Employee.EmployeeFront.Domain.menutable;
import com.Employee.EmployeeFront.Service.MenuService;

@Service
public class MenuImplementation implements MenuService {

	@Autowired
	private menurepo mrepo;

	@Override
	public void createmenu(menutable menu) {
	
		mrepo.save(menu);
		
	}

	@Override
	public List<menutable> findall(Class c) {
		// TODO Auto-generated method stub
		return mrepo.findAll();
	}

	@Override
	public Optional<menutable> FinById(int id) {
		// TODO Auto-generated method stub
		return mrepo.findById(id);
	}
	
	
}
