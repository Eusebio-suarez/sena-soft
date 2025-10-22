package com.senasoft.tikets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senasoft.tikets.entity.PlaneEntity;


@Repository
public interface PlaneRepository extends JpaRepository<PlaneEntity, Long> {
    
}
