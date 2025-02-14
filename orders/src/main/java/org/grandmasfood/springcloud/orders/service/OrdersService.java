package org.grandmasfood.springcloud.orders.service;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.orders.config.interfaces.IOrdersServices;
import org.grandmasfood.springcloud.orders.model.dto.OrdersDTO;
import org.grandmasfood.springcloud.orders.model.entity.Orders;
import org.grandmasfood.springcloud.orders.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrdersService implements IOrdersServices {

    @Autowired
    private OrdersRepository ordersRepository;


    @Override
    public Orders createOrders(@Valid OrdersDTO ordersDTO) {
        Orders orders = new Orders();
        orders.setUuid(ordersDTO.uuid());
        orders.setCreation_date_time(ordersDTO.creation_date_time());
        orders.setClient_document(ordersDTO.client_document());
        orders.setProduct_uuid(ordersDTO.product_uuid());
        orders.setQuantity(ordersDTO.quantity());
        orders.setExtra_info(ordersDTO.extra_info());
        orders.setSub_total(ordersDTO.sub_total());
        orders.setTax(ordersDTO.tax());
        orders.setGrand_total(ordersDTO.grand_total());
        orders.setDelivered(ordersDTO.delivered());
        orders.setDelivery_date(ordersDTO.delivery_date());
        orders.setActive(ordersDTO.active());

        return ordersRepository.save(orders);

    }
}
