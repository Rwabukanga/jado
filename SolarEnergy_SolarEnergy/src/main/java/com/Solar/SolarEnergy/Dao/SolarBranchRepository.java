package com.Solar.SolarEnergy.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Solar.SolarEnergy.Domain.SolarBranch;

@Repository
public interface SolarBranchRepository extends JpaRepository<SolarBranch, Integer> {

}
