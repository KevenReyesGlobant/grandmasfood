package org.grandmasfood.springcloud.orders.application.ports.input;


import org.grandmasfood.springcloud.orders.domain.model.Order;
import org.grandmasfood.springcloud.orders.domain.model.Product;
import org.grandmasfood.springcloud.orders.model.Client;

import java.util.List;
import java.util.Optional;

public interface OrdersServicesPort {
    Order findById(Long id);

    List<Order> findAll();

    Order save(Order order);

    Order update(String document, Order order);

    Order findActiveByDocument(String document);

    Order findActiveById(Long id);

    Order deleteByDocument(String document);

//
//    Orders createOrders(@Valid OrdersDTO ordersDTO);
//
//    Page<Orders> readOrders(Pageable pageable);
//
//    Optional<Orders> readOrdersById(Long id);
//
//    void deleteOrdersById(Long id);
//
//

    //    Comunication microservices CLients

    Optional<Client> signedClient(Client client, Long id);

    Optional<Client> createClient(Client client, Long id);

    Optional<Client> designedClient(Client client, Long id);

//    //    Comunication microservices Products
//
    Optional<Product> signedProduct(Product product, Long id);

    Optional<Product> createProduct(Product product, Long id);

    Optional<Product> designedProduct(Product product, Long id);

}
