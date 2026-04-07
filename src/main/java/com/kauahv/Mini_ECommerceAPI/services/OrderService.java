package com.kauahv.Mini_ECommerceAPI.services;

import com.kauahv.Mini_ECommerceAPI.domain.Order;
import com.kauahv.Mini_ECommerceAPI.exception.ResourceNotFoundException;
import com.kauahv.Mini_ECommerceAPI.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(UUID id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found!"));
    }

    public Order insert(Order obj){
        return orderRepository.save(obj);
    }

    public void delete(UUID id){
        if(!orderRepository.existsById(id)){
            throw new ResourceNotFoundException("Order not found!");
        }
        orderRepository.deleteById(id);
    }

    public Order update(UUID id, Order obj){
        Order Order = findById(id);
        updateData(Order, obj);
        return orderRepository.save(Order);
    }

    public void updateData(Order Order, Order obj){
        if(obj.getOrderStatus() != null){
            Order.setOrderStatus(obj.getOrderStatus());
        }
    }
}
