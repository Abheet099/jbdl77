package com.example.product_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @PostMapping
    ResponseEntity<Product> saveProduct(@RequestBody Product product){
        logger.info("In product app: ProductController.saveProduct with product: {}",product);
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @GetMapping("/{productId}")
    ResponseEntity<Product> getProductById(@PathVariable long productId){
        logger.info("In product app: ProductController.getProductById with productId: {}",productId);
        return ResponseEntity.ok(productService.getProduct(productId));
    }
}
