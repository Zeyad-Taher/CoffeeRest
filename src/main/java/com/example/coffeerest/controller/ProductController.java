package com.example.coffeerest.controller;

import com.example.coffeerest.Entity.Product;
import com.example.coffeerest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;


@RestController
@RequestMapping("/product/v1")
public class ProductController {

    @Autowired private ProductService productService;

    @GetMapping(value = "/get/products")
    public Iterable<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @PostMapping(value = "/add/product")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PostMapping(value = "/add/image")
    public String addImage(@ModelAttribute MultipartFile image){
        return productService.addImage(image);
    }

    @GetMapping(value = "/get/image/", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getImage(@RequestBody String path) throws IOException {
        Resource file = productService.getImage(path);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @DeleteMapping(value = "/del/{id}")
    public boolean deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
}
