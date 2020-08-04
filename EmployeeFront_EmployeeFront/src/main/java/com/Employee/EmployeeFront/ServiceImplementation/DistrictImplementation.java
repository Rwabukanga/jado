package com.Employee.EmployeeFront.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employee.EmployeeFront.Dao.DistrictRepository;
import com.Employee.EmployeeFront.Dao.ProvinceRepository;
import com.Employee.EmployeeFront.Domain.District;
import com.Employee.EmployeeFront.Domain.Province;
import com.Employee.EmployeeFront.Service.DistrictService;

@Service
public class DistrictImplementation implements DistrictService {

	@Autowired
	private DistrictRepository districtrepo;
	
	@Autowired
	private ProvinceRepository provincerepo;

	@Override
	public void create(District district) {
		
		districtrepo.save(district);
		
	}

	@Override
	public void update(District district) {
		
		districtrepo.save(district);
		
	}

	@Override
	public void delete(District district) {
		
		districtrepo.delete(district);
		
	}

	@Override
	public Optional<District> findById(int id) {
		
		return districtrepo.findById(id);
	}

	@Override
	public List<District> findall(Class c) {
		
		return districtrepo.findAll();
	}

	@Override
	public List<District> findByProvinceId(int id) {
		// TODO Auto-generated method stub
		return districtrepo.findByProvinceId(id);
	}

	

	

	
	
	
}
