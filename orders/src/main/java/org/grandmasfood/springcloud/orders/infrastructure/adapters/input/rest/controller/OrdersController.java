package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Orders", description = "The Orders microservice manages the creation and status update of orders, allowing the registration of new orders and marking their status as \"delivered\".")
public class OrdersController {

    private final OrdersServicesPort ordersServicesPort;
    private final OrderRestMapper orderRestMapper;

    public OrdersController(OrdersServicesPort ordersServicesPort, OrderRestMapper orderRestMapper) {
        this.ordersServicesPort = ordersServicesPort;
        this.orderRestMapper = orderRestMapper;
    }

    @Operation(
            summary = "Create a new order",
            description = "Creates a new order based on the provided information"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @PostMapping("/api/v1/order")
    public ResponseEntity<OrdersResponseDTO> createOrderRest(@RequestBody @Valid OrdersCreateRequestDTO ordersCreateRequestDTO) {
        try {
            OrdersResponseDTO responseDTO = orderRestMapper.toOrdersResponseDTO(ordersServicesPort.save(orderRestMapper.toOrder(ordersCreateRequestDTO)));
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @Operation(
            summary = "Update order status to delivered",
            description = "Updates the order status to 'delivered' based on the provided UUID and timestamp"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order status updated to delivered"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/api/v1/order/{uuid}/deliverd/{timestamp}")
    public ResponseEntity<OrdersResponseDTO> updateDelivered(@PathVariable @Valid UUID uuid, @PathVariable LocalDateTime timestamp) {
        try {
            OrdersResponseDTO responseDTO = orderRestMapper.toOrdersResponseDTO(ordersServicesPort.updateDelivered(uuid, timestamp));
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}