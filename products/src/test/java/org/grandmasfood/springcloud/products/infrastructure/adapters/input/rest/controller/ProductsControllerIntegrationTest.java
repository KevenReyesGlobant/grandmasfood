package org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.controller;

import org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.model.enums.Category;
import org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.model.request.ProductsCreateRequestDTO;
import org.grandmasfood.springcloud.products.infrastructure.adapters.input.rest.model.response.ProductResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class ProductsControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateProduct() {
        ProductsCreateRequestDTO requestDTO = new ProductsCreateRequestDTO();
        requestDTO.setFantasyName("CHOCO CAKE");
        requestDTO.setCategory(Category.valueOf("MEATS"));
        requestDTO.setDescription("CAKE");
        requestDTO.setPrice(12.5F);
        requestDTO.setAvailable(true);

        webTestClient.post()
                .uri("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ProductResponse.class)
                .consumeWith(response -> {
                    var body = response.getResponseBody();
                    assert body != null;
                    assertThat(body.getFantasyName()).isEqualTo("CHOCO CAKE");
                    assertThat(body.getCategory()).isEqualTo(Category.MEATS);
                    assertThat(body.getDescription()).isEqualTo("CAKE");
                    assertThat(body.getPrice()).isEqualTo(12.5F);
                    assertThat(body.isAvailable()).isTrue();
                    assertThat(body.getUuid()).isNotNull();
                });
    }

    @Test
    void testSearchProductByName() {
        // Create a product
        ProductsCreateRequestDTO requestDTO = new ProductsCreateRequestDTO();
        requestDTO.setFantasyName("SEARCH PRODUCT");
        requestDTO.setCategory(Category.MEATS);
        requestDTO.setPrice(15.0F);
        requestDTO.setDescription("Product for search test");
        requestDTO.setAvailable(true);

        ProductResponse createdProduct = webTestClient.post()
                .uri("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isCreated()
                .returnResult(ProductResponse.class)
                .getResponseBody()
                .blockFirst();

        assertThat(createdProduct).isNotNull();

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/product/search")
                        .queryParam("q", "SEARCH PRODUCT")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ProductResponse.class)
                .consumeWith(response -> {
                    List<ProductResponse> products = response.getResponseBody();
                    assertThat(products)
                            .isNotEmpty()
                            .anySatisfy(product -> {
                                assertThat(product.getFantasyName()).isEqualTo("SEARCH PRODUCT");
                                assertThat(product.getCategory()).isEqualTo(Category.MEATS);
                            });
                });
    }

    @Test
    void testUpdateProduct() {
        // Create initial product
        ProductsCreateRequestDTO createRequestDTO = new ProductsCreateRequestDTO();
        createRequestDTO.setFantasyName("ORIGINAL PRODUCT");
        createRequestDTO.setCategory(Category.MEATS);
        createRequestDTO.setPrice(10.0F);
        createRequestDTO.setDescription("Initial product description");

        ProductResponse createdProduct = webTestClient.post()
                .uri("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(createRequestDTO)
                .exchange()
                .expectStatus().isCreated()
                .returnResult(ProductResponse.class)
                .getResponseBody()
                .blockFirst();

        assertThat(createdProduct).isNotNull();
        assertThat(createdProduct.getUuid()).isNotNull();

        UUID productUuid = createdProduct.getUuid();

        ProductsCreateRequestDTO updateRequestDTO = new ProductsCreateRequestDTO();
        updateRequestDTO.setFantasyName("UPDATED PRODUCT");
        updateRequestDTO.setCategory(Category.DESSERTS);
        updateRequestDTO.setPrice(15.0F);
        updateRequestDTO.setDescription("Updated product description");

        ProductResponse updatedProduct = webTestClient.put()
                .uri("/product/{uuid}", productUuid)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updateRequestDTO)
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductResponse.class)
                .getResponseBody()
                .blockFirst();

        System.out.println("Created Product UUID: " + createdProduct.getUuid());
        System.out.println("Update Request DTO: " + updateRequestDTO);
        System.out.println("Updated Product Response: " + updatedProduct);

        assertThat(updatedProduct).isNotNull();
        assertThat(updatedProduct.getUuid()).isEqualTo(productUuid);
        assertThat(updatedProduct.getFantasyName()).isEqualTo("UPDATED PRODUCT");
        assertThat(updatedProduct.getCategory()).isEqualTo(Category.DESSERTS);
        assertThat(updatedProduct.getPrice()).isEqualTo(15.0F);
        assertThat(updatedProduct.getDescription()).isEqualTo("Updated product description");


        ProductResponse fetchedProduct = webTestClient.get()
                .uri("/product/{uuid}", productUuid)
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductResponse.class)
                .getResponseBody()
                .blockFirst();

        System.out.println("Fetched Product: " + fetchedProduct);
        assertThat(fetchedProduct).isNotNull();
    }

    @Test
    void testDeleteProduct() {
        // Create product to delete
        ProductsCreateRequestDTO createRequestDTO = new ProductsCreateRequestDTO();
        createRequestDTO.setFantasyName("Product to Delete");
        createRequestDTO.setPrice(12.0F);
        createRequestDTO.setDescription("This product will be deleted");

        ProductResponse createdProduct = webTestClient.post()
                .uri("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(createRequestDTO)
                .exchange()
                .expectStatus().isCreated()
                .returnResult(ProductResponse.class)
                .getResponseBody()
                .blockFirst();

        assertThat(createdProduct).isNotNull();

        webTestClient.delete()
                .uri("/product/{uuid}", createdProduct.getUuid())
                .exchange()
                .expectStatus().isOk();

        webTestClient.get()
                .uri("/product/{uuid}", createdProduct.getUuid())
                .exchange()
                .expectStatus().isNotFound();
    }
}