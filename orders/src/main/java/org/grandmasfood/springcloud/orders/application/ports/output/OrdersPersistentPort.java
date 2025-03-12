package org.grandmasfood.springcloud.orders.application.ports.output;


import org.grandmasfood.springcloud.orders.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrdersPersistentPort {
    Optional<Order> findById(Long id);

    List<Order> findAll();

    Order save(Order order);

    Optional<Order> findActiveByDocument(String document);

    Optional<Order> findActiveById(Long id);

    Order deleteByDocument(String document);
}
