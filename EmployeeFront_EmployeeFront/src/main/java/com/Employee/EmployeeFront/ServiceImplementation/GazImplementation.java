package com.Employee.EmployeeFront.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employee.EmployeeFront.Dao.GazRepository;
import com.Employee.EmployeeFront.Domain.Gaz;
import com.Employee.EmployeeFront.Service.GazService;

@Service
public class GazImplementation implements GazService {

	@Autowired
	private GazRepository gazrepo;
	
	@Override
	public void createGaz(Gaz gaz) {
		
		gazrepo.save(gaz);		
	}
	@Override
	public void updateGaz(Gaz gaz) {
		gazrepo.save(gaz);
		
	}
	@Override
	public void delete(Gaz gaz) {
	
		gazrepo.delete(gaz);		
	}
	@Override
	public Optional<Gaz> findById(int id) {
		
		return gazrepo.findById(id);
	}
	@Override
	public Optional<Gaz> findByUuid(String uuid) {
	
		return gazrepo.findByUuid(uuid);
	}
	@Override
	public List<Gaz> findAll(Class c) {
		
		return gazrepo.findAll();
	}

}
