package com.senasoft.tikets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senasoft.tikets.dto.request.PaymentRequestDTO;
import com.senasoft.tikets.dto.response.TiketResponseDTO;
import com.senasoft.tikets.services.PaymentService;
import com.senasoft.tikets.utils.ApiResponse;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    
    @Autowired
    private PaymentService paymentService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<?>> pay(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        
        List<TiketResponseDTO> tikets = paymentService.processPayment(paymentRequestDTO); 

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.builder()
                .success(true)
                .message("exito")
                .data(tikets)
                .build()
            );
    }
}
