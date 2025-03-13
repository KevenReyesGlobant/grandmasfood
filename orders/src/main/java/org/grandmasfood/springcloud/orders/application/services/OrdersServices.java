package org.grandmasfood.springcloud.orders.application.services;

import org.grandmasfood.springcloud.orders.application.ports.input.OrdersServicesPort;
import org.grandmasfood.springcloud.orders.application.ports.output.OrdersPersistentPort;
import org.grandmasfood.springcloud.orders.domain.exceptions.OrderNotFoundException;
import org.grandmasfood.springcloud.orders.domain.model.Client;
import org.grandmasfood.springcloud.orders.domain.model.Order;
import org.grandmasfood.springcloud.orders.domain.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersServices implements OrdersServicesPort {

    private final OrdersPersistentPort ordersPersistentPort;

    public OrdersServices(OrdersPersistentPort ordersPersistentPort) {
        this.ordersPersistentPort = ordersPersistentPort;
    }


    @Override
    public Order findById(Long id) {
        return ordersPersistentPort.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }

    @Override
    public Order save(Order order) {
        return ordersPersistentPort.save(order);
    }

    @Override
    public Order update(String document, Order order) {
        return null;
    }

    @Override
    public Order findActiveByDocument(String document) {
        return null;
    }

    @Override
    public Order findActiveById(Long id) {
        return null;
    }

    @Override
    public Order deleteByDocument(String document) {
        return null;
    }

    @Override
    public Optional<Client> signedClient(Client client, Long id) {
        return Optional.ofNullable(ordersPersistentPort.signedClient(client, id).orElseThrow(OrderNotFoundException::new));
    }

    @Override
    public Optional<Client> createClient(Client client, Long id) {
        return Optional.ofNullable(ordersPersistentPort.createClient(client, id).orElseThrow(OrderNotFoundException::new));
    }

    @Override
    public Optional<Client> designedClient(Client client, Long id) {
        return Optional.ofNullable(ordersPersistentPort.designedClient(client, id).orElseThrow(OrderNotFoundException::new));
    }

    @Override
    public Optional<Product> signedProduct(Product product, Long id) {
        return Optional.ofNullable(ordersPersistentPort.signedProduct(product, id).orElseThrow(OrderNotFoundException::new));
    }

    @Override
    public Optional<Product> createProduct(Product product, Long id) {
        return Optional.ofNullable(ordersPersistentPort.createProduct(product, id).orElseThrow(OrderNotFoundException::new));
    }

    @Override
    public Optional<Product> designedProduct(Product product, Long id) {
        return Optional.of(ordersPersistentPort.designedProduct(product, id).orElseThrow());
    }
}
