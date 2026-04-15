package com.kauahv.Mini_ECommerceAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequestDTO {

    private  UUID productId;
    private Integer quantity;

}
