package com.example.coffeerest.controller;

import com.example.coffeerest.Entity.Product;
import com.example.coffeerest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller class for product related endpoints
 */
@RestController
@CrossOrigin
@RequestMapping("/product/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Endpoint that returns list of all products
     *
     * @return Iterable that contains all Product objects
     */
    @GetMapping(value = "/get/products")
    public Iterable<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    /**
     * Endpoint that adds a new product
     *
     * @param product Product object to be added
     * @return ResponseEntity containing either Product object or an Error object indicating a missing parameter (product == null)
     */
    @PostMapping(value = "/add/product")
    public ResponseEntity<?> addProduct(@RequestBody(required = false) Product product) {
        return productService.addProduct(product);
    }

    /**
     * Endpoint that uploads an image to the server's local storage
     *
     * @param image MultipartFile to be added to the local storage
     * @return ResponseEntity containing either String with the local path to the uploaded image or an Error indicating failure to upload
     */
    @PostMapping(value = "/add/image")
    public ResponseEntity<?> addImage(@ModelAttribute MultipartFile image) {
        return productService.addImage(image);
    }


    /**
     * Endpoint that returns an image from server's local storage
     *
     * @param path String that contains the path of the image file to be returned
     * @return ResponseEntity containing either a Resource object with the image file or an Error indicating that the requested file is corrupted or resource not found
     */
    @GetMapping(value = "/get/image/", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getImage(@RequestBody(required = false) String path) {
        if (path == null) {
            return ResponseEntity.notFound().build();
        } else {
            return productService.getImage(path);
        }
    }

    /**
     * Endpoint that deletes a product by id
     *
     * @param id id of the product to be deleted
     * @return ResponseEntity containing either true value indicating successful deletion or an Error indicating product ID is not found
     */
    @DeleteMapping(value = "/del/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }


    /**
     * Endpoint that returns a product by id
     *
     * @param id id of the product to be found
     * @return ResponseEntity containing the requested Product or an Error indicating that product ID is not found
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductByID(@PathVariable Long id) {
        return productService.getProductByID(id);
    }
}
