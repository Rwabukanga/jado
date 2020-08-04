package com.Employee.EmployeeFront.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Employee.EmployeeFront.Domain.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

	Optional<Branch> findByUuid(String uuid);
	Optional<Branch> findById(int id);
}
