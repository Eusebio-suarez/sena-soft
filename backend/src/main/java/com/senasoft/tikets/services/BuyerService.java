package com.senasoft.tikets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.senasoft.tikets.dto.request.BuyerRequest;
import com.senasoft.tikets.entity.BuyerEntity;
import com.senasoft.tikets.exceptions.ExceptionImpl;
import com.senasoft.tikets.repository.BuyerRepository;

@Service
public class BuyerService {
    
    @Autowired
    private BuyerRepository buyerRepository;

    public Long registerBuyer(BuyerRequest buyerRequest){

        BuyerEntity buyer = BuyerEntity.builder()
            .docNumber(buyerRequest.getDocNumber())
            .docType(buyerRequest.getDocType())
            .name(buyerRequest.getName())
            .email(buyerRequest.getEmail())
            .phone(buyerRequest.getPhone())
            .build();

        BuyerEntity buyerRegister = buyerRepository.save(buyer);
    
        if(buyerRegister.getId()==null){

            throw new ExceptionImpl("Error al registrar comprador", HttpStatus.INTERNAL_SERVER_ERROR );
        }

        return buyerRegister.getId();

    }
}
