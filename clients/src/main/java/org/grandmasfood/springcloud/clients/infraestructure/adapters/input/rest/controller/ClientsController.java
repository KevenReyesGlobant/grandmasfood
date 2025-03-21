package org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.controller;


import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.application.ports.input.ClientsServicePort;
import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.mapper.ClientRestMapper;
import org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.model.request.ClientsCreateRequestDTO;
import org.grandmasfood.springcloud.clients.infraestructure.adapters.input.rest.model.response.ClientsResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ClientsController {
    private final ClientsServicePort iCreateClientUseCase;
    private final ClientRestMapper clientRestMapper;

    public ClientsController(ClientsServicePort iCreateClientUseCase, ClientRestMapper clientRestMapper) {
        this.iCreateClientUseCase = iCreateClientUseCase;
        this.clientRestMapper = clientRestMapper;
    }


    @PostMapping("/client")
    public ResponseEntity<ClientsResponseDTO> createClientRest(@RequestBody @Valid ClientsCreateRequestDTO clientsCreateRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientRestMapper.toClientsResponseDTO(iCreateClientUseCase.save(clientRestMapper.toClient(clientsCreateRequestDTO))));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClientsResponseDTO> findActiveClientById(@PathVariable @Valid Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(clientRestMapper.toClientsResponseDTO(iCreateClientUseCase.findActiveById(id)));

    }

    @GetMapping("/client")
    public ResponseEntity<List<ClientsResponseDTO>> getAllClients(
            @RequestParam(name = "orderBy") String orderValue,
            @RequestParam(name = "direction") String directionValue) {
        List<Client> clients = iCreateClientUseCase.findClientByValue(orderValue, directionValue);
        List<ClientsResponseDTO> response = clientRestMapper.toClientResponseList(clients);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/client/{document}")
    public ResponseEntity<ClientsResponseDTO> listClientActiveByDocument(@PathVariable @Valid String document) {

        return ResponseEntity.status(HttpStatus.OK).body(clientRestMapper.toClientsResponseDTO(iCreateClientUseCase.findActiveByDocument(document)));

    }


    @PutMapping("/client/{document}")
    public ResponseEntity<ClientsResponseDTO> update(@PathVariable String document, @Valid @RequestBody ClientsCreateRequestDTO request) {
        clientRestMapper.toClientsResponseDTO(
                iCreateClientUseCase.update(document, clientRestMapper.toClient(request)));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }


    @DeleteMapping("/client/{document}")
    public ResponseEntity<ClientsResponseDTO> deleteClientActiveByDocument(@PathVariable @Valid String document) {
        ClientsResponseDTO clientsResponseDTO = clientRestMapper.toClientsResponseDTO(iCreateClientUseCase.deleteByDocument(document));
        return ResponseEntity.ok().build();
    }


    private ResponseEntity<?> getErrors(BindingResult bindingResult) {
        Map<String, String> error = new HashMap<>();

        bindingResult.getFieldErrors().forEach(e -> {
            error.put(e.getField(), "This field " + e.getField() + " is not empty");
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}