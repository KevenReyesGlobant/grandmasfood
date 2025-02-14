package org.grandmasfood.springcloud.orders.service;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.orders.config.interfaces.IOrdersServices;
import org.grandmasfood.springcloud.orders.model.dto.OrdersDTO;
import org.grandmasfood.springcloud.orders.model.entity.Orders;
import org.grandmasfood.springcloud.orders.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrdersService implements IOrdersServices {

    @Autowired
    private OrdersRepository ordersRepository;


    @Override
    public Orders createOrders( OrdersDTO ordersDTO) {
        Orders orders = new Orders();


        return ordersRepository.save(orders);

    }
}
