package com.example.user_app;

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
@RequestMapping("/user")
public class UserProductController {

//    @Autowired
//    RestTemplate restTemplate;

//    @Value("${product.app.url}")
//    private String productAppUrl;

    @Autowired
    ProductFeignClient productFeignClient;

    private static Logger logger = LoggerFactory.getLogger(UserProductController.class);


    // Using Rest Template
//    @PostMapping
//    ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) {
//        logger.info("In user app: UserProductController.saveProduct with productDTO: {}", productDTO);
//        String url = productAppUrl + "product";
//
//        return restTemplate.postForEntity(url, productDTO, ProductDTO.class);
//    }
//
//    @GetMapping("/{productId}")
//    ResponseEntity<ProductDTO> getProductById(@PathVariable long productId) {
//        logger.info("In user app: UserProductController.getProductById with productId: {}", productId);
//        String url = productAppUrl + "product/"+ productId;
//        ProductDTO productDTO = restTemplate.getForObject(url, ProductDTO.class);
//
//        return ResponseEntity.ok(productDTO);
//    }

    // Using Feign Client
    @PostMapping
    ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) {
        logger.info("In user app: UserProductController.saveProduct with productDTO: {}", productDTO);

        return ResponseEntity.ok(productFeignClient.saveProduct(productDTO));
    }

    @GetMapping("/{productId}")
    ResponseEntity<ProductDTO> getProductById(@PathVariable long productId) {
        logger.info("In user app: UserProductController.getProductById with productId: {}", productId);

        return ResponseEntity.ok(productFeignClient.getProductById(productId));
    }
}
