package com.kauahv.Mini_ECommerceAPI.mapper;

import com.kauahv.Mini_ECommerceAPI.domain.Product;
import com.kauahv.Mini_ECommerceAPI.dto.ProductRequestDTO;
import com.kauahv.Mini_ECommerceAPI.dto.ProductResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequestDTO dto){
        Product obj = new Product();
        obj.setName(dto.getName());
        obj.setDescription(dto.getDescription());
        obj.setPrice(dto.getPrice());
        obj.setImageURL(dto.getImageURL());

        return obj;
    }

    public ProductResponseDTO toDto(Product obj){
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(obj.getId());
        dto.setName(obj.getName());
        dto.setDescription(obj.getDescription());
        dto.setPrice(obj.getPrice());
        dto.setStock(obj.getStock());
        if(obj.getCategory() != null){
            dto.setCategoryName(obj.getCategory().getName());
        }
        dto.setImageURL(obj.getImageURL());

        return dto;
    }

    public List<ProductResponseDTO> toDtoList(List<Product> products){
        return products.stream().map(this::toDto).toList();
    }
}
