package com.example.coffeerest.controller;

import com.example.coffeerest.Entity.Product;
import com.example.coffeerest.exception.ErrorResponse;
import com.example.coffeerest.exception.Errors;
import com.example.coffeerest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/product/v1")
public class ProductController {

    @Autowired private ProductService productService;

    @GetMapping(value = "/get/products")
    public Iterable<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @PostMapping(value = "/add/product")
    public ResponseEntity<?> addProduct(@RequestBody(required=false) Product product){
        return productService.addProduct(product);
    }

    @PostMapping(value = "/add/image")
    public ResponseEntity<?> addImage(@ModelAttribute MultipartFile image){
        return productService.addImage(image);
    }

    @GetMapping(value = "/get/image/",produces = {MediaType.IMAGE_PNG_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getImage(@RequestBody(required=false) String path) {
        if(path==null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return productService.getImage(path);
        }
    }


    @DeleteMapping(value = "/del/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductByID(@PathVariable Long id){
        return productService.getProductByID(id);
    }
}
