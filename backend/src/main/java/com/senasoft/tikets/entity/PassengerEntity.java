package com.senasoft.tikets.entity;

import java.time.LocalDate;

import com.senasoft.tikets.enums.DocEnum;
import com.senasoft.tikets.enums.GenderEnum;
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
@Table(name="passengers")
public class PassengerEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String numberDoc;

    private DocEnum docType;

    private LocalDate birth;

    private GenderEnum gender;

    private boolean infant;

    private String phone;

    private String email;

    private Long seatNumber;

    private SeatStatusEnum seatStatus;

    @ManyToOne
    @JoinColumn(name="booking_id")
    private BookingEntity booking;

    @OneToOne(mappedBy="passenger")
    private TiketEntity tiket;
}
