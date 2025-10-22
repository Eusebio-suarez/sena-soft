package com.senasoft.tikets.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.senasoft.tikets.entity.BookingEntity;
import com.senasoft.tikets.entity.FlightEntity;
import com.senasoft.tikets.enums.StatusEnum;
import com.senasoft.tikets.exceptions.ExceptionImpl;
import com.senasoft.tikets.repository.BookingRepository;
import com.senasoft.tikets.repository.FlightRepository;

@Service
public class BookingService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private BookingRepository bookingRepository;
    
    public Long registerBooking(Long flightId){

        Optional<FlightEntity> flightOptional = flightRepository.findById(flightId);

        if(flightOptional.isEmpty()){

            throw new ExceptionImpl("no se encontro el vuelo.", HttpStatus.NOT_FOUND);
        }

        FlightEntity flight = flightOptional.get();

        BookingEntity booking = BookingEntity.builder()
            .date(LocalDate.now())
            .totalPrice(0.0)
            .status(StatusEnum.WAITING)
            .flight(flight)
            .build();


        BookingEntity bookingRegister = bookingRepository.save(booking);
            
        return bookingRegister.getId();
    }
}
