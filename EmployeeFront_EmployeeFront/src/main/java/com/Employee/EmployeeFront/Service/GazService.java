package com.Employee.EmployeeFront.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Employee.EmployeeFront.Domain.District;
import com.Employee.EmployeeFront.Domain.Gaz;

@Service
public interface GazService {

	public void createGaz(Gaz gaz);
	public void updateGaz(Gaz gaz);
	public void delete(Gaz gaz);
	public Optional<Gaz> findById(int id);
	public Optional<Gaz> findByUuid(String uuid);
	public List<Gaz> findAll(Class c);
	
}
