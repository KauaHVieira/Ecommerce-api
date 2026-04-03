package com.kauahv.Mini_ECommerceAPI.dto;

import com.kauahv.Mini_ECommerceAPI.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    private String name;
    private String description;
    private BigDecimal price;
    private UUID categoryId;
    private String imageURL;

}
