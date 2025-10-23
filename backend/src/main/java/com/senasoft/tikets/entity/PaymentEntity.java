package com.senasoft.tikets.entity;

import java.time.LocalDate;

import com.senasoft.tikets.enums.DocEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="payments")
public class PaymentEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private DocEnum docType;

    private String numberDoc;
    
    private Double monto;

    private String email;

    private String phone;

    private LocalDate date;

    @OneToOne(mappedBy="payment")
    private BookingEntity booking;
}
