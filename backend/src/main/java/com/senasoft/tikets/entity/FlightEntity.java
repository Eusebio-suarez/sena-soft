package com.senasoft.tikets.entity;

import java.time.LocalDateTime;
import java.util.List;

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
@Table(name="flights")
public class FlightEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String origin;

    private String destination;

    private LocalDateTime date;

    private Double price;

    @ManyToOne
    @JoinColumn(name="plane_id")
    private PlaneEntity plane;

    @OneToMany(mappedBy="flight")
    private List<BookingEntity> bookings;

    @OneToMany(mappedBy="flight")
    private List<SeatEntity> seats;
}
