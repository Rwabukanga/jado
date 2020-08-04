package com.Employee.EmployeeFront.Service;

import java.util.List;
import java.util.Optional;

import com.Employee.EmployeeFront.Domain.Gazorder;

public interface GazorderService {

	public void creategazorder(Gazorder order);
	public void updategazorder(Gazorder order);
	public void delete(Gazorder order);
	public Optional<Gazorder> findById(int id);
	public Optional<Gazorder> findByUuid(String uuid);
	public List<Gazorder> findAll(Class c);
	public byte[] OrderGazDetailsPDF(Gazorder app);
}
