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
import java.util.Map;
import java.util.Optional;

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


    @GetMapping("/client/{document}")
    public ResponseEntity<ClientsResponseDTO> ListClientActiveByDocument(@PathVariable @Valid String document) {

        return ResponseEntity.status(HttpStatus.OK).body(clientRestMapper.toClientsResponseDTO(iCreateClientUseCase.findActiveByDocument(document)));

    }
//
//    @PutMapping("/clients/{document}")
//    public ResponseEntity<?> updateClientActiveByDocument(@RequestBody @Valid ClientsRequestDTO clientDTO, @PathVariable @Valid String document, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return getErrors(bindingResult);
//        }
//
//        Optional<ClientsEntity> client = Optional.ofNullable(clientsService.updateClient(clientDTO, document));
//        if (client.isPresent()) {
//            return ResponseEntity.ok(client.get());
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @DeleteMapping("/client/{document}")
//    public ResponseEntity<?> deleteClientActiveByDocument(@PathVariable @Valid String document) {
//        Optional<ClientsEntity> client = clientsService.deleteClientsByDocument(document);
//        if (client.isPresent()) {
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
//    }


    private ResponseEntity<?> getErrors(BindingResult bindingResult) {
        Map<String, String> error = new HashMap<>();

        bindingResult.getFieldErrors().forEach(e -> {
            error.put(e.getField(), "This field " + e.getField() + " is not empty");
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}