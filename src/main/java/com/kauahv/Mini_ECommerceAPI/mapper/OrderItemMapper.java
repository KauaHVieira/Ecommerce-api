package com.kauahv.Mini_ECommerceAPI.mapper;

import com.kauahv.Mini_ECommerceAPI.domain.OrderItem;
import com.kauahv.Mini_ECommerceAPI.dto.OrderItemRequestDTO;
import com.kauahv.Mini_ECommerceAPI.dto.OrderItemResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
