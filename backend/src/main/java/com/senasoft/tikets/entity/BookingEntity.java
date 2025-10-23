package com.senasoft.tikets.entity;


import java.time.LocalDate;
import java.util.List;

import com.senasoft.tikets.enums.StatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="bookings")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private Double totalPrice;

    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name="flight_id", nullable=false)
    private FlightEntity flight;

    @OneToMany(mappedBy="booking")
    private List<PassengerEntity> passengers;

    @OneToMany(mappedBy="booking")
    private List<TiketEntity> tikets;

    @OneToOne
    @JoinColumn(name="payment_id")
    private PaymentEntity payment;

}