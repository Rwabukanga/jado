package com.Solar.SolarEnergy.ServiceImplementation;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Solar.SolarEnergy.Controller.RegistrarController.RegAdmin;
import com.Solar.SolarEnergy.Dao.RegistrarDao;
import com.Solar.SolarEnergy.Dao.RegistrarRepository;
import com.Solar.SolarEnergy.Domain.Category;
import com.Solar.SolarEnergy.Domain.Registrar;
import com.Solar.SolarEnergy.Service.RegistrarService;

@Service
public class RegistrarImplementation implements RegistrarService {
	
	@Autowired
	private RegistrarRepository registrarrepository;
	
	@Autowired
	private SessionFactory sessionfactory;
	
	@Autowired
	private RegistrarDao registrardao;
	
	@Override
	public void createRegistrar(Registrar registrar) {
		registrardao.createRegistrar(registrar);
	}
	
    @Override
	public void updateRegistrar(Registrar registrar) {
		
		registrarrepository.save(registrar);
	}
	

    @Override
	public void deleteRegistrar(int id) {
		
		registrarrepository.delete(id);
	}
	
    @Override
	public Registrar findByid(int id) {
    	
	return registrardao.findByid(id);
	}
    
    public Registrar findByUuid(String uuid) {
    	
    	return registrardao.findByUuid(uuid);
    }
	
    @Override
	public List<Registrar> findAll(){
		
		return registrarrepository.findAll();
	}

	@Override
	public Registrar buildRegistrant(RegAdmin regAdmin) {
		Registrar registrant=new Registrar();
		try {
			
			
			
		    
			registrant.setCategory(checkRegistrarCategory(regAdmin.getCategory()));
			registrant.setEmail(regAdmin.getEmail());
			registrant.setFirstname(regAdmin.getFirstname());
			registrant.setLastname(regAdmin.getLastname());
			registrant.setPhonenumber(regAdmin.getPhonenumber());
			registrant.setDateofbirth(regAdmin.getDateofbirth());
			registrant.setIdnumber(regAdmin.getIdnumber());
			registrant.setGender(regAdmin.getGender());
			
			
		}catch (Exception ex){
			System.out.println("RegistantService (formRegistrant()) "+ex.getMessage());
		}
		return registrant;
	}
	@Override
	public Category checkRegistrarCategory(String registrant){
		for (Category re : Category.values()) {
			if (re.name().equalsIgnoreCase(registrant))
				return re;
		}
		return null;
	}
	}



