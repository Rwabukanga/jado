package com.Solar.SolarEnergy.Service;

import java.util.List;

import com.Solar.SolarEnergy.Controller.RegistrarController;
import com.Solar.SolarEnergy.Domain.Category;
import com.Solar.SolarEnergy.Domain.Registrar;

public interface RegistrarService {
	
	public void createRegistrar(Registrar registrar);
	public void updateRegistrar(Registrar registrar);
	public void deleteRegistrar(int id);
	public Registrar findByid(int id);
	public Registrar findByUuid(String uuid);
	public List<Registrar> findAll();
	public Registrar buildRegistrant(RegistrarController.RegAdmin regAdmin);
	public Category checkRegistrarCategory(String registrant);
}
