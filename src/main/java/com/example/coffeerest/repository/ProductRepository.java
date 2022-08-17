package com.example.coffeerest.repository;

import com.example.coffeerest.Entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    /**
     * Used to get the last inserted product by ordering by ID int descending order and selecting the top tuple
     *
     * @return Last inserted Product object
     * */
    Product findTopByOrderByIdDesc();
}