package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.mapper;

import org.grandmasfood.springcloud.orders.domain.model.Order;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.request.OrdersCreateRequestDTO;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.response.OrdersResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderRestMapper {

    Order toOrder(OrdersCreateRequestDTO ordersCreateRequestDTO);

    OrdersResponseDTO toOrdersResponseDTO(Order order);

}
