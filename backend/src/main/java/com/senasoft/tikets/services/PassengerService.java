package com.senasoft.tikets.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.senasoft.tikets.dto.request.PassengerRequestDTO;
import com.senasoft.tikets.entity.BookingEntity;
import com.senasoft.tikets.entity.FlightEntity;
import com.senasoft.tikets.entity.PassengerEntity;
import com.senasoft.tikets.enums.SeatStatusEnum;
import com.senasoft.tikets.exceptions.ExceptionImpl;
import com.senasoft.tikets.repository.BookingRepository;
import com.senasoft.tikets.repository.FlightRepository;
import com.senasoft.tikets.repository.PassengerRepository;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private FlightRepository flightRepository;

    public boolean  registerPassenger(PassengerRequestDTO passengerRequestDTO){

        //buscar la reserva
        Optional<BookingEntity> bookingOptional = bookingRepository.findById(passengerRequestDTO.getBookingId());

        if(bookingOptional.isEmpty()){
            throw new ExceptionImpl("no se encontro la reserva",HttpStatus.NOT_FOUND);
        }

        BookingEntity booking = bookingOptional.get();

        //validar los 5 pasageros maximo
        long passengerCount = passengerRepository.countByBookingId(booking.getId());

        if (passengerCount >= 5) {
            throw new ExceptionImpl("limite alcanzado",HttpStatus.NOT_FOUND);
        }

        //buscar el vuelo
        Optional<FlightEntity> flightOptional = flightRepository.findById(booking.getFlight().getId());

        if(flightOptional.isEmpty()){
            throw new ExceptionImpl("no se encontro el vuelo",HttpStatus.NOT_FOUND);
        }

        FlightEntity flight = flightOptional.get();

        if(passengerRequestDTO.getSeatNumber()>flight.getPlane().getCapacity()){
            throw new ExceptionImpl("no se encontro el asiento",HttpStatus.NOT_FOUND);
        }

        //varificar que el asiento no este vendido
        if(passengerRepository.existsByBooking_Flight_IdAndSeatNumberAndSeatStatus(flight.getId(),passengerRequestDTO.getSeatNumber(), SeatStatusEnum.BUSY)){
            throw new ExceptionImpl("asiento ocupado", HttpStatus.CONFLICT);
        }

        //construir el pasajero
        PassengerEntity passenger = PassengerEntity.builder()
            .birth(passengerRequestDTO.getBirth())
            .gender(passengerRequestDTO.getGender())
            .infant(passengerRequestDTO.isInfant())
            .phone(passengerRequestDTO.getPhone())
            .email(passengerRequestDTO.getEmail())
            .seatNumber(passengerRequestDTO.getSeatNumber())
            .seatStatus(SeatStatusEnum.RESERVED)
            .booking(booking)
            .build();
        
        //registarr el passagero
        passengerRepository.save(passenger);

        //actualizar el precio de la reserva
        booking.setTotalPrice(booking.getTotalPrice()+flight.getPrice());

        //actualizar la reserva
        bookingRepository.save(booking);

        return true;

    }
}
