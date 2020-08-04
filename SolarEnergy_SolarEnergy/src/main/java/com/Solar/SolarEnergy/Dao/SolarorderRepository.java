package com.Solar.SolarEnergy.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Solar.SolarEnergy.Domain.Solarorder;

@Repository
public interface SolarorderRepository extends JpaRepository<Solarorder, Integer> {

}
