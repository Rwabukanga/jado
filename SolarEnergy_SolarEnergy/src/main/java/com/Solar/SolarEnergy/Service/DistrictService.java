package com.Solar.SolarEnergy.Service;

import java.util.List;

import com.Solar.SolarEnergy.Domain.District;

public interface DistrictService {

	public void createprovince(District pr);
	public void delete(District pr);
	public void update(District pr);
	public District findByid(int id);
	public List<District> findAll();
}
