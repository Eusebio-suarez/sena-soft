package com.senasoft.tikets.entity;

import java.util.List;

import com.senasoft.tikets.enums.DocEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name="buyers")
public class BuyerEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private DocEnum docType;

    private String docNumber;

    private String name;

    private String phone;

    private String email;

    @OneToMany(mappedBy="buyer")
    private List<BookingEntity> bokings;

}
