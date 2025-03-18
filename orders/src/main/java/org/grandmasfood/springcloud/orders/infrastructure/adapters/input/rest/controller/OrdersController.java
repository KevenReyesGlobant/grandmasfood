package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.controller;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.orders.application.ports.input.OrdersServicesPort;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.mapper.OrderRestMapper;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.request.OrdersCreateRequestDTO;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.response.OrdersResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public ResponseEntity<OrdersResponseDTO> updateDelivered(@PathVariable @Valid UUID uuid, @PathVariable LocalDateTime timestamp) {
        return ResponseEntity.status(HttpStatus.OK).body(orderRestMapper.toOrdersResponseDTO(ordersServicesPort.updateDelivered(uuid, timestamp)));
    }

}
