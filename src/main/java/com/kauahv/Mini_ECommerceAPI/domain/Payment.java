package com.kauahv.Mini_ECommerceAPI.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kauahv.Mini_ECommerceAPI.enums.PaymentStatus;
import com.kauahv.Mini_ECommerceAPI.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

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
    private Order order;

    public abstract PaymentType getType();

}
