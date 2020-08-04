package com.Solar.SolarEnergy.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Solar.SolarEnergy.Domain.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

	
	
}
