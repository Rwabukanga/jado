package com.Employee.EmployeeFront.Service;

import java.util.List;
import java.util.Optional;

import com.Employee.EmployeeFront.Domain.SystemUser;

public interface ISystemUserService {

	public String create(SystemUser user);
	public String update (SystemUser systemUser);
	public String delete(SystemUser systemUser);
	public List<SystemUser>all();
	public Optional<SystemUser> findById(long id);
	public List<SystemUser> system_user_by_referenceName(String referenceName);
	/*public List<Permission>user_permissions(String applicationName, long objectId, String objectName);*/ 
	public SystemUser findByUsernameAndPassword(String Username, String Password);
	public String lock_user( String username);
	public Optional<SystemUser> findByuuid(String uuid); 
	public SystemUser findByUsername(String username); 
	public List<SystemUser>super_admin(String applicationName);
	public List<SystemUser>adminsAndEntityManagers(String applicationName, String objectName,long objectId);
	public String resetPassword(SystemUser systemUser);
	public String blockUser(long objectId,boolean status);
}
