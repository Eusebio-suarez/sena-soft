package com.senasoft.tikets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senasoft.tikets.entity.BookingEntity;
import com.senasoft.tikets.enums.StatusEnum;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    
    @Query("SELECT b FROM BookingEntity b JOIN b.passengers p WHERE p.seatNumber IN :seatNumbers AND b.status = :status")
    List<BookingEntity> findBySeatNumbersAndStatus(@Param("seatNumbers") List<Long> seatNumbers, @Param("status") StatusEnum status);

}
