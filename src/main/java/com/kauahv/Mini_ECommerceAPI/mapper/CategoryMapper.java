package com.kauahv.Mini_ECommerceAPI.mapper;

import com.kauahv.Mini_ECommerceAPI.domain.Category;
import com.kauahv.Mini_ECommerceAPI.dto.CategoryRequestDTO;
import com.kauahv.Mini_ECommerceAPI.dto.CategoryResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequestDTO dto){
        Category obj = new Category();
        obj.setName(dto.getName());
        obj.setDescription(dto.getDescription());

        return obj;
    }

    public CategoryResponseDTO toDto(Category obj){
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(obj.getId());
        dto.setName(obj.getName());
        dto.setDescription(obj.getDescription());
        if(obj.getCategoryParent() != null){
            dto.setCategoryParentName(obj.getCategoryParent().getName());
        }

        return dto;
    }

    public List<CategoryResponseDTO> toDtoList(List<Category> Categories){
        return Categories.stream().map(this::toDto).toList();
    }
}
