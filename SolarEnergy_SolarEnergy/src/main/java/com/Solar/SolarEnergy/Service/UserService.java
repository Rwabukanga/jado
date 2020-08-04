package com.Solar.SolarEnergy.Service;

import java.util.List;

import com.Solar.SolarEnergy.Domain.User;



public interface UserService {
	
	public void createUser(User user);
	public void updateUser(User user);
	public void deleteUser(int id);
	public User findByid(int id);
	public List<User> findAll();
	

}
