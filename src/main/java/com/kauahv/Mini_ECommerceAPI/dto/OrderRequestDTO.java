package com.kauahv.Mini_ECommerceAPI.dto;

import com.kauahv.Mini_ECommerceAPI.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    private UUID client;
    private List<OrderItemRequestDTO> items;

}
