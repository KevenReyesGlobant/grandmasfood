package org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.grandmasfood.springcloud.products.application.ports.input.ProductServicesPort;
import org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.generated.GeneratedPdfBox;
import org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.mapper.ProductRestMapper;
import org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.model.request.ProductsCreateRequestDTO;
import org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.model.response.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ProductsController {
    private final ProductServicesPort productServicesPort;
    private final ProductRestMapper productRestMapper;
    private final GeneratedPdfBox generatedPdfBox;


    public ProductsController(ProductServicesPort productServicesPort, ProductRestMapper productRestMapper, GeneratedPdfBox generatedPdfBox) {
        this.productServicesPort = productServicesPort;
        this.productRestMapper = productRestMapper;
        this.generatedPdfBox = generatedPdfBox;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductsCreateRequestDTO productsCreateRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productRestMapper.toProductResponseDTO(productServicesPort.save(productRestMapper.toProduct(productsCreateRequestDTO))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> readProductActiveById(@PathVariable @Valid Long id) {
        return ResponseEntity.ok(productRestMapper.toProductResponseDTO(productServicesPort.findActiveById(id)));
    }

    @GetMapping("/product/menu")
    public ResponseEntity<ProductResponse> generatedMenu() throws IOException {
        try {
            generatedPdfBox.saveDocument();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<ProductResponse>> findProductActiveByFantasyName(
            @RequestParam(name = "q") @Valid @NotBlank String fantasyName) {
        List<ProductResponse> products = productServicesPort.findByFantasyName(fantasyName).stream()
                .map(productRestMapper::toProductResponseDTO)
                .sorted(Comparator.comparing(ProductResponse::getFantasyName))
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }


    @GetMapping("/product/{uuid}")
    public ResponseEntity<ProductResponse> findProductActiveByUuid(@PathVariable @Valid UUID uuid) {
        return ResponseEntity.ok(productRestMapper.toProductResponseDTO(productServicesPort.findActiveByUuid(uuid)));
    }


    @PutMapping("/product/{uuid}")
    public ResponseEntity<ProductResponse> updateDataProductByUuid(@PathVariable @Valid UUID uuid, @RequestBody @Valid ProductsCreateRequestDTO productsCreateRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(productRestMapper.toProductResponseDTO(productServicesPort.update(uuid, productRestMapper.toProduct(productsCreateRequestDTO))));
    }

    @DeleteMapping("/product/{uuid}")
    public ResponseEntity<ProductResponse> softDeleteProductByUuid(@PathVariable @Valid UUID uuid) {
        ProductResponse productResponse = productRestMapper.toProductResponseDTO(productServicesPort.deleteByUuid(uuid));
        return ResponseEntity.ok().build();
    }
}
