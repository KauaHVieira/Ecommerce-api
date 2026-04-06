package com.kauahv.Mini_ECommerceAPI.services;

import com.kauahv.Mini_ECommerceAPI.domain.Category;
import com.kauahv.Mini_ECommerceAPI.dto.CategoryRequestDTO;
import com.kauahv.Mini_ECommerceAPI.dto.CategoryResponseDTO;
import com.kauahv.Mini_ECommerceAPI.exception.ResourceNotFoundException;
import com.kauahv.Mini_ECommerceAPI.mapper.CategoryMapper;
import com.kauahv.Mini_ECommerceAPI.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper){
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryResponseDTO> findAll(){
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toDtoList(categories);
    }

    public CategoryResponseDTO findById(UUID id){
        Category obj = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found!"));
        return categoryMapper.toDto(obj);
    }

    public CategoryResponseDTO insert(CategoryRequestDTO obj){
        Category category = categoryMapper.toEntity(obj);
        if(obj.getCategoryParentId() != null){
            Category parent = categoryRepository.findById(obj.getCategoryParentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent category not found!"));
            category.setCategoryParent(parent);
        }
        else{
            category.setCategoryParent(null);
        };
        category = categoryRepository.save(category);

        return categoryMapper.toDto(category);
    }

    public void delete(UUID id){
        if(!categoryRepository.existsById(id)){
            throw new ResourceNotFoundException("Category not found!");
        }
        categoryRepository.deleteById(id);
    }

    public CategoryResponseDTO update(UUID id, CategoryRequestDTO obj){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found!"));
        updateData(category, obj);
        category = categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    public void updateData(Category category, CategoryRequestDTO obj){
        if(obj.getName() != null){
            category.setName(obj.getName());
        }
        if(obj.getDescription() != null){
            category.setDescription(obj.getDescription());
        }
        if(obj.getCategoryParentId() != null){
            Category cat = categoryRepository.findById(obj.getCategoryParentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found!"));
            category.setCategoryParent(cat);
        }
    }
}
