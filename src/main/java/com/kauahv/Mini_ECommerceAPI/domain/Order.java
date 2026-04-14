package com.kauahv.Mini_ECommerceAPI.domain;

import com.kauahv.Mini_ECommerceAPI.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @UuidGenerator
    @EqualsAndHashCode.Include
    private UUID id;
    private Instant moment;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    //private Payment payment;
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    public BigDecimal getTotalPrice(){
        return items.stream()
                .map(OrderItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
