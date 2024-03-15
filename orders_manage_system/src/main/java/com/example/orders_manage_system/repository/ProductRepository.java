package com.example.orders_manage_system.repository;

import com.example.orders_manage_system.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
