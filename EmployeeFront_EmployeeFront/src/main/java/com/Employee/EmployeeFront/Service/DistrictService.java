package com.Employee.EmployeeFront.Service;

import java.util.List;
import java.util.Optional;

import com.Employee.EmployeeFront.Domain.District;
import com.Employee.EmployeeFront.Domain.Province;

public interface DistrictService {

	public void create(District district);
	public void update(District district);
	public void delete(District district);
	public Optional<District> findById(int id);
	public List<District> findByProvinceId(int id);
	public List<District> findall(Class c);
	
}
