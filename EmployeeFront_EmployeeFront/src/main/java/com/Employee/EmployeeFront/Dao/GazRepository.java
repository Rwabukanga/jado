package com.Employee.EmployeeFront.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Employee.EmployeeFront.Domain.Gaz;

@Repository
public interface GazRepository extends JpaRepository<Gaz, Integer> {

	Optional<Gaz> findByUuid(String uuid);
	Optional<Gaz> findById(int id);
}
