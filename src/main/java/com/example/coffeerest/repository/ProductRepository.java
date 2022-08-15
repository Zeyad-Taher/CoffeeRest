package com.example.coffeerest.repository;

import com.example.coffeerest.Entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    public Product findTopByOrderByIdDesc();
}