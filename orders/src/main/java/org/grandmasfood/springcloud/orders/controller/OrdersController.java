package org.grandmasfood.springcloud.orders.controller;

import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping
    public ResponseEntity<?> createOrders(@RequestBody @Valid OrdersDTO orderDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return getErrors(bindingResult);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.createOrders(orderDTO));
    }

    @GetMapping
    public ResponseEntity<PageableDTO> readAllOrders(@PageableDefault(size = 10) Pageable pageable) {
        try {
            Page<Orders> orders = ordersService.readOrders(pageable);

            Page<OrdersDTO> ordersDTO = orders.map(o -> new OrdersDTO(o.getCreation_date_time(), o.getClient_document(), o.getProduct_uuid(), o.getQuantity(), o.getExtra_info(), o.getSub_total(), o.getTax(), o.getGrand_total(), null, o.getDelivery_date(), o.getActive()));

            return ResponseEntity.ok(PageableDTO.fromPage(ordersDTO));


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
//        return ordersService.readOrders(pageable);
    }


    private ResponseEntity<?> getErrors(BindingResult bindingResult) {
        Map<String, String> error = new HashMap<>();

        bindingResult.getFieldErrors().forEach(e -> {
            error.put(e.getField(), "This field " + e.getField() + " is not empty");
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}