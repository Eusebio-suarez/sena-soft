package com.senasoft.tikets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senasoft.tikets.entity.TiketEntity;

@Repository
public interface TiketReposistory extends JpaRepository<TiketEntity, Long> {
    
}
