package com.lefortdesigns.furnitarium_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lefortdesigns.furnitarium_backend.models.Category;
import com.lefortdesigns.furnitarium_backend.repos.CategoryRepository;

@Service
public class CategoriesService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoriesService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCats() {

        return categoryRepository.findAll();
    }
}
