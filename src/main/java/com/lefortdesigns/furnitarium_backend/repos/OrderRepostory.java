package com.lefortdesigns.furnitarium_backend.repos;

import com.lefortdesigns.furnitarium_backend.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepostory extends JpaRepository<Order, Long> {
}