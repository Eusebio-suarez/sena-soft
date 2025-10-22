package com.senasoft.tikets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senasoft.tikets.dto.request.BuyerRequest;
import com.senasoft.tikets.services.BuyerService;
import com.senasoft.tikets.utils.ApiResponse;


@RestController
@RequestMapping("/api/v1/buyers")
public class BuyerController {
    
    
}
