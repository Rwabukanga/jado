package com.Employee.EmployeeFront.Service;

import java.util.List;
import java.util.Optional;

import com.Employee.EmployeeFront.Controller.RegistrarController;
import com.Employee.EmployeeFront.Domain.Category;
import com.Employee.EmployeeFront.Domain.Registrant;



public interface RegistrarService {
	
	public Registrant createregistrar(Registrant registrar);
	public void updateRegistrar(Registrant registrar);
	public void deleteRegistrar(int id);
	public Optional<Registrant> findByid(int id);
	public Optional<Registrant> findByUuid(String uuid);
	public List<Registrant> findAll();
	public Registrant buildRegistrant(RegistrarController.RegAdmin regAdmin);
	public Category checkRegistrarCategory(String registrant);
	
	

}
