package com.Solar.SolarEnergy.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Solar.SolarEnergy.Dao.UserRepository;
import com.Solar.SolarEnergy.Domain.User;
import com.Solar.SolarEnergy.Service.UserService;

@Service
public class UserImplementation implements UserService {
	
	@Autowired
	private UserRepository userrepository;
	
	@Override
	public void createUser(User user) {
		
		userrepository.save(user);
	}
	
	@Override
	public void updateUser(User user) {
		
		userrepository.save(user);
	}
	
	@Override
	public void deleteUser(int id) {
		userrepository.delete(id);
	}
	
	@Override
	public User findByid(int id) {
		
	return userrepository.getOne(id);
	}
	
	@Override
	public List<User> findAll(){
		
		return userrepository.findAll();
	}


}
