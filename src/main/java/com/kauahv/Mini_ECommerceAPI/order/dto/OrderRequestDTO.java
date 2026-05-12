package com.kauahv.Mini_ECommerceAPI.order.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    @NotEmpty
    private List<OrderItemRequestDTO> items;

}
