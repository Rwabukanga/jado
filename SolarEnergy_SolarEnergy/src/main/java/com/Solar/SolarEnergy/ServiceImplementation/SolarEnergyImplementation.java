package com.Solar.SolarEnergy.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Solar.SolarEnergy.Dao.SolarenergyRepository;
import com.Solar.SolarEnergy.Dao.solarEnergyDao;
import com.Solar.SolarEnergy.Domain.Solarenergy;
import com.Solar.SolarEnergy.Service.SolarEnergyService;

@Service
public class SolarEnergyImplementation implements SolarEnergyService {

	@Autowired
	private SolarenergyRepository solarenergyrepository;
	
	@Autowired
	private solarEnergyDao solarenergydao;
	
	@Override
	public void createSolarenergy(Solarenergy solar) {
		
		solarenergydao.createsolarenergy(solar);
	}
	
    @Override
	public void updateSolarenergy(Solarenergy solar) {
		
    	solarenergydao.updatesolarenergy(solar);
	}
	

    @Override
	public void deleteSolarenergy(int id) {
		
      solarenergyrepository.delete(id);
	}
	
    @Override
	public Solarenergy findByid(int id) {
    	
	return solarenergydao.findByid(id);
	}
	
    @Override
	public List<Solarenergy> findAll(){
		
		return solarenergydao.findAll(Solarenergy.class);
	}
    
    @Override
    public Solarenergy findByUuid(String uuid) {
    	
    	return solarenergydao.findByUuid(uuid);
    }
	

}
