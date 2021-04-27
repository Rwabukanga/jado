package com.Employee.EmployeeFront.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Employee.EmployeeFront.Domain.Ussd;



@Repository
public interface UssdRepository extends JpaRepository<Ussd, Integer> {

}
