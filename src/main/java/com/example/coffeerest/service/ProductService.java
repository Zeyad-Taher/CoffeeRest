package com.example.coffeerest.service;

import com.example.coffeerest.Entity.Product;
import com.example.coffeerest.exception.ErrorResponse;
import com.example.coffeerest.exception.Errors;
import com.example.coffeerest.repository.ProductRepository;
import com.example.coffeerest.utility.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.springframework.util.StringUtils.cleanPath;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public ResponseEntity<?> addProduct(Product product) {
        if(product==null) {
            return new ResponseEntity<>(new ErrorResponse(Errors.PRODUCT_IS_MISSING.getCode(),
                    Errors.PRODUCT_IS_MISSING.getMessage()), HttpStatus.BAD_REQUEST);
        }
        else {
            Product newProduct = productRepository.save(product);
            return new ResponseEntity<>(newProduct,HttpStatus.OK);
        }
    }

    public ResponseEntity<?> addImage(MultipartFile image){
        String fileName = cleanPath(image.getOriginalFilename());
        Product product=productRepository.findTopByOrderByIdDesc();
        String uploadDir;
        if(product==null)
            uploadDir = "product-images/" + 1;
        else
            uploadDir = "product-images/" + (product.getId()+1);
        if (!FileUploadUtil.saveFile(uploadDir, fileName, image)){
            return new ResponseEntity<>(new ErrorResponse(Errors.FAILED_TO_UPLOAD_IMAGE.getCode(),
                    Errors.FAILED_TO_UPLOAD_IMAGE.getMessage()), HttpStatus.BAD_REQUEST); //failed to upload image
        }
        Path upPath = Paths.get(uploadDir);
        return new ResponseEntity<>(upPath.resolve(fileName).toString(),HttpStatus.OK);
    }

    public ResponseEntity<?> getImage(String path) {
        try {

            Path file = Paths.get(path);
            Resource resource = new UrlResource(file.toUri());
            return new ResponseEntity<>(resource,HttpStatus.OK);
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("Image is corrupted"); //image is corrupted
        }
    }

    public ResponseEntity<?> deleteProduct(Long id){
        Optional<Product> product=productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ErrorResponse(Errors.PRODUCT_NOT_FOUND.getCode(),
                    Errors.PRODUCT_NOT_FOUND.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getProductByID(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return new ResponseEntity<>(product.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new ErrorResponse(Errors.PRODUCT_NOT_FOUND.getCode(),
                    Errors.PRODUCT_NOT_FOUND.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
