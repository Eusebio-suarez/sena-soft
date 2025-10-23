package com.senasoft.tikets.dto.request;

import com.senasoft.tikets.enums.DocEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequestDTO {

    @NotNull
    private Long bookingId;

    @NotBlank
    private String name;

    private DocEnum docType;

    @NotBlank
    private String numberDoc;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phone;

}
