package org.grandmasfood.springcloud.orders.application.services;

import org.grandmasfood.springcloud.orders.application.ports.input.OrdersServicesPort;
import org.grandmasfood.springcloud.orders.application.ports.output.OrdersPersistentPort;
import org.grandmasfood.springcloud.orders.domain.exceptions.OrderNotFoundException;
import org.grandmasfood.springcloud.orders.domain.model.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrdersServices implements OrdersServicesPort {

    private final OrdersPersistentPort ordersPersistentPort;

    public OrdersServices(OrdersPersistentPort ordersPersistentPort) {
        this.ordersPersistentPort = ordersPersistentPort;
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

}
