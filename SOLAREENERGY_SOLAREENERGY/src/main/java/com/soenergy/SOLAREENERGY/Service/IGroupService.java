package com.soenergy.SOLAREENERGY.Service;

import java.util.List;

import com.soenergy.SOLAREENERGY.Domain.TheGroup;



public interface IGroupService {

	public String create (TheGroup group);
	public String update(TheGroup group);
	public String delete(TheGroup group);
	public List<TheGroup>all();
	public TheGroup findById(long id);
	
	
}
