package com.senasoft.tikets.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senasoft.tikets.dto.response.FlightResponseDTO;
import com.senasoft.tikets.repository.FlightRepository;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;
    
    public List<FlightResponseDTO>getFlights(){

        return flightRepository.findAll().stream()
            .map(flight -> FlightResponseDTO.builder()
                .id(flight.getId())
                .arrivalDate(flight.getArrivalDate())
                .departureDate(flight.getDepartureDate())
                .destination(flight.getDestination())
                .origin(flight.getOrigin())
                .type(flight.getType())
                .price((flight.getPrice()))
                .build()
            )
            .toList();
    }
}
