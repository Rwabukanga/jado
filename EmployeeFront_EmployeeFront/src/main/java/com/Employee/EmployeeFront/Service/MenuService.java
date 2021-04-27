package com.Employee.EmployeeFront.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Employee.EmployeeFront.Domain.menutable;

@Service
public interface MenuService {

	public void createmenu(menutable menu);
	public List<menutable> findall(Class c);
	public Optional<menutable> FinById(int id);
}
