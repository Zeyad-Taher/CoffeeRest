package com.example.coffeerest.controller;

import com.example.coffeerest.Entity.Product;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/product/v1/")
public class ProductController {
    //@Autowired private ProductService productService;

    @GetMapping(value = "/")
    public List<Product> getAllProduct(){
        //TODO

        //DUMMY
        Random rand = new Random();
        List <Product> ret = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            int randInt = rand.nextInt();
            Float randFloat = rand.nextFloat();

            randInt = randInt > 0 ? randInt : randInt * -1;
            randFloat = randFloat > 0 ? randFloat : randFloat * -1;

            randInt %= 1000;

            Product temp = new Product(
                    "name" + i,
                    randFloat,
                    "/static/coffee.jpg",
                    randFloat,
                    randFloat,
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    "red",
                    randInt,
                    "coffee"
            );
            ret.add(temp);
        }
        return ret;
    }

    @PostMapping(value = "/add")
    public Product addProduct(@RequestBody Product prod, @RequestParam("image") MultipartFile multipartFile){
        //TODO

        return prod;
    }

    @DeleteMapping(value = "/del/{id}")
    public boolean deleteProduct(@PathVariable Long id){
        //TODO
        return true;
    }
}
