package com.senasoft.tikets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senasoft.tikets.entity.PassengerEntity;

@Repository
public interface PassengerRepository extends JpaRepository<PassengerEntity,Long> {
    
}
