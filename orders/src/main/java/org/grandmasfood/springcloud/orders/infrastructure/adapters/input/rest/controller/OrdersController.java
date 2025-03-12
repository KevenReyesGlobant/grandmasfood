package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.controller;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.orders.application.ports.input.OrdersServicesPort;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.mapper.OrderRestMapper;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.request.OrdersCreateRequestDTO;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.response.OrdersResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
