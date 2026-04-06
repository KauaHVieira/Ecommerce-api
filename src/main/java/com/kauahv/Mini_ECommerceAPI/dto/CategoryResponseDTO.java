package com.kauahv.Mini_ECommerceAPI.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CategoryResponseDTO {

    private UUID id;
    private String name;
    private String description;
    private String categoryParentName;

}
