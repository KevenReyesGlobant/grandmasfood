package org.grandmasfood.springcloud.orders.controller;

import feign.FeignException;
import jakarta.validation.Valid;
import org.grandmasfood.springcloud.orders.model.Client;
import org.grandmasfood.springcloud.orders.model.dto.OrdersDTO;
import org.grandmasfood.springcloud.orders.model.dto.PageableDTO;
import org.grandmasfood.springcloud.orders.model.entity.Orders;
import org.grandmasfood.springcloud.orders.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("/")
    public ResponseEntity<?> createOrders(@RequestBody @Valid OrdersDTO orderDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return getErrors(bindingResult);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.createOrders(orderDTO));
    }

    @GetMapping("/")
    public ResponseEntity<PageableDTO> readAllOrders(@PageableDefault(size = 10) Pageable pageable) {
        try {
            Page<Orders> orders = ordersService.readOrders(pageable);

            Page<OrdersDTO> ordersDTO = orders.map(o -> new OrdersDTO(
                    o.getCreation_date_time(), o.getClient_document(), o.getOrdersProducts(), o.getOrdersClients(),
                    o.getProduct_uuid(), o.getQuantity(), o.getExtra_info(), o.getSub_total(), o.getTax(),
                    o.getGrand_total(), o.isDelivered(), o.getDelivery_date(), o.getActive()
            ));

            return ResponseEntity.ok(PageableDTO.fromPage(ordersDTO));


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/signed_client/{id}")
    ResponseEntity<?> signedClient(@RequestBody Client client, @PathVariable @Valid Long id) {
        Optional<Client> client_msvc;
        try {
            client_msvc = ordersService.signedClient(client, id);


        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message: ", "Client not found" + e.getMessage()));

        }
        if (client_msvc.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(client_msvc.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/created_client/{id}")
    ResponseEntity<?> createClient(@RequestBody Client client, @PathVariable @Valid Long id) {
        Optional<Client> client_msvc;
        try {
            client_msvc = ordersService.createClient(client, id);


        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message: ", "Client not create" + e.getMessage()));

        }
        if (client_msvc.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(client_msvc.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/designed_client/{id}")
    ResponseEntity<?> deleteClient(@RequestBody Client client, @PathVariable @Valid Long id) {
        Optional<Client> client_msvc;
        try {
            client_msvc = ordersService.designedClient(client, id);


        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message: ", "Client not designed" + e.getMessage()));

        }
        if (client_msvc.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(client_msvc.get());
        }
        return ResponseEntity.notFound().build();
    }


    private ResponseEntity<?> getErrors(BindingResult bindingResult) {
        Map<String, String> error = new HashMap<>();

        bindingResult.getFieldErrors().forEach(e -> {
            error.put(e.getField(), "This field " + e.getField() + " is not empty");
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}