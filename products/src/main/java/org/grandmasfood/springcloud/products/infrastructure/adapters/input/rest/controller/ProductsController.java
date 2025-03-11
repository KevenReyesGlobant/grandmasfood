package org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.controller;


import jakarta.validation.Valid;
import org.grandmasfood.springcloud.products.application.ports.input.ProductServicesPort;
import org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.mapper.ProductRestMapper;
import org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.model.request.ProductsCreateRequestDTO;
import org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.model.response.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductsController {
    private final ProductServicesPort productServicesPort;
    private final ProductRestMapper productRestMapper;

    public ProductsController(ProductServicesPort productServicesPort, ProductRestMapper productRestMapper) {
        this.productServicesPort = productServicesPort;
        this.productRestMapper = productRestMapper;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductsCreateRequestDTO productsCreateRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productRestMapper.toProductResponseDTO(productServicesPort.save(productRestMapper.toProduct(productsCreateRequestDTO))));

    }
}
