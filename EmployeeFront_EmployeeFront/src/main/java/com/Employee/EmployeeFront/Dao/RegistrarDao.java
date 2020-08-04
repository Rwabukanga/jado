package com.Employee.EmployeeFront.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Employee.EmployeeFront.Domain.Registrant;

@Repository
public interface RegistrarDao extends JpaRepository<Registrant, Integer> {
	
	Optional<Registrant> findByUuid(String uuid);
	Optional<Registrant> findById(int id);
}
