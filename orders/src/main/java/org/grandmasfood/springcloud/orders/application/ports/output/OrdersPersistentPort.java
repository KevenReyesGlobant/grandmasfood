package org.grandmasfood.springcloud.orders.application.ports.output;


import feign.Param;
import org.grandmasfood.springcloud.orders.domain.model.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrdersPersistentPort {

    Order save(Order order);

    Optional<Order> findActiveByUuid(UUID uuid);

    Optional<Order> updateByUuidAndDocumentActive(@Param("clientDocument") String clientDocument, @Param("productUuid") UUID productUuid);

}
