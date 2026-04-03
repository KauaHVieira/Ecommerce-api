package com.kauahv.Mini_ECommerceAPI.controllers;

import com.kauahv.Mini_ECommerceAPI.domain.Product;
import com.kauahv.Mini_ECommerceAPI.dto.ProductRequestDTO;
import com.kauahv.Mini_ECommerceAPI.dto.ProductResponseDTO;
import com.kauahv.Mini_ECommerceAPI.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAllProducts(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable UUID id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> insert(@RequestBody ProductRequestDTO obj){
        ProductResponseDTO created = productService.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable UUID id, @RequestBody ProductRequestDTO obj){
        return ResponseEntity.ok(productService.update(id, obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
