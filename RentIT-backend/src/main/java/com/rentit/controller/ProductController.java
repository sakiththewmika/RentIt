package com.rentit.controller;

import com.rentit.model.Product;
import com.rentit.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // Add a new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return repository.save(product);
    }
}

