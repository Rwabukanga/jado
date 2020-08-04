package com.Employee.EmployeeFront.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employee.EmployeeFront.Dao.ProvinceRepository;
import com.Employee.EmployeeFront.Domain.Province;
import com.Employee.EmployeeFront.Service.ProvinceService;

@Service
public class ProvinceImplementation implements ProvinceService {

	@Autowired
	private ProvinceRepository provincerepo;
	
	@Override
	public void create(Province province) {
		provincerepo.save(province);
		
	}

	@Override
	public void update(Province province) {
	
		provincerepo.save(province);
		
	}

	@Override
	public void delete(Province province) {
		
		provincerepo.delete(province);
		
	}

	@Override
	public Optional<Province> findById(int id) {
		
		return provincerepo.findById(id);
	}
	@Override
	public List<Province> findall(Class c) {
		
		return provincerepo.findAll();
	}

}
