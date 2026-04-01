package com.kauahv.Mini_ECommerceAPI.services;

import com.kauahv.Mini_ECommerceAPI.domain.Product;
import com.kauahv.Mini_ECommerceAPI.exception.ResourceNotFoundException;
import com.kauahv.Mini_ECommerceAPI.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(UUID id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
    }

    public Product insert(Product obj){
        return productRepository.save(obj);
    }

    public void delete(UUID id){
        if(!productRepository.existsById(id)){
            throw new ResourceNotFoundException("Product not found!");
        }
        productRepository.deleteById(id);
    }

    public Product update(UUID id, Product obj){
        Product Product = findById(id);
        updateData(Product, obj);
        return productRepository.save(Product);
    }

    public void updateData(Product Product, Product obj){
        if(obj.getName() != null){
            Product.setName(obj.getName());
        }
        if(obj.getDescription() != null){
            Product.setDescription(obj.getDescription());
        }
        if(obj.getPrice() != null){
            Product.setPrice(obj.getPrice());
        }
        if(obj.getCategory() != null){
            Product.setCategory(obj.getCategory());
        }
        if(obj.getImageURL() != null){
            Product.setImageURL(obj.getImageURL());
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
