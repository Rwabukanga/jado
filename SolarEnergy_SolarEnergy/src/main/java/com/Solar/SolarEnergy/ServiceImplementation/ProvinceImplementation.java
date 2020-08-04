package com.Solar.SolarEnergy.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Solar.SolarEnergy.Dao.ProvinceDao;
import com.Solar.SolarEnergy.Domain.Province;
import com.Solar.SolarEnergy.Service.Provinceservice;

@Service
public class ProvinceImplementation implements Provinceservice {

	@Autowired
	private ProvinceDao provincedao;
	
	public List<Province> findAll(Class c){
		return provincedao.findAll(Province.class);
	}

	@Override
	public void createprovince(Province pr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Province pr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Province pr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Province findByid(int id) {
	
		return provincedao.findByid(id);
		
	}

	@Override
	public List<Province> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
