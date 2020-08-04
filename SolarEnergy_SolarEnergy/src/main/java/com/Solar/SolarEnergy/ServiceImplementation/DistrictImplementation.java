package com.Solar.SolarEnergy.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Solar.SolarEnergy.Dao.DistrictDao;
import com.Solar.SolarEnergy.Domain.District;
import com.Solar.SolarEnergy.Service.DistrictService;

@Service
public class DistrictImplementation implements DistrictService {

	@Autowired
	private DistrictDao districtdao;
	
	public void createDistrict(District district) {
		
		districtdao.createDistrict(district);
	}
	
	public List<District> findAll(Class c){
		return districtdao.findAll(District.class);
	}

	@Override
	public void createprovince(District pr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(District pr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(District pr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public District findByid(int id) {
	
		return districtdao.findByid(id);
		
	}

	@Override
	public List<District> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
