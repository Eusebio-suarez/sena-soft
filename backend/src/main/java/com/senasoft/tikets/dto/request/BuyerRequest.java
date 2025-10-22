package com.senasoft.tikets.dto.request;

import com.senasoft.tikets.enums.DocEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BuyerRequest {
    
    @NotBlank
    private DocEnum docType;

    @NotBlank
    private String docNumber;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;
    
    @NotBlank
    @Email
    private String email;
}
