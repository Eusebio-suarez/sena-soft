package com.senasoft.tikets.entity;


import com.senasoft.tikets.enums.SeatStatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="seats")
public class SeatEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Long number;
    
    @ManyToOne
    @JoinColumn(name="flight_id")
    private FlightEntity flight;

    @OneToOne
    @JoinColumn(name="passenger_id")
    private PassengerEntity passenger;

    private SeatStatusEnum status;

}
