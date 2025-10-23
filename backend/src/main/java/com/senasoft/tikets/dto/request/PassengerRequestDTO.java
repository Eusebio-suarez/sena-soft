package com.senasoft.tikets.dto.request;

import java.time.LocalDate;

import com.senasoft.tikets.enums.DocEnum;
import com.senasoft.tikets.enums.GenderEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PassengerRequestDTO {

    private Long bookingId;

    private String name;

    private DocEnum docType;

    private String numberDoc;

    @Past
    private LocalDate birth;

    @NotBlank
    private GenderEnum gender;

    private boolean infant;

    @NotBlank
    private String phone;

    @Email
    private String email;

    @Positive
    private Long seatNumber;

}
