package com.senasoft.tikets.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class TiketResponseDTO {
    
    private Long id;

    private String name;

    private Long seatNumber;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    private Double totalPrice;
}
