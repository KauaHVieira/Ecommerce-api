package com.kauahv.Mini_ECommerceAPI.order.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class OrderItemResponseDTO {

    private UUID productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;

}
