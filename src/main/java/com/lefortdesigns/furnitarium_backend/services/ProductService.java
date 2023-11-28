package com.lefortdesigns.furnitarium_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lefortdesigns.furnitarium_backend.models.Product;
import com.lefortdesigns.furnitarium_backend.repos.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> allProducts() {

        return productRepository.findAll();
    }

    public List<Product> allInCategoryProducts(int id) {

        return productRepository.findByCategoryId(id);
    }

    public Optional<Product> findOneProduct(Long prod_id) {


        return productRepository.findById(prod_id);
    }

}
