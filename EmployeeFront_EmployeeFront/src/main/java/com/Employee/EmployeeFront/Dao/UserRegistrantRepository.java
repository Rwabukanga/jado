package com.Employee.EmployeeFront.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Employee.EmployeeFront.Domain.UserRegistrant;

@Repository
public interface UserRegistrantRepository extends JpaRepository<UserRegistrant, Integer> {

}
