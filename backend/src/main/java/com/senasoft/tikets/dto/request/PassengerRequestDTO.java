package com.senasoft.tikets.dto.request;

import java.time.LocalDate;

import com.senasoft.tikets.enums.GenderEnum;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PassengerRequestDTO {

    @NonNull
    private Long bookingId;

    @Past
    private LocalDate birth;

    @NotBlank
    private GenderEnum gender;

    @NonNull
    private boolean infant;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;

    @Positive
    private Long seatNumber;

}
