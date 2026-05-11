package com.kauahv.Mini_ECommerceAPI.services;

import com.kauahv.Mini_ECommerceAPI.domain.Order;
import com.kauahv.Mini_ECommerceAPI.domain.OrderItem;
import com.kauahv.Mini_ECommerceAPI.domain.Product;
import com.kauahv.Mini_ECommerceAPI.domain.User;
import com.kauahv.Mini_ECommerceAPI.dto.OrderItemRequestDTO;
import com.kauahv.Mini_ECommerceAPI.dto.OrderRequestDTO;
import com.kauahv.Mini_ECommerceAPI.dto.OrderResponseDTO;
import com.kauahv.Mini_ECommerceAPI.enums.OrderStatus;
import com.kauahv.Mini_ECommerceAPI.exception.ResourceNotFoundException;
import com.kauahv.Mini_ECommerceAPI.mapper.OrderMapper;
import com.kauahv.Mini_ECommerceAPI.repositories.OrderRepository;
import com.kauahv.Mini_ECommerceAPI.repositories.ProductRepository;
import com.kauahv.Mini_ECommerceAPI.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderMapper orderMapper, PaymentService paymentService, UserRepository userRepository){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
        this.paymentService = paymentService;
        this.userRepository = userRepository;
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

    public List<OrderResponseDTO> findMyOrders(User user){
        return orderRepository.findByClientId(user.getId())
                .stream().map(orderMapper::toDto)
                .toList();
    }

    public OrderResponseDTO findMyOrder(UUID id, User user){
        Order order = orderRepository
                .findByIdAndClientId(id, user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found!"));
        return orderMapper.toDto(order);
    }

    public OrderResponseDTO insert(User user, OrderRequestDTO obj){
        Order order = new Order();
        order.setClient(user);
        order.setMoment(Instant.now());
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
        if (dto.getQuantity() == null || dto.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
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

    public void removeOrderItem(UUID orderId, UUID productId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found!"));
        if(order.getOrderStatus() != OrderStatus.WAITING_PAYMENT){
            throw new IllegalStateException("Cannot modify order after payment!");
        }
        if (order.getItems().isEmpty()) {
            throw new IllegalStateException("Order must have at least one item");
        }
        boolean removed = order.getItems()
                .removeIf(orderItem -> orderItem.getProduct().getId().equals(productId));
        if(!removed){
            throw new ResourceNotFoundException("OrderItem not found!");
        }

        orderRepository.save(order);
    }

    public OrderResponseDTO addOrderItem(UUID orderId, OrderItemRequestDTO dto){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found!"));
        if(order.getOrderStatus() != OrderStatus.WAITING_PAYMENT){
            throw new IllegalStateException("Cannot modify order after payment!");
        }

        OrderItem orderItem = order.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(dto.getProductId()))
                .findFirst()
                .orElse(null);
        if(orderItem != null){
            orderItem.setQuantity(orderItem.getQuantity() + dto.getQuantity());
        }
        else{
            order.getItems().add(createOrderItem(dto, order));
        }

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }

    public OrderResponseDTO updateQuantity(UUID orderId, UUID productId, Integer quantity){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found!"));
        if(order.getOrderStatus() != OrderStatus.WAITING_PAYMENT){
            throw new IllegalStateException("Cannot modify order after payment!");
        }
        if(quantity == null || quantity <= 0){
            throw new IllegalArgumentException("The quantity must be greater than 0!");
        }
        OrderItem orderItem = order.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Item not found!"));

        orderItem.setQuantity(quantity);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }

}
