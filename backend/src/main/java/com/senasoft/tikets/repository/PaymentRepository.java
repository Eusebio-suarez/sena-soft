package com.senasoft.tikets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senasoft.tikets.entity.PaymentEntity;

@Repository
public interface  PaymentRepository extends  JpaRepository<PaymentEntity, Long> {
    

}
