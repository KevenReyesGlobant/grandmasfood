package org.grandmasfood.springcloud.orders.application.ports.input;


import org.grandmasfood.springcloud.orders.domain.model.Order;

import java.time.LocalDateTime;
import java.util.UUID;

public interface OrdersServicesPort {


    Order save(Order order);

    Order update(String document, UUID productUuid, Order order);

    Order updateDelivered(UUID uuid, LocalDateTime timestamp);

}
