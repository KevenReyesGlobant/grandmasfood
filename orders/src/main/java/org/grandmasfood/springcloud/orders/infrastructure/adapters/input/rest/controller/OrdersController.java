package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.controller;

import feign.FeignException;
import jakarta.validation.Valid;
import org.grandmasfood.springcloud.orders.application.ports.input.OrdersServicesPort;
import org.grandmasfood.springcloud.orders.domain.model.Client;
import org.grandmasfood.springcloud.orders.domain.model.Order;
import org.grandmasfood.springcloud.orders.domain.model.Product;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.mapper.OrderRestMapper;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.request.OrdersCreateRequestDTO;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.response.OrdersResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@RestController
public class OrdersController {

    private final OrdersServicesPort ordersServicesPort;
    private final OrderRestMapper orderRestMapper;


    public OrdersController(OrdersServicesPort ordersServicesPort, OrderRestMapper orderRestMapper) {
        this.ordersServicesPort = ordersServicesPort;
        this.orderRestMapper = orderRestMapper;
    }

    @PostMapping("/order")
    public ResponseEntity<OrdersResponseDTO> createOrderRest(@RequestBody @Valid OrdersCreateRequestDTO ordersCreateRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderRestMapper.toOrdersResponseDTO(ordersServicesPort.save(orderRestMapper.toOrder(ordersCreateRequestDTO))));

    }

    @PatchMapping("/order/{uuid}/deliverd/{timestamp}")
    public ResponseEntity<OrdersResponseDTO> updateDelivered(@PathVariable @Valid UUID uuid, Order order, @PathVariable LocalDateTime timestamp) {
        return ResponseEntity.status(HttpStatus.OK).body(orderRestMapper.toOrdersResponseDTO(ordersServicesPort.updateDelivered(uuid, order, timestamp)));
    }

    @PutMapping("/order/signed_client/{id}")
    ResponseEntity<?> signedClient(@RequestBody Client client, @PathVariable @Valid Long id) {
        Optional<Client> client_msvc;
        try {
            client_msvc = ordersServicesPort.signedClient(client, id);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message: ", "Client not found" + e.getMessage()));
        }
        if (client_msvc.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(client_msvc.get());
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/order/signed_product/{id}")
    ResponseEntity<?> signedProduct(@RequestBody Product product, @PathVariable @Valid Long id) {
        Optional<Product> product_msvc;
        try {
            product_msvc = ordersServicesPort.signedProduct(product, id);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message: ", "Product not found" + e.getMessage()));
        }
        if (product_msvc.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(product_msvc.get());
        }
        return ResponseEntity.notFound().build();
    }
}
