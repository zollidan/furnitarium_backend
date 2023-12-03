package com.lefortdesigns.furnitarium_backend.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lefortdesigns.furnitarium_backend.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(int id);
}
