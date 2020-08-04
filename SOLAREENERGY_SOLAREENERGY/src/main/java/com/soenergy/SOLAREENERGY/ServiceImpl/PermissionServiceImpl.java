package com.soenergy.SOLAREENERGY.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soenergy.SOLAREENERGY.Dao.PermissionDao;
import com.soenergy.SOLAREENERGY.Domain.Permission;
import com.soenergy.SOLAREENERGY.Service.IPermissionService;


@Service
public class PermissionServiceImpl implements IPermissionService {
	
	@Autowired
	private PermissionDao permissionDao;

	@Override
	public String create(Permission permission) {
		
		return permissionDao.create(permission);
	}

	@Override
	public String update(Permission permission) {
		
		return permissionDao.update(permission);
	}

	@Override
	public String delete(Permission permission) {
		
		return permissionDao.delete(permission);
	}

	@Override
	public List<Permission> all() {
		
		return permissionDao.all();
	}

	@Override
	public Permission findById(long id) {
		
		return permissionDao.findOne(id); 
	}

}
