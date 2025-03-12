package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.mapper;

import org.grandmasfood.springcloud.orders.domain.model.OrdersClients;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.request.OrderClientsCreateRequestDTO;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.response.OrderClientsResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderClientsRestMapper {
    OrdersClients toOrderClients(OrderClientsCreateRequestDTO orderClientsCreateRequestDTO);
    OrderClientsResponseDTO toOrderClientsResponseDTO(OrdersClients ordersClients);
}
