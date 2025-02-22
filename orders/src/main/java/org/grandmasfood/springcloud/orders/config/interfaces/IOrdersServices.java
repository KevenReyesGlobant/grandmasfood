package org.grandmasfood.springcloud.orders.config.interfaces;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.orders.model.Client;
import org.grandmasfood.springcloud.orders.model.Product;
import org.grandmasfood.springcloud.orders.model.dto.OrdersDTO;
import org.grandmasfood.springcloud.orders.model.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IOrdersServices {
    //    CRUD

    Orders createOrders(@Valid OrdersDTO ordersDTO);

    Page<Orders> readOrders(Pageable pageable);

    Optional<Orders> readOrdersById(Long id);

    void deleteOrdersById(Long id);



    //    Comunication microservices CLients

    Optional<Client> signedClient(Client client, Long id);

    Optional<Client> createClient(Client client, Long id);

    Optional<Client> designedClient(Client client, Long id);

    //    Comunication microservices Products

    Optional<Product> signedProduct(Product product, Long id);

    Optional<Product> createProduct(Product product, Long id);

    Optional<Product> designedProduct(Product product, Long id);
}

