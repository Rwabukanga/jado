package com.soenergy.SOLAREENERGY.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soenergy.SOLAREENERGY.Dao.AssignedPermissionDao;
import com.soenergy.SOLAREENERGY.Dao.SystemUserDao;
import com.soenergy.SOLAREENERGY.Domain.AssignedPermission;
import com.soenergy.SOLAREENERGY.Domain.Permission;
import com.soenergy.SOLAREENERGY.Domain.SystemUser;
import com.soenergy.SOLAREENERGY.Service.ISystemUserService;
import com.soenergy.SOLAREENERGY.Utility.Encryption;
import com.soenergy.SOLAREENERGY.Utility.Msg;



@Service
public class SystemUserServiceImpl implements ISystemUserService{

	@Autowired
	private SystemUserDao userDao;
	@Autowired
	private AssignedPermissionDao assignedPermissionDao;

	@Override
	public String create(SystemUser systemUser) {
		String message="";
		try{
			systemUser.setPassword(Encryption.md5(systemUser.getPassword())); 
			userDao.create(systemUser);
			message=Msg.save;
		}catch (Exception e) {
			message=Msg.error;
		}
		
		return message;
	}

	@Override
	public String update(SystemUser systemUser) {
		String message="";
		try{
			userDao.update(systemUser);
			message=Msg.update;
		}catch (Exception e) {
			message=Msg.error;
		}
		
		return message;
	}

	@Override
	public String delete(SystemUser systemUser) {
		String message="";
		try{
			systemUser.setDeletedStatus(true);
			userDao.update(systemUser);
			message=Msg.delete;
		}catch (Exception e) {
			message=Msg.error;
		}
		
		return message;
	}

	@Override
	public List<SystemUser> all() {
		
		return userDao.all();
	}

	@Override
	public SystemUser findById(long id) {
		
		return userDao.findOne(id); 
	}

	@Override
	public List<SystemUser> system_user_by_referenceName(String referenceName) {
		
		return userDao.system_user_by_referenceName(referenceName); 
	}

	@Override
	public List<Permission> user_permissions(String applicationName, long objectId, String objectName) {
		List<Permission>list=new ArrayList<>();
		for(AssignedPermission a:assignedPermissionDao.user_permissions(applicationName, objectId, objectName)){
			
		}
		return list;
		
	}

	/*
	 * Login
	 * */
	
	@Override
	public SystemUser login(String applicationName,String username, String password) {
		List<SystemUser>users=userDao.login(applicationName, username, password);		
		if(users.size()>0){
			return users.get(0);
		}else{
			return null;
		}
		
	}

	@Override
	public String lock_user(String username) {
	
		String message="";
		try{
			SystemUser systemUser=findByUsername(username); 
			systemUser.setUserLocked(true);
			systemUser.setReasonForLock("3 attempts failed");
			userDao.update(systemUser);
			message=Msg.update;
		}catch (Exception e) {
			message=Msg.error;
		}
		
		return message;
		
		
	}

	@Override
	public SystemUser findByUUId(String uuid) {
		
		return userDao.find_user_by_uuid(uuid); 
	}

	@Override
	public SystemUser findByUsername(String username) {
		
		return userDao.find_user_by_username(username); 
	}

	@Override
	public List<SystemUser> super_admin(String applicationName) {
		
		return userDao.super_admins(applicationName); 
	}

	@Override
	public List<SystemUser> adminsAndEntityManagers(String applicationName, String objectName, long objectId) {
		// TODO Auto-generated method stub
		return userDao.adminsAndEntityManagers(applicationName,objectName,objectId);
	}

	@Override
	public String resetPassword(SystemUser systemUser) {
		String message="";
		try{
			systemUser.setPassword(Encryption.md5(systemUser.getPassword())); 
			userDao.update(systemUser);
			message=Msg.reset;
		}catch (Exception e) {
			e.printStackTrace();
			message=Msg.error;
		}
		
		return message;
	}

	@Override
	public String blockUser(long objectId, boolean status) {
		return userDao.blockUser(objectId, status);
	}

	
	
}
