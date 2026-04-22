package com.kauahv.Mini_ECommerceAPI.controllers;

import com.kauahv.Mini_ECommerceAPI.dto.OrderItemRequestDTO;
import com.kauahv.Mini_ECommerceAPI.dto.OrderRequestDTO;
import com.kauahv.Mini_ECommerceAPI.dto.OrderResponseDTO;
import com.kauahv.Mini_ECommerceAPI.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAllOrders(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable UUID id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> insert(@RequestBody OrderRequestDTO obj){
        OrderResponseDTO created = orderService.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{id}/items")
    public ResponseEntity<OrderResponseDTO> updateOrderItems(@PathVariable UUID id, @RequestBody OrderRequestDTO dto){
        return ResponseEntity.ok(orderService.updateOrderItems(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{orderId}/items/{productId}")
    public ResponseEntity<Void> removeItem(@PathVariable UUID orderId, @PathVariable UUID productId){
        orderService.removeOrderItem(orderId, productId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{orderId}/items")
    public ResponseEntity<OrderResponseDTO> addItem(@PathVariable UUID orderId, @RequestBody OrderItemRequestDTO dto){
        OrderResponseDTO response = orderService.addOrderItem(orderId, dto);
        return ResponseEntity.ok(response);
    }

}
