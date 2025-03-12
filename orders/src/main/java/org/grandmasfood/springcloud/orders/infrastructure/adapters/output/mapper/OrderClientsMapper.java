package org.grandmasfood.springcloud.orders.infrastructure.adapters.output.mapper;

import org.grandmasfood.springcloud.orders.domain.model.OrdersClients;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.output.entities.OrdersClientsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderClientsMapper {
    OrdersClientsEntity toOrderClientsEntity(OrdersClients ordersClients);

    OrdersClients toOrderClients(OrdersClientsEntity orderClientsEntity);
}
