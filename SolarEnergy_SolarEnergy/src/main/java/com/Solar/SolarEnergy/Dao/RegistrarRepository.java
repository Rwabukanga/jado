package com.Solar.SolarEnergy.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Solar.SolarEnergy.Domain.Registrar;

@Repository
public interface RegistrarRepository extends JpaRepository<Registrar, Integer> {

}
