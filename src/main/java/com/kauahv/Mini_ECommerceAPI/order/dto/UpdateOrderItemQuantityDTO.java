package com.kauahv.Mini_ECommerceAPI.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderItemQuantityDTO {

    @NotNull
    @Min(1)
    private Integer quantity;
}
