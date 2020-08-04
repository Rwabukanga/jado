package com.Solar.SolarEnergy.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Solar.SolarEnergy.Domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
