package controller;

import jakarta.validation.Valid;
import model.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ClientsService;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ClientsController {

    @Autowired
    private ClientsService clientsService;

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody @Valid ClientDTO client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return getErrors(bindingResult);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(clientsService.createClient(client));
    }

    @GetMapping
    public ResponseEntity<?> message() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello, world!");
    }

    private ResponseEntity<?> getErrors(BindingResult bindingResult) {
        Map<String, String> error = new HashMap<>();

        bindingResult.getFieldErrors().forEach(e -> {
            error.put(e.getField(), "This field " + e.getField() + " is not empty");
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}