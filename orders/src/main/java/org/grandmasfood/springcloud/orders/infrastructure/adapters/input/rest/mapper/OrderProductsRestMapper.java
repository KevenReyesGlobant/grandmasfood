package org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.mapper;

import org.grandmasfood.springcloud.orders.domain.model.OrdersProducts;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.request.OrderProductsCreateRequestDTO;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.input.rest.model.response.OrderProductsResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderProductsRestMapper {
    OrdersProducts toOrderProducts(OrderProductsCreateRequestDTO orderProductsCreateRequestDTO);

    OrderProductsResponseDTO toOrderProductsResponseDTO(OrdersProducts ordersProducts);
}
