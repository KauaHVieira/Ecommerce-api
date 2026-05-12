package com.kauahv.Mini_ECommerceAPI.order.dto;

import com.kauahv.Mini_ECommerceAPI.payment.dto.PaymentResponseDTO;
import com.kauahv.Mini_ECommerceAPI.order.enums.OrderStatus;
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
    private PaymentResponseDTO payment;
    private BigDecimal totalPrice;
    private Instant createdAt;
    private List<OrderItemResponseDTO> items;

}
