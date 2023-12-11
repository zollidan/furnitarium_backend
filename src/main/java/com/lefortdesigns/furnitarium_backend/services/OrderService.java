package com.lefortdesigns.furnitarium_backend.services;

import com.lefortdesigns.furnitarium_backend.models.Order;
import com.lefortdesigns.furnitarium_backend.repos.OrderRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepostory orderRepostory;

    @Autowired
    public OrderService(OrderRepostory orderRepostory) {
        this.orderRepostory = orderRepostory;
    }

    public List<Order> showAllOrders() {
        return orderRepostory.findAll();
    }

    public Optional<Order> findOrderById(Long orderId) {
        return orderRepostory.findById(orderId);
    }
}
