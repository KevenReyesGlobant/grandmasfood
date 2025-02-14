package org.grandmasfood.springcloud.orders.config.interfaces;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.orders.model.dto.OrdersDTO;
import org.grandmasfood.springcloud.orders.model.entity.Orders;

public interface IOrdersServices {
    Orders createOrders(@Valid OrdersDTO ordersDTO);
}
