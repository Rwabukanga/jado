package com.Solar.SolarEnergy.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Solar.SolarEnergy.Domain.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}
