package com.soenergy.SOLAREENERGY.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soenergy.SOLAREENERGY.Dao.GroupDao;
import com.soenergy.SOLAREENERGY.Domain.TheGroup;
import com.soenergy.SOLAREENERGY.Service.IGroupService;



@Service
public class GroupServiceImpl implements IGroupService {

	@Autowired
	private GroupDao groupDao;
	
	@Override
	public String create(TheGroup group) {
		
		return groupDao.create(group);
	}

	@Override
	public String update(TheGroup group) {
		return groupDao.update(group);
	}

	@Override
	public String delete(TheGroup group) {
		
		return groupDao.delete(group); 
	}

	@Override
	public List<TheGroup> all() {
		
		return groupDao.all();
	}

	@Override
	public TheGroup findById(long id) {
		
		return groupDao.findOne(id); 
	}

	
	
}
