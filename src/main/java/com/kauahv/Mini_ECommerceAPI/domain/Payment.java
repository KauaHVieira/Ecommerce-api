package com.kauahv.Mini_ECommerceAPI.domain;

import com.kauahv.Mini_ECommerceAPI.enums.PaymentStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    private UUID id;
    private Instant moment;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @OneToOne
    @MapsId
    private Order order;
}
