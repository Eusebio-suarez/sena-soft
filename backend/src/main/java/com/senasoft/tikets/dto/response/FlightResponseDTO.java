package com.senasoft.tikets.dto.response;

import java.time.LocalDateTime;

import com.senasoft.tikets.enums.TypeEnum;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightResponseDTO {
    private Long id;

    private String origin;

    private String destination;

    private TypeEnum type;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    private Double price;
}
