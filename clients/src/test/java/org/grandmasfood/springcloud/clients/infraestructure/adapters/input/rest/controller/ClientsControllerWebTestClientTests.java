package org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.controller;

import org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.model.request.ClientsCreateRequestDTO;
import org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.model.response.ClientsResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class ClientsControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateClient() {
        ClientsCreateRequestDTO requestDTO = new ClientsCreateRequestDTO();
        requestDTO.setName("Keven Steven");
        requestDTO.setEmail("john.doe9@example.com");
        requestDTO.setDocument("CC-1234567890");
        requestDTO.setPhone("1234567890");
        requestDTO.setDeliveryAddress("123 Main St, Anytown, USA");
        requestDTO.setActive(true);

        webTestClient.post()
                .uri("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ClientsResponseDTO.class)
                .consumeWith(response -> {
                    var body = response.getResponseBody();
                    assert body != null;
                    assert body.getName().equals("Keven Steven");
                    assert body.getEmail().equals("john.doe9@example.com");
                    assert body.getDocument().equals("CC-1234567890");
                    assert body.getPhone().equals("1234567890");
                    assert body.getDeliveryAddress().equals("123 Main St, Anytown, USA");
                });
    }

    @Test
    void testGetClientByDocument() {
        ClientsCreateRequestDTO requestDTO = new ClientsCreateRequestDTO();
        requestDTO.setName("Laura González");
        requestDTO.setEmail("laura.gonzalez@example.com");
        requestDTO.setDocument("CC-9876543210");
        requestDTO.setPhone("9876543210");
        requestDTO.setDeliveryAddress("456 Oak Avenue, Springfield, USA");
        requestDTO.setActive(true);

        webTestClient.post()
                .uri("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isCreated();

        String document = "CC-9876543210";
        webTestClient.get()
                .uri("/{document}", document)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ClientsResponseDTO.class)
                .consumeWith(response -> {
                    var body = response.getResponseBody();
                    assert body != null;
                    assert body.getDocument().equals("CC-9876543210");
                    assert body.getName().equals("Laura González");
                    assert body.getEmail().equals("laura.gonzalez@example.com");
                    assert body.getPhone().equals("9876543210");
                    assert body.getDeliveryAddress().equals("456 Oak Avenue, Springfield, USA");
                });
    }

    @Test
    void testValidationFailsWithInvalidDocument() {
        ClientsCreateRequestDTO requestDTO = new ClientsCreateRequestDTO();
        requestDTO.setName("Test User");
        requestDTO.setEmail("test.user@example.com");
        requestDTO.setDocument("1234567890");
        requestDTO.setPhone("1234567890");
        requestDTO.setDeliveryAddress("Test Address");

        webTestClient.post()
                .uri("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testValidationFailsWithInvalidPhone() {
        ClientsCreateRequestDTO requestDTO = new ClientsCreateRequestDTO();
        requestDTO.setName("Test User");
        requestDTO.setEmail("test.user@example.com");
        requestDTO.setDocument("CC-1234567890");
        requestDTO.setPhone("123456"); // No tiene 10 dígitos
        requestDTO.setDeliveryAddress("Test Address");

        webTestClient.post()
                .uri("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isBadRequest();
    }
}