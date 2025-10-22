package com.senasoft.tikets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senasoft.tikets.dto.response.FlightResponseDTO;
import com.senasoft.tikets.services.FlightService;
import com.senasoft.tikets.utils.ApiResponse;


@RestController
@RequestMapping("/api/v1/flights")
public class BuyerController {
    
    @Autowired
    private FlightService flightService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<?>> register() {
        
        List<FlightResponseDTO> flights = flightService.getFlights();

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.builder()
                .success(true)
                .message("exito")
                .data(flights)
                .build()
            );
    }
    
}
