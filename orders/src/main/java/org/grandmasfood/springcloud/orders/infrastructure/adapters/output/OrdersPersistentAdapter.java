package org.grandmasfood.springcloud.orders.infrastructure.adapters.output;

import org.grandmasfood.springcloud.orders.application.ports.output.OrdersPersistentPort;
import org.grandmasfood.springcloud.orders.domain.model.Client;
import org.grandmasfood.springcloud.orders.domain.model.Order;
import org.grandmasfood.springcloud.orders.domain.model.Product;
import org.grandmasfood.springcloud.orders.domain.uuid.GeneratedUuId;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.output.mapper.OrderMapper;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.output.repository.OrdersRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrdersPersistentAdapter implements OrdersPersistentPort {

    private final OrdersRepository ordersRepository;
    private final OrderMapper orderMapper;
    private final GeneratedUuId generatedUuId;

    public OrdersPersistentAdapter(OrdersRepository ordersRepository, OrderMapper orderMapper, GeneratedUuId generatedUuId) {
        this.ordersRepository = ordersRepository;
        this.orderMapper = orderMapper;
        this.generatedUuId = generatedUuId;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }

    @Override
    public Order save(Order order) {
        order.setUuid(generatedUuId.generateUuid());
        return orderMapper.toOrder(ordersRepository.save(orderMapper.toOrderEntity(order)));
    }

    @Override
    public Optional<Order> findActiveByDocument(String document) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> findActiveById(Long id) {
        return Optional.empty();
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
