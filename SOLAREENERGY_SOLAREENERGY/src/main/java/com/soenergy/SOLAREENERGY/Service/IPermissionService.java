package com.soenergy.SOLAREENERGY.Service;

import java.util.List;

import com.soenergy.SOLAREENERGY.Domain.Permission;



public interface IPermissionService {

	public String create (Permission permission);
	public String update(Permission permission);
	public String delete(Permission permission);
	public List<Permission>all();
	public Permission findById(long id);
}
