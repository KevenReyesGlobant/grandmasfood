package org.grandmasfood.springcloud.orders.application.services;

import org.grandmasfood.springcloud.orders.application.ports.input.OrdersServicesPort;
import org.grandmasfood.springcloud.orders.application.ports.output.OrdersPersistentPort;
import org.grandmasfood.springcloud.orders.domain.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
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
}
