package org.example.restexam.controller;

import org.example.restexam.domain.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        long id = counter.incrementAndGet();
        product.setId(id);
        products.put(id,product);
        return product;
    }


    @GetMapping("/{id}")
    public Product getProduct(@PathVariable long id){
        return products.get(id);
    }

    @GetMapping
    public List<Product> getAllProduct(){
        return new ArrayList<>(products.values());
    }
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product product) {
        product.setId(id);
        products.put(id, product);
        return product;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
        products.remove(id);
    }

}
