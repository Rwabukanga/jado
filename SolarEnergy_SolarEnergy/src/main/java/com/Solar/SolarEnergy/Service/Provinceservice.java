package com.Solar.SolarEnergy.Service;

import java.util.List;

import com.Solar.SolarEnergy.Domain.Province;

public interface Provinceservice {

	public void createprovince(Province pr);
	public void delete(Province pr);
	public void update(Province pr);
	public Province findByid(int id);
	public List<Province> findAll();
	
}
