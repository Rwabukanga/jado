package com.Employee.EmployeeFront.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Employee.EmployeeFront.Domain.Branchuser;

@Repository
public interface BracnhuserRepository extends JpaRepository<Branchuser, Integer> {
   Optional<Branchuser> findByUuid(String uuid);
   Optional<Branchuser> findById(int id);
}
