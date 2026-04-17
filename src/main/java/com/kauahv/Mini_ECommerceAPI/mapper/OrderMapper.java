package com.kauahv.Mini_ECommerceAPI.mapper;

import com.kauahv.Mini_ECommerceAPI.domain.Order;
import com.kauahv.Mini_ECommerceAPI.dto.OrderRequestDTO;
import com.kauahv.Mini_ECommerceAPI.dto.OrderResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    private final PaymentMapper paymentMapper;
    private final OrderItemMapper orderItemMapper;

    public OrderMapper(PaymentMapper paymentMapper, OrderItemMapper orderItemMapper){
        this.paymentMapper = paymentMapper;
        this.orderItemMapper = orderItemMapper;
    }

    public Order toEntity(OrderRequestDTO dto){
        Order obj = new Order();
        return obj;
    }

    public OrderResponseDTO toDto(Order obj){
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(obj.getId());
        dto.setClientName(obj.getClient().getName());
        dto.setStatus(obj.getOrderStatus());
        dto.setPayment(paymentMapper.toDto(obj.getPayment()));
        dto.setTotalPrice(obj.getTotalPrice());
        dto.setCreatedAt(obj.getMoment());
        dto.setItems(orderItemMapper.toDtoList(obj.getItems()));

        return dto;
    }

    public List<OrderResponseDTO> toDtoList(List<Order> orders){
        return orders.stream().map(this::toDto).toList();
    }
}
