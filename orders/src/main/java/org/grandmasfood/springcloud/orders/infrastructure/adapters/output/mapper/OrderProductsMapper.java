package org.grandmasfood.springcloud.orders.infrastructure.adapters.output.mapper;

import org.grandmasfood.springcloud.orders.domain.model.OrdersProducts;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.output.entities.OrdersProductsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderProductsMapper {
    OrdersProductsEntity toOrderProductsEntity(OrdersProducts ordersProducts);

    OrdersProducts toOrderProducts(OrdersProductsEntity orderProductsEntity);
}
