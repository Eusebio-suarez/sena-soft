package com.senasoft.tikets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senasoft.tikets.dto.request.BookingRequetDTO;
import com.senasoft.tikets.services.BookingService;
import com.senasoft.tikets.utils.ApiResponse;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@RequestBody BookingRequetDTO bookingRequetDTO) {
        
        Long bookingId = bookingService.registerBooking(bookingRequetDTO.getFlightId());

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.builder()
                .success(true)
                .message("exito")
                .data(bookingId)
                .build()
            );
    }

}
