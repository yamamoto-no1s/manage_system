package com.example.orders_manage_system.repository;

import com.example.orders_manage_system.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
