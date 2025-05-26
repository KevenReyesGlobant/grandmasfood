package org.grandmasfood.springcloud.orders.infrastructure.adapters.output;

import org.grandmasfood.springcloud.orders.application.ports.input.IClientClientRest;
import org.grandmasfood.springcloud.orders.application.ports.input.IProductClientRest;
import org.grandmasfood.springcloud.orders.application.ports.output.OrdersPersistentPort;
import org.grandmasfood.springcloud.orders.domain.model.Client;
import org.grandmasfood.springcloud.orders.domain.model.Order;
import org.grandmasfood.springcloud.orders.domain.model.Product;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.output.mapper.OrderMapper;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.output.repository.OrdersRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class OrdersPersistentAdapter implements OrdersPersistentPort {

    private final OrdersRepository ordersRepository;
    private final OrderMapper orderMapper;
    private final IClientClientRest iClientClientRest;
    private final IProductClientRest iProductClientRest;


    public OrdersPersistentAdapter(OrdersRepository ordersRepository, OrderMapper orderMapper, IClientClientRest iClientClientRest, IProductClientRest iProductClientRest) {
        this.ordersRepository = ordersRepository;
        this.orderMapper = orderMapper;
        this.iClientClientRest = iClientClientRest;
        this.iProductClientRest = iProductClientRest;

    }

    //ToDo New endpoint GET orders

    @Override
    public Order save(Order order) {
//        ToDo Facade
        Client client_msv = iClientClientRest.listClientActiveByDocuments(order.getClientDocument());
        Product product_msv = iProductClientRest.findProductActiveByUuid(order.getProductUuid());
        order.setClientDocument(client_msv.getDocument());
        order.setProductUuid(product_msv.getUuid());
        order.setSubTotal(product_msv.getPrice() * order.getQuantity());
        order.setGrandTotal(product_msv.getPrice() * order.getQuantity() + (product_msv.getPrice() * order.getQuantity()) * 0.19);
        order.setTax(product_msv.getPrice() * order.getQuantity() * 0.19);
        if (client_msv.getDocument().matches(order.getClientDocument()) && product_msv.getUuid().equals(order.getProductUuid())) {
            return orderMapper.toOrder(ordersRepository.save(orderMapper.toOrderEntity(order)));
        }
        return null;
    }



    @Override
    public Optional<Order> findActiveByUuid(UUID uuid) {
        return Optional.ofNullable(orderMapper.toOrder(ordersRepository.findByUuidActive(uuid)));
    }


    @Override
    public Optional<Order> updateByUuidAndDocumentActive(String clientDocument, UUID productUuid) {
        return null;
    }

}
