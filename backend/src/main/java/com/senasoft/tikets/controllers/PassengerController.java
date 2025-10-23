package com.senasoft.tikets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senasoft.tikets.dto.request.PassengerRequestDTO;
import com.senasoft.tikets.services.PassengerService;
import com.senasoft.tikets.utils.ApiResponse;


@RestController
@RequestMapping("/api/v1/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@RequestBody PassengerRequestDTO passengerRequestDTO) {
        
        boolean register = passengerService.registerPassenger(passengerRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.builder()
                .success(true)
                .message("exito")
                .data(register)
                .build()
            );
    }

}