package com.Employee.EmployeeFront.ServiceImplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Employee.EmployeeFront.Dao.BetterRepository;
import com.Employee.EmployeeFront.Domain.Better;
import com.Employee.EmployeeFront.Service.BetterService;

@Service
@Transactional
public class BetterServiceImpl implements BetterService {
	
	@Autowired
	private BetterRepository bbrepo;

	@Override
	public Better create(Better bt) {
		// TODO Auto-generated method stub
		return bbrepo.save(bt);
	}

	@Override
	public Optional<Better> findById(int id) {
		// TODO Auto-generated method stub
		return bbrepo.findById(id);
	}

}
