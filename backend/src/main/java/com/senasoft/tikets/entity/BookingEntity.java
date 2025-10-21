package com.senasoft.tikets.entity;


import java.util.List;


import com.senasoft.tikets.enums.BookingStatusEnum;
import com.senasoft.tikets.enums.TypeEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    private TypeEnum type;

    private Double totalPrice;

    private BookingStatusEnum status;

    @ManyToOne
    @JoinColumn(name="buyer_id",nullable=false)
    private BuyerEntity buyer;

    @ManyToOne
    @JoinColumn(name="flight_id", nullable=false)
    private FlightEntity flight;

    @OneToMany(mappedBy="booking")
    private List<PassengerEntity> passengenrs;
}
