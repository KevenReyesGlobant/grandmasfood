package org.grandmasfood.springcloud.orders.application.ports.output;


import feign.Param;
import org.grandmasfood.springcloud.orders.domain.model.Client;
import org.grandmasfood.springcloud.orders.domain.model.Order;
import org.grandmasfood.springcloud.orders.domain.model.Product;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.output.entities.OrderEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrdersPersistentPort {
    Optional<Order> findById(Long id);

    List<Order> findAll();

    Order save(Order order);

    Optional<Order> findActiveByDocument(String document);

    Optional<Order> findActiveById(Long id);

    Order deleteByDocument(String document);

    Optional<Order> updateByUuidAndDocumentActive(@Param("clientDocument") String clientDocument, @Param("productUuid") UUID productUuid);

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
