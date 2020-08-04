package com.Solar.SolarEnergy.Service;

import java.util.List;


import com.Solar.SolarEnergy.Domain.Solarenergy;



public interface SolarEnergyService {
	
	public void createSolarenergy(Solarenergy en);
	public void updateSolarenergy(Solarenergy en);
	public void deleteSolarenergy(int id);
	public Solarenergy findByid(int id);
	public List<Solarenergy> findAll();
	public Solarenergy findByUuid(String uuid);
	

}
