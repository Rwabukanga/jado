package com.Employee.EmployeeFront.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Employee.EmployeeFront.Domain.menutable;

@Repository
public interface menurepo extends JpaRepository<menutable, Integer>{

	
}
