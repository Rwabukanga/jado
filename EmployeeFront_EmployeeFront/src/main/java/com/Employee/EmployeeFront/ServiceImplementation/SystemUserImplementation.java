package com.Employee.EmployeeFront.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Employee.EmployeeFront.Dao.SystemuserRepository;
import com.Employee.EmployeeFront.Domain.SystemUser;
import com.Employee.EmployeeFront.Service.ISystemUserService;
import com.Employee.EmployeeFront.Utility.Encryption;
import com.Employee.EmployeeFront.Utility.Msg;

@Service
@Transactional
public class SystemUserImplementation implements ISystemUserService {

	@Autowired
	private SystemuserRepository systemrepo;
	
	@Autowired
	private ISystemUserService systemservice;
	
	@Override
	public String create(SystemUser user) {
		 String message="";
			try{
				user.setPassword(Encryption.md5(user.getPassword())); 
				systemrepo.save(user);
				message=Msg.save;
			}catch (Exception e) {
				message=Msg.error;
			}
			return message;
	}

	@Override
	public String update(SystemUser systemUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(SystemUser systemUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SystemUser> all() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SystemUser> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SystemUser> system_user_by_referenceName(String referenceName) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public String lock_user(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SystemUser> findByuuid(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemUser findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SystemUser> super_admin(String applicationName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SystemUser> adminsAndEntityManagers(String applicationName, String objectName, long objectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String resetPassword(SystemUser systemUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String blockUser(long objectId, boolean status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemUser findByUsernameAndPassword(String Username, String Password) {
		// TODO Auto-generated method stub
		return systemrepo.findByUsernameAndPassword(Username, Password);
	}

	

	

	
}
