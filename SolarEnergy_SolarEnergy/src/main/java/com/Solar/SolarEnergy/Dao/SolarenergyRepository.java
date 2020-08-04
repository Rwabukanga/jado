package com.Solar.SolarEnergy.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Solar.SolarEnergy.Domain.Solarenergy;

@Repository
public interface SolarenergyRepository extends JpaRepository<Solarenergy, Integer> {

}
