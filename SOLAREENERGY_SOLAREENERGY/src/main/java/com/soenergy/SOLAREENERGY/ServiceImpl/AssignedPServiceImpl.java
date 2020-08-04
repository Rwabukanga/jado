package com.soenergy.SOLAREENERGY.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soenergy.SOLAREENERGY.Dao.AssignedPermissionDao;
import com.soenergy.SOLAREENERGY.Domain.AssignedPermission;
import com.soenergy.SOLAREENERGY.Service.IAssgnedPService;


@Service
public class AssignedPServiceImpl implements IAssgnedPService{

	@Autowired
	private AssignedPermissionDao assignedPermissionDao;

	@Override
	public String create(AssignedPermission assignedPermission) {
		
		return assignedPermissionDao.create(assignedPermission);
	}

	@Override
	public String update(AssignedPermission assignedPermission) {
		
		return assignedPermissionDao.update(assignedPermission);
	}

	@Override
	public String delete(AssignedPermission assignedPermission) {
		
		return assignedPermissionDao.delete(assignedPermission);
	}

	@Override
	public List<AssignedPermission> all() {
		
		return assignedPermissionDao.all();
	}

	@Override
	public AssignedPermission findById(long id) {
		
		return assignedPermissionDao.findOne(id); 
	}
	
	
}
