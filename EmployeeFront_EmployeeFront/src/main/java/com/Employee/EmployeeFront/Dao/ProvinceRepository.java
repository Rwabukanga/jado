package com.Employee.EmployeeFront.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Employee.EmployeeFront.Domain.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

	Optional<Province> findById(int id);
}
