package com.example.user_app;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PRODUCT-APP")
//@FeignClient(name="product-app-client",url = "${product.app.url}")
public interface ProductFeignClient {

    @PostMapping("/product")
    ProductDTO saveProduct(@RequestBody ProductDTO productDTO);

    @GetMapping("/product/{productId}")
    ProductDTO getProductById(@PathVariable long productId);
}
