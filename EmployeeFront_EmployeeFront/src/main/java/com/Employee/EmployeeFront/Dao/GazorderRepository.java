package com.Employee.EmployeeFront.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Employee.EmployeeFront.Domain.Gazorder;

@Repository
public interface GazorderRepository extends JpaRepository<Gazorder, Integer> {

	Optional<Gazorder> findByUuid(String uuid);
	Optional<Gazorder> findById(int id);
}
