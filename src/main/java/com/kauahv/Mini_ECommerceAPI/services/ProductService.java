package com.kauahv.Mini_ECommerceAPI.services;

import com.kauahv.Mini_ECommerceAPI.domain.Category;
import com.kauahv.Mini_ECommerceAPI.domain.Product;
import com.kauahv.Mini_ECommerceAPI.dto.ProductRequestDTO;
import com.kauahv.Mini_ECommerceAPI.dto.ProductResponseDTO;
import com.kauahv.Mini_ECommerceAPI.exception.ResourceNotFoundException;
import com.kauahv.Mini_ECommerceAPI.mapper.ProductMapper;
import com.kauahv.Mini_ECommerceAPI.repositories.CategoryRepository;
import com.kauahv.Mini_ECommerceAPI.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    public List<ProductResponseDTO> findAll(){
        List<Product> products = productRepository.findAll();
        return productMapper.toDtoList(products);
    }

    public ProductResponseDTO findById(UUID id){
        Product obj = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
        return productMapper.toDto(obj);
    }

    public ProductResponseDTO insert(ProductRequestDTO obj){
        Category category = categoryRepository.findById(obj.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found!"));
        Product product = productMapper.toEntity(obj);
        product.setCategory(category);
        product.setStock(0);

        product = productRepository.save(product);

        return productMapper.toDto(product);
    }

    public void delete(UUID id){
        if(!productRepository.existsById(id)){
            throw new ResourceNotFoundException("Product not found!");
        }
        productRepository.deleteById(id);
    }

    public ProductResponseDTO update(UUID id, ProductRequestDTO obj){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found!"));
        updateData(product, obj);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    public void updateData(Product product, ProductRequestDTO obj){
        if(obj.getName() != null){
            product.setName(obj.getName());
        }
        if(obj.getDescription() != null){
            product.setDescription(obj.getDescription());
        }
        if(obj.getPrice() != null){
            product.setPrice(obj.getPrice());
        }
        if(obj.getCategoryId() != null && (product.getCategory() == null || !product.getCategory().getId().equals(obj.getCategoryId()))){
            Category category = categoryRepository.findById(obj.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found!"));
            product.setCategory(category);
        }
        if(obj.getImageURL() != null){
            product.setImageURL(obj.getImageURL());
        }
    }

    @Transactional
    public void addStock(Product obj, int quantity){
        obj.setStock(obj.getStock() + quantity);
        productRepository.save(obj);
    }

    @Transactional
    public void removeStock(Product obj, int quantity){
        if(obj.getStock() < quantity){
            throw new RuntimeException("Stock insufficient!");
        }
        obj.setStock(obj.getStock() - quantity);
        productRepository.save(obj);
    }
}
