package org.grandmasfood.springcloud.orders.application.services;

import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.orders.application.ports.input.OrdersServicesPort;
import org.grandmasfood.springcloud.orders.application.ports.output.OrdersPersistentPort;
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
        return null;
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

    private <T> void updateField(T newValue, java.util.function.Consumer<T> setter) {
        if (newValue != null) {
            setter.accept(newValue);
        }
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
        return Optional.empty();
    }

    @Override
    public Optional<Client> createClient(Client client, Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> designedClient(Client client, Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> signedProduct(Product product, Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> createProduct(Product product, Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> designedProduct(Product product, Long id) {
        return Optional.empty();
    }
}
