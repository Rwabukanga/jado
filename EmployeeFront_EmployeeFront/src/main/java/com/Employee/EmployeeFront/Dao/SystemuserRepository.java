package com.Employee.EmployeeFront.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Employee.EmployeeFront.Domain.SystemUser;

@Repository
public interface SystemuserRepository extends JpaRepository<SystemUser, Long> {

	Optional<SystemUser>findByuuid(String uuid);
	Optional<SystemUser>findById(long  id);
    public SystemUser findByUsernameAndPassword(String Username, String Password);
}
