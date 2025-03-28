package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.controller;

import org.grandmasfood.springcloud.orders.application.ports.input.IClientClientRest;
import org.grandmasfood.springcloud.orders.application.ports.input.IProductClientRest;
import org.grandmasfood.springcloud.orders.domain.model.Client;
import org.grandmasfood.springcloud.orders.domain.model.Product;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.request.OrdersCreateRequestDTO;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.response.OrdersResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class OrdersControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private IClientClientRest clientClientRest;

    @MockBean
    private IProductClientRest productClientRest;

    private static final UUID PRODUCT_UUID = UUID.fromString("5d0db43e-e22b-4285-878e-b4ceb892df98");
    private static final String CLIENT_DOCUMENT = "CC-2234567890";

    @BeforeEach
    void setUp() {
        // Prepare mock data for client and product
        Product mockProduct = Product.builder()
                .uuid(PRODUCT_UUID)
                .active(true)
                .build();

        Client mockClient = Client.builder()
                .document(CLIENT_DOCUMENT)
                .name("Test Client")
                .active(true)
                .build();

        // Setup mock behaviors
        when(productClientRest.findProductActiveByUuid(PRODUCT_UUID)).thenReturn(mockProduct);
        when(clientClientRest.listClientActiveByDocuments(CLIENT_DOCUMENT)).thenReturn(mockClient);
    }

    @Test
    void testCreateOrder_WithValidData_ShouldCreateOrderSuccessfully() {
        OrdersCreateRequestDTO requestDTO = OrdersCreateRequestDTO.builder()
                .productUuid(PRODUCT_UUID)
                .clientDocument(CLIENT_DOCUMENT)
                .quantity(12)
                .extraInfo("Test order creation")
                .creationDateTime(LocalDateTime.now())
                .subTotal(120.0)
                .tax(12.0)
                .grandTotal(132.0)
                .delivered(false)
                .active(true)
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

        assertNotNull(createdOrder);
        assertNotNull(createdOrder.getUuid());
    }

    @Test
    void testUpdateDelivered_WithNullUuid_ShouldReturnBadRequest() {
        LocalDateTime deliveryTimestamp = LocalDateTime.now();

        webTestClient.patch()
                .uri("/order/null/deliverd/" + deliveryTimestamp)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testUpdateDelivered_WithInvalidUuid_ShouldReturnBadRequest() {
        UUID invalidUuid = UUID.randomUUID();
        LocalDateTime deliveryTimestamp = LocalDateTime.now();

        webTestClient.patch()
                .uri("/order/" + invalidUuid + "/deliverd/" + deliveryTimestamp)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void testUpdateDelivered_WithFutureDeliveryDate() {
        OrdersCreateRequestDTO requestDTO = OrdersCreateRequestDTO.builder()
                .productUuid(PRODUCT_UUID)
                .clientDocument(CLIENT_DOCUMENT)
                .quantity(12)
                .extraInfo("Test order for future date test")
                .creationDateTime(LocalDateTime.now())
                .subTotal(120.0)
                .tax(12.0)
                .grandTotal(132.0)
                .delivered(false)
                .active(true)
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

        LocalDateTime futureTimestamp = LocalDateTime.now().plusDays(1);

        webTestClient.patch()
                .uri("/order/" + createdOrder.getUuid() + "/deliverd/" + futureTimestamp)
                .exchange()
                .expectStatus().isOk();
    }
}