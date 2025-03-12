package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.controller;

import feign.FeignException;
import jakarta.validation.Valid;
import org.grandmasfood.springcloud.orders.model.Client;
import org.grandmasfood.springcloud.orders.model.Product;
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

    @PostMapping("/order")
    public ResponseEntity<?> createOrders(@RequestBody @Valid OrdersDTO orderDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return getErrors(bindingResult);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.createOrders(orderDTO));
    }


    @GetMapping("/order")
    public ResponseEntity<PageableDTO> readAllOrders(@PageableDefault(size = 10) Pageable pageable) {
        try {
            Page<Orders> orders = ordersService.readOrders(pageable);

            Page<OrdersDTO> ordersDTO = orders.map(o -> new OrdersDTO(
                    o.getCreationDateTime(), o.getClientDocument(), o.getOrdersProducts(), o.getOrdersClients(),
                    o.getProductUuid(), o.getQuantity(), o.getExtraInfo(), o.getSubTotal(), o.getTax(),
                    o.getGrandTotal(), o.isDelivered(), o.getDeliveryDate(), o.getActive()
            ));

            return ResponseEntity.ok(PageableDTO.fromPage(ordersDTO));


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //    SIGNED

    @PutMapping("/order/signed_client/{id}")
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

    @PutMapping("/order/signed_product/{id}")
    ResponseEntity<?> signedProduct(@RequestBody Product product, @PathVariable @Valid Long id) {
        Optional<Product> product_msvc;
        try {
            product_msvc = ordersService.signedProduct(product, id);


        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message: ", "Product not found" + e.getMessage()));

        }
        if (product_msvc.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(product_msvc.get());
        }
        return ResponseEntity.notFound().build();
    }

    //    CREATED

    @PostMapping("/order/created_client/{id}")
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

    @PostMapping("/order/created_product/{id}")
    ResponseEntity<?> createProduct(@RequestBody Product product, @PathVariable @Valid Long id) {
        Optional<Product> product_msvc;
        try {
            product_msvc = ordersService.createProduct(product, id);


        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message: ", "Product not create" + e.getMessage()));

        }
        if (product_msvc.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(product_msvc.get());
        }
        return ResponseEntity.notFound().build();
    }


    //DESIGNED

    @DeleteMapping("/order/designed_client/{id}")
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

    @DeleteMapping("/order/designed_product/{id}")
    ResponseEntity<?> deleteProduct(@RequestBody Product product, @PathVariable @Valid Long id) {
        Optional<Product> product_msvc;
        try {
            product_msvc = ordersService.designedProduct(product, id);


        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message: ", "Product not designed" + e.getMessage()));

        }
        if (product_msvc.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(product_msvc.get());
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