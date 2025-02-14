package org.grandmasfood.springcloud.products.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

    @GetMapping
    public ResponseEntity<?> message(){
        return ResponseEntity.ok("Hello from Products");
    }
}
