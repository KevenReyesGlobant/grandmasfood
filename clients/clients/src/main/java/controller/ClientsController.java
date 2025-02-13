package controller;

import jakarta.validation.Valid;
import model.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.ClientsService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ClientsController {

    @Autowired
    private ClientsService clientsService;

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody @Valid ClientDTO clientDTO, BindingResult bindingResult) {

        return null;
    }

    private ResponseEntity<?> getErrors(BindingResult bindingResult) {
        Map<String, String> error = new HashMap<>();

        return null;
    }
}
