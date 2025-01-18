package com.example.product_app;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private AtomicLong nextId = new AtomicLong(0);
    private Map<Long, Product> productMap = new HashMap<>();

    public Product saveProduct(Product product) {
        product.setId(nextId.incrementAndGet());
        productMap.put(product.getId(), product);
        return product;
    }

    public Product getProduct(Long id) {
        return productMap.get(id);
    }
}
