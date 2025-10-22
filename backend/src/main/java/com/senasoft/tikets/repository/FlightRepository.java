package com.senasoft.tikets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senasoft.tikets.entity.FlightEntity;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {
    
}
