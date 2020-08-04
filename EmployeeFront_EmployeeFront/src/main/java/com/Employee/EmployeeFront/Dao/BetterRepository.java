package com.Employee.EmployeeFront.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Employee.EmployeeFront.Domain.Better;

@Repository
public interface BetterRepository extends JpaRepository<Better, Integer> {

	Optional<Better> findById(int id);
}
