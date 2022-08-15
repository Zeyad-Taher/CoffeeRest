package com.example.coffeerest.service;

import com.example.coffeerest.Entity.Product;
import com.example.coffeerest.repository.ProductRepository;
import com.example.coffeerest.utility.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.util.StringUtils.cleanPath;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public String addImage(MultipartFile image){
        String fileName = cleanPath(image.getOriginalFilename());
        Product product=productRepository.findTopByOrderByIdDesc();
        String uploadDir;
        if(product==null)
            uploadDir = "product-images/" + 1;
        else
            uploadDir = "product-images/" + (product.getId()+1);
        if (!FileUploadUtil.saveFile(uploadDir, fileName, image)){
            return null;
        }
        Path upPath = Paths.get(uploadDir);
        return upPath.resolve(fileName).toString();
    }

    public Resource getImage(String path) {
        try {
            Path file = Paths.get(path);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public boolean deleteProduct(Long id){
        productRepository.deleteById(id);
        return true;
    }
}
