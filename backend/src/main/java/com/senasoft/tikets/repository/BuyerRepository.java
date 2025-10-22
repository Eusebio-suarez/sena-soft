package com.senasoft.tikets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senasoft.tikets.entity.BuyerEntity;

@Repository
public interface  BuyerRepository extends JpaRepository<BuyerEntity, Long> {
    
}
