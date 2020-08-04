package com.Employee.EmployeeFront.Service;

import java.util.List;
import java.util.Optional;

import com.Employee.EmployeeFront.Domain.Province;

public interface ProvinceService {

	public void create(Province province);
	public void update(Province province);
	public void delete(Province province);
	public Optional<Province> findById(int id);
	public List<Province> findall(Class c);
}
