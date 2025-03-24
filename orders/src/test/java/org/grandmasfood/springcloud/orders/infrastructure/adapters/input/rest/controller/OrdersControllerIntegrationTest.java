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

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateOrder() {
        OrdersCreateRequestDTO requestDTO = OrdersCreateRequestDTO.builder()
                .productUuid(UUID.randomUUID())
                .clientDocument("CC-2234567890")
                .quantity(99)
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
                    assertThat(body.getClientDocument()).isEqualTo("CC-2234567890");
                    assertThat(body.getQuantity()).isEqualTo(99);
                });
    }

    @Test
    void testGetOrderById() {
        OrdersCreateRequestDTO requestDTO = OrdersCreateRequestDTO.builder()
                .productUuid(UUID.randomUUID())
                .clientDocument("CC-1122334455")
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
                    assertThat(body.getClientDocument()).isEqualTo("CC-1122334455");
                });
    }
}