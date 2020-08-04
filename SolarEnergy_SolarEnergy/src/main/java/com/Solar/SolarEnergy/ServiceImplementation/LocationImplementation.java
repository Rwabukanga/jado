package com.Solar.SolarEnergy.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Solar.SolarEnergy.Dao.LocationRepository;
import com.Solar.SolarEnergy.Domain.Location;
import com.Solar.SolarEnergy.Service.LocationService;

@Service
public class LocationImplementation implements LocationService {

	@Autowired
	private LocationRepository locationrepository;
	
	@Override
	public void createLocation(Location loc) {
		
		locationrepository.save(loc);
	}
	
	@Override
	public void updateLocation(Location loc) {
		
		locationrepository.save(loc);
	}
	
	@Override
	public void deleteLocation(int id) {
		locationrepository.delete(id);
	}
	
	@Override
	public Location findByid(int id) {
	return locationrepository.getOne(id);
	}
	
	public List<Location> findAll(){
		
		return locationrepository.findAll();
	}
}
