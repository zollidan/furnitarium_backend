package com.lefortdesigns.furnitarium_backend.conrollers;

import com.lefortdesigns.furnitarium_backend.models.Category;
import com.lefortdesigns.furnitarium_backend.models.Product;
import com.lefortdesigns.furnitarium_backend.services.CategoriesService;
import com.lefortdesigns.furnitarium_backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductsController {

    private final CategoriesService categoriesService;
    private final ProductService productService;

    @Autowired
    public ProductsController(CategoriesService categoriesService, ProductService productService) {
        this.categoriesService = categoriesService;
        this.productService = productService;
    }

    @GetMapping("/aboba")
    ResponseEntity<String> hello() {
        return ResponseEntity.ok()
                .body("бомжик");
    }

    @GetMapping("/allItems")
    public List<Product> allProducts(){
        return productService.allProducts();
    }

    @GetMapping("/categories")
    public List<Category> allCategories(){
        return categoriesService.findAllCats();
    }

    @GetMapping("/{categoryId}/allItems")
    public List<Product> allInCatProducts(@PathVariable int categoryId){
        return productService.allInCategoryProducts(categoryId);
    }

    @GetMapping("/item/{proId}")
    public Optional<Product> allProducts(@PathVariable Long proId){
        return productService.findOneProduct(proId);
    }

}
