package com.kauahv.Mini_ECommerceAPI.category.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
