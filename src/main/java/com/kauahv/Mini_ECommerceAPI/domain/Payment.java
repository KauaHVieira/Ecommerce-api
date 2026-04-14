package com.kauahv.Mini_ECommerceAPI.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kauahv.Mini_ECommerceAPI.enums.PaymentStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    private UUID id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @OneToOne
    @MapsId
    private Order order;
}
