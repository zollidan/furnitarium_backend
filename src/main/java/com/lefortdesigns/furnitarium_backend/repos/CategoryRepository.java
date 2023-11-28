package com.lefortdesigns.furnitarium_backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lefortdesigns.furnitarium_backend.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
