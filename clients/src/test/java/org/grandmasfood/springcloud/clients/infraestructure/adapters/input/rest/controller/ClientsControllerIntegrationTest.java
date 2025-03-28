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

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class ClientsControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    //    CREATE CLIENT H2 DATABASE
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

//    FIND CLIENT H2 DATABASE

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
                .uri("/client/{document}", document)
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

//    FIND ALL CLIENT H2 DATABASE

    @Test
    void testGetAllClients() {
        ClientsCreateRequestDTO requestDTO = new ClientsCreateRequestDTO();
        requestDTO.setName("Test User");
        requestDTO.setEmail("test.user@example.com");
        requestDTO.setDocument("CC-1111222233");
        requestDTO.setPhone("3123456789");
        requestDTO.setDeliveryAddress("Test Address");
        requestDTO.setActive(true);

        webTestClient.post()
                .uri("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isCreated();

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/client")
                        .queryParam("orderBy", "name")
                        .queryParam("direction", "asc")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ClientsResponseDTO.class)
                .consumeWith(response -> {
                    List<ClientsResponseDTO> clients = response.getResponseBody();
                    assert clients != null;
                    assertThat(clients).isNotEmpty();
                });

    }

    //    UPDATE CLIENT H2 DATABASE
    @Test
    void testUpdateClient() {
        ClientsCreateRequestDTO requestDTO = new ClientsCreateRequestDTO();
        requestDTO.setName("Pedro Torres");
        requestDTO.setEmail("pedro.torres@example.com");
        requestDTO.setDocument("CC-5544332211");
        requestDTO.setPhone("3154433221");
        requestDTO.setDeliveryAddress("Calle 60 #22-33, Medellín");
        requestDTO.setActive(true);

        webTestClient.post()
                .uri("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isCreated();

        ClientsCreateRequestDTO updatedRequest = new ClientsCreateRequestDTO();
        updatedRequest.setName("Pedro Torres Actualizado");
        updatedRequest.setEmail("pedro.actualizado@example.com");
        updatedRequest.setDocument("CC-5544332211");
        updatedRequest.setPhone("3209998888");
        updatedRequest.setDeliveryAddress("Carrera 50 #10-20, Medellín");
        updatedRequest.setActive(true);

        webTestClient.put()
                .uri("/client/{document}", "CC-5544332211")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedRequest)
                .exchange()
                .expectStatus().isNoContent();

        webTestClient.get()
                .uri("/client/{document}", "CC-5544332211")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ClientsResponseDTO.class)
                .consumeWith(response -> {
                    ClientsResponseDTO updatedClient = response.getResponseBody();
                    assert updatedClient != null;
                    assertThat(updatedClient.getName()).isEqualTo("Pedro Torres Actualizado");
                    assertThat(updatedClient.getEmail()).isEqualTo("pedro.actualizado@example.com");
                });
    }

    //    DELETE CLIENT H2 DATABASE
    @Test
    void testDeleteClientByDocument() {
        ClientsCreateRequestDTO requestDTO = new ClientsCreateRequestDTO();
        requestDTO.setName("Luis Ramírez");
        requestDTO.setEmail("luis.ramirez@example.com");
        requestDTO.setDocument("CC-6677889900");
        requestDTO.setPhone("3007788990");
        requestDTO.setDeliveryAddress("Transversal 80 #30-20, Cali");
        requestDTO.setActive(true);

        webTestClient.post()
                .uri("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isCreated();

        webTestClient.delete()
                .uri("/client/{document}", "CC-6677889900")
                .exchange()
                .expectStatus().isOk();

        webTestClient.get()
                .uri("/client/{document}", "CC-6677889900")
                .exchange()
                .expectStatus().isNotFound();
    }

//    VALIDATIONS

    //    UPDATE NON EXISTENT CLIENT H2 DATABASE
    @Test
    void testUpdateNonExistentClient() {
        ClientsCreateRequestDTO updatedRequest = new ClientsCreateRequestDTO();
        updatedRequest.setName("Cliente Inexistente");
        updatedRequest.setEmail("inexistente@example.com");
        updatedRequest.setDocument("CC-0000000000");
        updatedRequest.setPhone("3000000000");
        updatedRequest.setDeliveryAddress("Dirección falsa");
        updatedRequest.setActive(true);

        webTestClient.put()
                .uri("/client/{document}", "CC-0000000000")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedRequest)
                .exchange()
                .expectStatus().isNotFound();
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
        requestDTO.setPhone("123456");
        requestDTO.setDeliveryAddress("Test Address");

        webTestClient.post()
                .uri("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isBadRequest();
    }


}






