package com.soenergy.SOLAREENERGY.Service;

import java.util.List;

import com.soenergy.SOLAREENERGY.Domain.AssignedPermission;




public interface IAssgnedPService {

	public String create (AssignedPermission assignedPermission);
	public String update(AssignedPermission assignedPermission);
	public String delete(AssignedPermission assignedPermission);
	public List<AssignedPermission>all();
	public AssignedPermission findById(long id);
}
