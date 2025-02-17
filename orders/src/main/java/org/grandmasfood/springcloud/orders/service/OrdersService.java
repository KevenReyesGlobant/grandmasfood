package org.grandmasfood.springcloud.orders.service;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.orders.config.interfaces.IClientClientRest;
import org.grandmasfood.springcloud.orders.config.interfaces.IOrdersServices;
import org.grandmasfood.springcloud.orders.config.interfaces.IProductClientRest;
import org.grandmasfood.springcloud.orders.config.uuid.GeneratedUuId;
import org.grandmasfood.springcloud.orders.model.Client;
import org.grandmasfood.springcloud.orders.model.Product;
import org.grandmasfood.springcloud.orders.model.dto.OrdersDTO;
import org.grandmasfood.springcloud.orders.model.entity.Orders;
import org.grandmasfood.springcloud.orders.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class OrdersService implements IOrdersServices {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private GeneratedUuId generatedUuId;

    @Autowired
    private IClientClientRest iClientClientRest;

    @Autowired
    private IProductClientRest iProductClientRest;


    @Override
    public Orders createOrders(@Valid OrdersDTO ordersDTO) {
        Orders orders = new Orders();
        orders.setUuid(generatedUuId.generateUuid());
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

    @Override
    @Transactional(readOnly = true)
    public Page<Orders> readOrders(Pageable pageable) {
        return ordersRepository.findOrdersActive(pageable);
    }

    @Override
    public Optional<Orders> readOrdersById(Long id) {
        return ordersRepository.findOrdersActiveById(id).stream().findFirst();
    }

    @Override
    public void deleteOrdersById(Long id) {

    }

    @Override
    public Optional<Client> signedClient(Client client, Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> createClient(Client client, Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> designedClient(Client client, Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> signedProduct(Product product, Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> createProduct(Product product, Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> designedProduct(Product product, Long id) {
        return Optional.empty();
    }
}