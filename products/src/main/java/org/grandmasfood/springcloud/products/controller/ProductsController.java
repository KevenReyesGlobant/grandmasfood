package org.grandmasfood.springcloud.products.controller;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.products.model.dto.ProductsDTO;
import org.grandmasfood.springcloud.products.model.entity.Products;
import org.grandmasfood.springcloud.products.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @PostMapping("/product")
    public ResponseEntity<?> createProductRest(@RequestBody @Valid ProductsDTO productsDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return getErrors(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productsService.createProduct(productsDTO));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readProductActiveByID(@PathVariable @Valid Long id) {
        Optional<Products> product = productsService.readProductsById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/product/{uuid}")
    public ResponseEntity<?> ListProductActiveByUUID(@PathVariable @Valid UUID uuid) {
        try {
            Optional<Products> product = productsService.readProductsByUuId(uuid.compareTo(new UUID(0, 0)) == 0 ? null : uuid);

            if (product.isPresent()) {
                return ResponseEntity.ok(product.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format");
        }
    }

    @PutMapping("/product/{uuid}")
    public ResponseEntity<?> updateProductActiveByID(@RequestBody ProductsDTO productsDTO, @PathVariable @Valid UUID uuid) {
        try {
            Optional<Products> product = productsService.readProductsByUuId(uuid.compareTo(new UUID(0, 0)) == 0 ? null : uuid);

            if (product.isPresent()) {
                return ResponseEntity.ok(productsService.updateProduct(productsDTO, uuid));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format");
        }
    }
    @DeleteMapping("/product/{uuid}")
    public ResponseEntity<?> deleteProductActiveByID(@PathVariable @Valid UUID uuid) {
        try {
            Optional<Products> product = productsService.readProductsByUuId(uuid.compareTo(new UUID(0, 0)) == 0 ? null : uuid);

            if (product.isPresent()) {
                productsService.deleteProductsByUuId(uuid);
                return ResponseEntity.ok("Product deleted");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format");
        }
    }

    private ResponseEntity<?> getErrors(BindingResult bindingResult) {
        Map<String, String> error = new HashMap<>();

        bindingResult.getFieldErrors().forEach(e -> {
            error.put(e.getField(), "This field " + e.getField() + " is not empty or negative");
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
