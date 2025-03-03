package org.grandmasfood.springcloud.clients.infraestructure.controller;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.domain.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.domain.model.entity.Clients;
import org.grandmasfood.springcloud.clients.application.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class ClientsController {
    @Autowired
    private ClientsService clientsService;

    @PostMapping("/client")
    public ResponseEntity<?> createClientRest(@RequestBody @Valid ClientsRequestDTO client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return getErrors(bindingResult);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(clientsService.createClient(client));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readClientActiveById(@PathVariable @Valid Long id) {
        Optional<Clients> client = clientsService.readCLientsActiveById(id);
        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/client/{document}")
    public ResponseEntity<?> ListClientActiveByDocument(@PathVariable @Valid String document) {
        Optional<Clients> client = clientsService.readActiveClientsByDocument(document);
        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/clients/{document}")
    public ResponseEntity<?> updateClientActiveByDocument(@RequestBody @Valid ClientsRequestDTO clientDTO, @PathVariable @Valid String document, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return getErrors(bindingResult);
        }

        Optional<Clients> client = Optional.ofNullable(clientsService.updateClient(clientDTO, document));
        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/client/{document}")
    public ResponseEntity<?> deleteClientActiveByDocument(@PathVariable @Valid String document) {
        Optional<Clients> client = clientsService.deleteClientsByDocument(document);
        if (client.isPresent()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
    }




    private ResponseEntity<?> getErrors(BindingResult bindingResult) {
        Map<String, String> error = new HashMap<>();

        bindingResult.getFieldErrors().forEach(e -> {
            error.put(e.getField(), "This field " + e.getField() + " is not empty");
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
