package com.kauahv.Mini_ECommerceAPI.dto;

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
    private Integer quantity;
}
