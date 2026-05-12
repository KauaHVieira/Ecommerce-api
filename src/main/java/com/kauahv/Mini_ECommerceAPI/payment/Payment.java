package com.kauahv.Mini_ECommerceAPI.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kauahv.Mini_ECommerceAPI.order.Order;
import com.kauahv.Mini_ECommerceAPI.payment.enums.PaymentStatus;
import com.kauahv.Mini_ECommerceAPI.payment.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment {

    @Id
    private UUID id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @OneToOne
    @MapsId
    @JoinColumn(name = "order_id")
    private Order order;

    public abstract PaymentType getType();

}
