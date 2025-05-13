package org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.grandmasfood.springcloud.clients.application.ports.input.ClientsServicePort;
import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.mapper.ClientRestMapper;
import org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.model.request.ClientsCreateRequestDTO;
import org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.model.response.ClientsResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@Tag(name = "Clients", description = "The Clients microservice manages the creation, retrieval, update, and deletion of active clients.")
public class ClientsController {
    private final ClientsServicePort iCreateClientUseCase;
    private final ClientRestMapper clientRestMapper;

    public ClientsController(ClientsServicePort iCreateClientUseCase, ClientRestMapper clientRestMapper) {
        this.iCreateClientUseCase = iCreateClientUseCase;
        this.clientRestMapper = clientRestMapper;
    }

    @Operation(
            summary = "Create a client",
            description = "Create a new client with the provided information"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/api/v1/client")
    public ResponseEntity<ClientsResponseDTO> createClientRest(
            @RequestBody @Valid ClientsCreateRequestDTO clientsCreateRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientRestMapper
                .toClientsResponseDTO(iCreateClientUseCase.save(clientRestMapper.toClient(clientsCreateRequestDTO))));
    }

    @Hidden
    @GetMapping("/api/v1/{id}")
    public ResponseEntity<ClientsResponseDTO> findActiveClientById(@PathVariable @Valid Long id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(clientRestMapper.toClientsResponseDTO(iCreateClientUseCase.findActiveById(id)));

    }

    @Operation(
            summary = "Find a client by document",
            description = "Retrieve a client's details by their document"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    @GetMapping("/api/v1/client/{document}")
    public ResponseEntity<ClientsResponseDTO> listClientActiveByDocument(@PathVariable @Valid String document) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(clientRestMapper.toClientsResponseDTO(iCreateClientUseCase.findActiveByDocument(document)));

    }

    @Operation(
            summary = "Get all clients",
            description = "Retrieve a list of all clients sorted by the provided criteria"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clients retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/api/v1/client")
    public ResponseEntity<List<ClientsResponseDTO>> getAllClients(
            @RequestParam(name = "orderBy") String orderValue,
            @RequestParam(name = "direction") String directionValue) {
        List<Client> clients = iCreateClientUseCase.findClientByValue(orderValue, directionValue);
        List<ClientsResponseDTO> response = clientRestMapper.toClientResponseList(clients);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Update a client",
            description = "Update the details of an existing client"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Client updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/api/v1/client/{document}")
    public ResponseEntity<ClientsResponseDTO> update(@PathVariable String document,
                                                     @Valid @RequestBody ClientsCreateRequestDTO request) {
        clientRestMapper.toClientsResponseDTO(
                iCreateClientUseCase.update(document, clientRestMapper.toClient(request)));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Operation(
            summary = "Delete a client",
            description = "Delete a client by their document"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/api/v1/client/{document}")
    public ResponseEntity<ClientsResponseDTO> deleteClientActiveByDocument(@PathVariable @Valid String document) {
        ClientsResponseDTO clientsResponseDTO = clientRestMapper
                .toClientsResponseDTO(iCreateClientUseCase.deleteByDocument(document));
        return ResponseEntity.ok().build();
    }

}