package com.kauahv.Mini_ECommerceAPI.services;

import com.kauahv.Mini_ECommerceAPI.domain.Order;
import com.kauahv.Mini_ECommerceAPI.domain.OrderItem;
import com.kauahv.Mini_ECommerceAPI.domain.Product;
import com.kauahv.Mini_ECommerceAPI.dto.OrderItemRequestDTO;
import com.kauahv.Mini_ECommerceAPI.dto.OrderRequestDTO;
import com.kauahv.Mini_ECommerceAPI.dto.OrderResponseDTO;
import com.kauahv.Mini_ECommerceAPI.enums.OrderStatus;
import com.kauahv.Mini_ECommerceAPI.exception.ResourceNotFoundException;
import com.kauahv.Mini_ECommerceAPI.mapper.OrderMapper;
import com.kauahv.Mini_ECommerceAPI.repositories.OrderRepository;
import com.kauahv.Mini_ECommerceAPI.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    private final PaymentService paymentService;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderMapper orderMapper, PaymentService paymentService){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
        this.paymentService = paymentService;
    }

    public List<OrderResponseDTO> findAll(){
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toDtoList(orders);
    }

    public OrderResponseDTO findById(UUID id){
        Order obj = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found!"));
        return orderMapper.toDto(obj);
    }

    public OrderResponseDTO insert(OrderRequestDTO obj){
        Order order = orderMapper.toEntity(obj);
        order.setOrderStatus(OrderStatus.WAITING_PAYMENT);
        Set<OrderItem> items = obj.getItems().stream()
                .map(itemDTO -> createOrderItem(itemDTO, order))
                .collect(Collectors.toSet());
        order.getItems().addAll(items);
        Order savedOrder = orderRepository.save(order);

        return orderMapper.toDto(savedOrder);
    }

    public OrderItem createOrderItem(OrderItemRequestDTO dto, Order order){
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(dto.getQuantity());
        item.setPrice(product.getPrice());

        return item;
    }

    public void delete(UUID id){
        if(!orderRepository.existsById(id)){
            throw new ResourceNotFoundException("Order not found!");
        }
        orderRepository.deleteById(id);
    }

    public OrderResponseDTO updateOrderItems(UUID id, OrderRequestDTO dto){
        Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found!"));

        if(order.getOrderStatus() != OrderStatus.WAITING_PAYMENT){
            throw new IllegalStateException("Cannot modify order after payment!");
        }

        if(dto.getItems() == null || dto.getItems().isEmpty()){
            throw new IllegalArgumentException("Order must have at least one item!");
        }

        order.getItems().clear();

        Set<OrderItem> items = dto.getItems().stream()
                .map(itemDTO -> createOrderItem(itemDTO, order))
                .collect(Collectors.toSet());

        order.getItems().addAll(items);
        Order savedOrder = orderRepository.save(order);

        return orderMapper.toDto(savedOrder);
    }

}
