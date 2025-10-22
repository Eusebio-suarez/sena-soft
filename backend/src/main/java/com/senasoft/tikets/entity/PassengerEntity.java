package com.senasoft.tikets.entity;

import java.time.LocalDate;

import com.senasoft.tikets.enums.GenderEnum;

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
@Table(name="passengers")
public class PassengerEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private LocalDate birth;

    private GenderEnum gender;

    private boolean infant;

    private String phone;

    private String email;

    @ManyToOne
    @JoinColumn(name="booking_id")
    private BookingEntity booking;

    @OneToOne(mappedBy="passenger")
    private SeatEntity seat;

    @OneToOne(mappedBy="passenger")
    private TiketEntity tiket;
}
