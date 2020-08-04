package com.Solar.SolarEnergy.Service;

import java.util.List;

import com.Solar.SolarEnergy.Domain.Location;

public interface LocationService {

	public void createLocation(Location location);
	public void updateLocation(Location location);
	public void deleteLocation(int id);
	public Location findByid(int id);
	public List<Location> findAll();
}
