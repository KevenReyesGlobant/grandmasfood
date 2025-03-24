package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.controller;


import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.request.OrdersCreateRequestDTO;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.response.OrdersResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class OrdersControllerIntegrationTest {

    private static final UUID KNOWN_PRODUCT_UUID = UUID.fromString("0695fbf5-1334-405e-af4a-7c6801e878f0");
    private static final String KNOWN_CLIENT_DOCUMENT = "CC-2234567890";

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateOrder_WithValidData_ShouldCreateOrderSuccessfully() {
        OrdersCreateRequestDTO requestDTO = OrdersCreateRequestDTO.builder()
                .productUuid(KNOWN_PRODUCT_UUID)
                .clientDocument(KNOWN_CLIENT_DOCUMENT)
                .quantity(12)
                .extraInfo("Some extra information")
                .build();

        webTestClient.post()
                .uri("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(OrdersResponseDTO.class)
                .consumeWith(response -> {
                    OrdersResponseDTO body = response.getResponseBody();
                    assertThat(body).isNotNull();
                    assertThat(body.getClientDocument()).isEqualTo(KNOWN_CLIENT_DOCUMENT);
                    assertThat(body.getProductUuid()).isEqualTo(KNOWN_PRODUCT_UUID);
                    assertThat(body.getQuantity()).isEqualTo(99);
                    assertThat(body.getUuid()).isNotNull();
                });
    }

    @Test
    void testGetOrderById_WhenOrderExists_ShouldReturnOrder() {
        // First, create an order
        OrdersCreateRequestDTO requestDTO = OrdersCreateRequestDTO.builder()
                .productUuid(KNOWN_PRODUCT_UUID)
                .clientDocument(KNOWN_CLIENT_DOCUMENT)
                .quantity(10)
                .extraInfo("Test order for retrieval")
                .build();

        OrdersResponseDTO createdOrder = webTestClient.post()
                .uri("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(OrdersResponseDTO.class)
                .returnResult()
                .getResponseBody();

        assertThat(createdOrder).isNotNull();
        UUID orderId = createdOrder.getUuid();

        // Then, retrieve the order
        webTestClient.get()
                .uri("/order/{id}", orderId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(OrdersResponseDTO.class)
                .consumeWith(response -> {
                    OrdersResponseDTO body = response.getResponseBody();
                    assertThat(body).isNotNull();
                    assertThat(body.getUuid()).isEqualTo(orderId);
                    assertThat(body.getClientDocument()).isEqualTo(KNOWN_CLIENT_DOCUMENT);
                    assertThat(body.getProductUuid()).isEqualTo(KNOWN_PRODUCT_UUID);
                });
    }

    @Test
    void testCreateOrder_WithInvalidData_ShouldReturnBadRequest() {
        OrdersCreateRequestDTO invalidRequestDTO = OrdersCreateRequestDTO.builder()
                .productUuid(null)
                .clientDocument(null)
                .quantity(-1)
                .build();

        webTestClient.post()
                .uri("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(invalidRequestDTO)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testGetOrderById_WhenOrderNotExists_ShouldReturnNotFound() {
        UUID nonExistentOrderId = UUID.randomUUID();

        webTestClient.get()
                .uri("/order/{id}", nonExistentOrderId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }
}