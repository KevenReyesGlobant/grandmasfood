package org.grandmasfood.springcloud.orders.application.services;

import org.grandmasfood.springcloud.orders.application.ports.input.OrdersServicesPort;
import org.grandmasfood.springcloud.orders.application.ports.output.OrdersPersistentPort;
import org.grandmasfood.springcloud.orders.domain.exceptions.OrderNotFoundException;
import org.grandmasfood.springcloud.orders.domain.model.Client;
import org.grandmasfood.springcloud.orders.domain.model.Order;
import org.grandmasfood.springcloud.orders.domain.model.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public Order update(String document, UUID productUuid, Order order) {
        return ordersPersistentPort.updateByUuidAndDocumentActive(document, productUuid).map(updateOrder -> {

                    updateField(order.getQuantity(), updateOrder::setQuantity);
                    updateField(order.getDeliveryDate(), updateOrder::setDeliveryDate);
                    updateField(order.getExtraInfo(), updateOrder::setExtraInfo);
                    return ordersPersistentPort.save(updateOrder);

                })
                .orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public Order updateDelivered(UUID uuid, LocalDateTime timestamp) {
        return ordersPersistentPort.findActiveByUuid(uuid).map(updateDelivered -> {
            updateField(true, updateDelivered::setDelivered);
            updateField(timestamp, updateDelivered::setDeliveryDate);
            return ordersPersistentPort.save(updateDelivered);
        }).orElseThrow(OrderNotFoundException::new);
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
    public Order findActiveByUuid(UUID uuid) {
        return ordersPersistentPort.findActiveByUuid(uuid).orElseThrow(OrderNotFoundException::new);
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
