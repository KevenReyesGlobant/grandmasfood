package org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Products", description = "This microservice manages products, allowing for creation, updating, deletion, and querying by UUID or fantasy name, as well as generating a PDF menu.")
public class ProductsController {
    private final ProductServicesPort productServicesPort;
    private final ProductRestMapper productRestMapper;
    private final GeneratedPdfBox generatedPdfBox;


    public ProductsController(ProductServicesPort productServicesPort, ProductRestMapper productRestMapper, GeneratedPdfBox generatedPdfBox) {
        this.productServicesPort = productServicesPort;
        this.productRestMapper = productRestMapper;
        this.generatedPdfBox = generatedPdfBox;
    }

    @Operation(
            summary = "Create a new product",
            description = "Creates a product with the provided information"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/api/v1/product")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductsCreateRequestDTO productsCreateRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productRestMapper.toProductResponseDTO(productServicesPort.save(productRestMapper.toProduct(productsCreateRequestDTO))));
    }

    @Hidden
    @GetMapping("/api/v1/{id}")
    public ResponseEntity<ProductResponse> readProductActiveById(@PathVariable @Valid Long id) {
        return ResponseEntity.ok(productRestMapper.toProductResponseDTO(productServicesPort.findActiveById(id)));
    }


    @Operation(
            summary = "Generate product menu PDF",
            description = "Generates and saves a product menu in PDF format"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu PDF generated successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/api/v1/product/menu")
    public ResponseEntity<ProductResponse> generatedMenu() throws IOException {
        try {
            generatedPdfBox.saveDocument();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Search products by fantasy name",
            description = "Searches for products based on their fantasy name"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products found"),
            @ApiResponse(responseCode = "400", description = "Invalid search parameter"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/api/v1/product/search")
    public ResponseEntity<List<ProductResponse>> findProductActiveByFantasyName(
            @RequestParam(name = "q") @Valid @NotBlank String fantasyName) {
        List<ProductResponse> products = productServicesPort.findByFantasyName(fantasyName).stream()
                .map(productRestMapper::toProductResponseDTO)
                .sorted(Comparator.comparing(ProductResponse::getFantasyName))
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }


    @Operation(
            summary = "Get a product by UUID",
            description = "Fetches a product by its UUID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/api/v1/product/{uuid}")
    public ResponseEntity<ProductResponse> findProductActiveByUuid(@PathVariable @Valid UUID uuid) {
        return ResponseEntity.ok(productRestMapper.toProductResponseDTO(productServicesPort.findActiveByUuid(uuid)));
    }


    @Operation(
            summary = "Update product data",
            description = "Updates the data of a product identified by its UUID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product data successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/api/v1/product/{uuid}")
    public ResponseEntity<ProductResponse> updateDataProductByUuid(@PathVariable @Valid UUID uuid, @RequestBody @Valid ProductsCreateRequestDTO productsCreateRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(productRestMapper.toProductResponseDTO(productServicesPort.update(uuid, productRestMapper.toProduct(productsCreateRequestDTO))));
    }


    @Operation(
            summary = "Soft delete a product",
            description = "Soft deletes a product identified by its UUID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/api/v1/product/{uuid}")
    public ResponseEntity<ProductResponse> softDeleteProductByUuid(@PathVariable @Valid UUID uuid) {
        ProductResponse productResponse = productRestMapper.toProductResponseDTO(productServicesPort.deleteByUuid(uuid));
        return ResponseEntity.ok().build();
    }
}
