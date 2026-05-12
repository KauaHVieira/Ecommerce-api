package com.kauahv.Mini_ECommerceAPI.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByClientId(UUID clientId);
    Optional<Order> findByIdAndClientId(UUID id, UUID clientId);
}
