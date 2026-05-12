package com.kauahv.Mini_ECommerceAPI.order.dto;

import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private  UUID productId;
    @NotNull
    private Integer quantity;

}
