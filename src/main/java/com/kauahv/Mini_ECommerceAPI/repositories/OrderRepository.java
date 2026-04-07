package com.kauahv.Mini_ECommerceAPI.repositories;

import com.kauahv.Mini_ECommerceAPI.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
