package org.grandmasfood.springcloud.orders.application.ports.input;


import org.grandmasfood.springcloud.orders.domain.model.Order;

import java.util.List;

public interface OrdersServicesPort {
    Order findById(Long id);

    List<Order> findAll();

    Order save(Order order);

    Order update(String document, Order order);

    Order findActiveByDocument(String document);

    Order findActiveById(Long id);

    Order deleteByDocument(String document);

}
