package com.Employee.EmployeeFront.ServiceImplementation;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Employee.EmployeeFront.Controller.RegistrarController.RegAdmin;
import com.Employee.EmployeeFront.Dao.RegistrarDao;
import com.Employee.EmployeeFront.Domain.Category;
import com.Employee.EmployeeFront.Domain.Registrant;
import com.Employee.EmployeeFront.Service.RegistrarService;


@Service
@Transactional
public class RegistrarServiceImplementation implements RegistrarService {
	
	@Autowired
	private RegistrarDao regdao ;

	@Override
	public void updateRegistrar(Registrant registrar) {
		
		regdao.save(registrar);
	}

	@Override
	public void deleteRegistrar(int id) {
		
		regdao.deleteById(id);
		
	}
	
	@Override
	public List<Registrant> findAll() {
	
		return regdao.findAll();
	}

	@Override
	public Registrant buildRegistrant(RegAdmin regAdmin) {
		Registrant registrant=new Registrant();
		try {
			
			registrant.setCategory(checkRegistrarCategory(regAdmin.getCategory()));
			registrant.setEmail(regAdmin.getEmail());
			registrant.setFirstname(regAdmin.getFirstname());
			registrant.setLastname(regAdmin.getLastname());
			registrant.setPhone(regAdmin.getPhonenumber());
			registrant.setDob(regAdmin.getDateofbirth());
			registrant.setIdnumber(regAdmin.getIdnumber());
			registrant.setGender(regAdmin.getGender());
			
			
		}catch (Exception ex){
			System.out.println("RegistantService (formRegistrant()) "+ex.getMessage());
		}
		
		return registrant;
	}

	@Override
	public Category checkRegistrarCategory(String registrant) {
		
		for(Category re: Category.values()) {
			if(re.name().equalsIgnoreCase(registrant)) {
				return re;
			}
		}
		
		return null;
	}

	@Override
	public Optional<Registrant> findByid(int id) {
		// TODO Auto-generated method stub
		return regdao.findById(id);
	}

	@Override
	public Optional<Registrant> findByUuid(String uuid) {
		// TODO Auto-generated method stub
		return regdao.findByUuid(uuid);
	}

	@Override
	public Registrant createregistrar(Registrant registrar) {
		// TODO Auto-generated method stub
		return regdao.save(registrar);
	}

	
}
