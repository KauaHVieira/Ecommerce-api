package com.kauahv.Mini_ECommerceAPI.services;

import com.kauahv.Mini_ECommerceAPI.domain.Category;
import com.kauahv.Mini_ECommerceAPI.exception.ResourceNotFoundException;
import com.kauahv.Mini_ECommerceAPI.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(UUID id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found!"));
    }

    public Category insert(Category obj){
        return categoryRepository.save(obj);
    }

    public void delete(UUID id){
        if(!categoryRepository.existsById(id)){
            throw new ResourceNotFoundException("Category not found!");
        }
        categoryRepository.deleteById(id);
    }

    public Category update(UUID id, Category obj){
        Category Category = findById(id);
        updateData(Category, obj);
        return categoryRepository.save(Category);
    }

    public void updateData(Category Category, Category obj){
        if(obj.getName() != null){
            Category.setName(obj.getName());
        }
        if(obj.getDescription() != null){
            Category.setDescription(obj.getDescription());
        }
    }
}
