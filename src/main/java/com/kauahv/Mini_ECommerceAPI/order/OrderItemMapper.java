package com.kauahv.Mini_ECommerceAPI.order;

import com.kauahv.Mini_ECommerceAPI.order.dto.OrderItemRequestDTO;
import com.kauahv.Mini_ECommerceAPI.order.dto.OrderItemResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class OrderItemMapper {

    public OrderItem toEntity(OrderItemRequestDTO dto){
        OrderItem obj = new OrderItem();
        obj.setQuantity(dto.getQuantity());

        return obj;
    }

    public OrderItemResponseDTO toDto(OrderItem obj){
        OrderItemResponseDTO dto = new OrderItemResponseDTO();
        dto.setProductId(obj.getProduct().getId());
        dto.setProductName(obj.getProduct().getName());
        dto.setQuantity(obj.getQuantity());
        dto.setPrice(obj.getPrice());
        dto.setSubTotal(obj.getSubTotal());

        return dto;
    }

    public List<OrderItemResponseDTO> toDtoList(Set<OrderItem> orderItems){
        return orderItems.stream().map(this::toDto).toList();
    }
}
