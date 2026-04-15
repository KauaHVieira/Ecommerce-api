package com.kauahv.Mini_ECommerceAPI.dto;

import com.kauahv.Mini_ECommerceAPI.domain.Payment;
import com.kauahv.Mini_ECommerceAPI.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class OrderResponseDTO {

    private UUID id;
    private String clientName;
    private OrderStatus status;
    private Payment paymentType;
    private BigDecimal totalPrice;
    private Instant createdAt;
    private List<OrderItemResponseDTO> items;

}
