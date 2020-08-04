package com.soenergy.SOLAREENERGY.Service;

import java.util.List;

import com.soenergy.SOLAREENERGY.Domain.Permission;
import com.soenergy.SOLAREENERGY.Domain.SystemUser;



public interface ISystemUserService {

	public String create(SystemUser systemUser);
	public String update (SystemUser systemUser);
	public String delete(SystemUser systemUser);
	public List<SystemUser>all();
	public SystemUser findById(long id);
	public List<SystemUser> system_user_by_referenceName(String referenceName);
	public List<Permission>user_permissions(String applicationName, long objectId, String objectName); 
	public SystemUser login(String applicationName,String username,String password);
	public String lock_user( String username);
	public SystemUser findByUUId(String uuid); 
	public SystemUser findByUsername(String username); 
	public List<SystemUser>super_admin(String applicationName);
	public List<SystemUser>adminsAndEntityManagers(String applicationName, String objectName,long objectId);
	public String resetPassword(SystemUser systemUser);

	public String blockUser(long objectId,boolean status);
}
