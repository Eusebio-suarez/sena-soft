package com.senasoft.tikets.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingRequetDTO {
    
    @NotNull
    private Long flightId;
}
