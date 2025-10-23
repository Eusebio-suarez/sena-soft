package com.senasoft.tikets.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senasoft.tikets.entity.PassengerEntity;

import com.senasoft.tikets.enums.SeatStatusEnum;
import java.util.List;
import com.senasoft.tikets.entity.BookingEntity;



@Repository
public interface PassengerRepository extends JpaRepository<PassengerEntity,Long> {

    boolean existsByBooking_Flight_IdAndSeatNumberAndSeatStatus(Long flightId, Long seatNumber, SeatStatusEnum seatStatusEnum);

    long countByBookingId(Long bookingId);

    boolean existsByBookingIdAndSeatNumber(Long bookingId, Long seatNumber);

    List<PassengerEntity> findByBooking(BookingEntity booking);

}
