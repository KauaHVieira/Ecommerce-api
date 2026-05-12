package com.kauahv.Mini_ECommerceAPI.order;

import com.kauahv.Mini_ECommerceAPI.payment.PaymentMapper;
import com.kauahv.Mini_ECommerceAPI.order.dto.OrderResponseDTO;
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

    public OrderResponseDTO toDto(Order obj){
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(obj.getId());
        dto.setClientName(obj.getClient().getName());
        dto.setStatus(obj.getOrderStatus());
        if (obj.getPayment() != null){
            dto.setPayment(paymentMapper.toDto(obj.getPayment()));
        }
        dto.setTotalPrice(obj.getTotalPrice());
        dto.setCreatedAt(obj.getMoment());
        dto.setItems(orderItemMapper.toDtoList(obj.getItems()));

        return dto;
    }

    public List<OrderResponseDTO> toDtoList(List<Order> orders){
        return orders.stream().map(this::toDto).toList();
    }
}
