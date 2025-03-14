package org.grandmasfood.springcloud.orders.infrastructure.adapters.output;

import org.grandmasfood.springcloud.orders.application.ports.input.IClientClientRest;
import org.grandmasfood.springcloud.orders.application.ports.input.IProductClientRest;
import org.grandmasfood.springcloud.orders.application.ports.output.OrdersPersistentPort;
import org.grandmasfood.springcloud.orders.domain.model.*;
import org.grandmasfood.springcloud.orders.domain.uuid.GeneratedUuId;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.output.mapper.OrderClientsMapper;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.output.mapper.OrderMapper;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.output.mapper.OrderProductsMapper;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.output.repository.OrdersRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OrdersPersistentAdapter implements OrdersPersistentPort {

    private final OrdersRepository ordersRepository;
    private final OrderMapper orderMapper;
    private final GeneratedUuId generatedUuId;
    private final IClientClientRest iClientClientRest;
    private final IProductClientRest iProductClientRest;


    public OrdersPersistentAdapter(OrdersRepository ordersRepository, OrderMapper orderMapper, GeneratedUuId generatedUuId, IClientClientRest iClientClientRest, IProductClientRest iProductClientRest, OrderClientsMapper orderClientsMapper, OrderProductsMapper orderProductsMapper) {
        this.ordersRepository = ordersRepository;
        this.orderMapper = orderMapper;
        this.generatedUuId = generatedUuId;
        this.iClientClientRest = iClientClientRest;
        this.iProductClientRest = iProductClientRest;

    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }

    @Override
    public Order save(Order order) {
        Client client_msv = iClientClientRest.listClientActiveByDocuments(order.getClientDocument());
        Product product_msv = iProductClientRest.findProductActiveByUuid(order.getProductUuid());
        order.setClientDocument(client_msv.getDocument());
//        order.setCreationDateTime(order.getCreationDateTime());
        order.setProductUuid(product_msv.getUuid());
        order.setUuid(generatedUuId.generateUuid());
        order.setSubTotal(product_msv.getPrice() * order.getQuantity());
        order.setGrandTotal(product_msv.getPrice() * order.getQuantity() + (product_msv.getPrice() * order.getQuantity()) * 0.19);
        order.setTax(product_msv.getPrice() * order.getQuantity() * 0.19);
        if (client_msv.getDocument().matches(order.getClientDocument()) && product_msv.getUuid().equals(order.getProductUuid())) {
            return orderMapper.toOrder(ordersRepository.save(orderMapper.toOrderEntity(order)));
        }
        return null;
    }

    @Override
    public Optional<Order> findActiveByDocument(String document) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> findActiveById(Long id) {
        return Optional.ofNullable(orderMapper.toOrder(ordersRepository.findOrdersActiveById(id)));
    }

    @Override
    public Optional<Order> findActiveByUuid(UUID uuid) {
        return Optional.ofNullable(orderMapper.toOrder(ordersRepository.findByUuidActive(uuid)));
    }

    @Override
    public Order deleteByDocument(String document) {
        return null;
    }

    @Override
    public Optional<Order> updateByUuidAndDocumentActive(String clientDocument, UUID productUuid) {
        return null;
    }

    @Override
    public Optional<Client> signedClient(Client client, Long id) {
        Optional<Order> order = Optional.ofNullable(orderMapper.toOrder(ordersRepository.findOrdersActiveById(id)));
        if (order.isPresent()) {
            Client client_msv = iClientClientRest.readClientActiveById(client.getId());
            Order orders = order.get();

            OrdersClients ordersClients = new OrdersClients();
            ordersClients.setClientId(client_msv.getId());
            ordersClients.setClientDocument(client_msv.getDocument());
            orders.addOrderClient(ordersClients);
            orderMapper.toOrder(ordersRepository.save(orderMapper.toOrderEntity(orders)));
            return Optional.of(client_msv);
        }

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
        Optional<Order> order = Optional.ofNullable(orderMapper.toOrder(ordersRepository.findOrdersActiveById(id)));
        if (order.isPresent()) {
            Product product_msv = iProductClientRest.readProductActiveByID(product.getId());

            Order orders = order.get();

            OrdersProducts ordersProducts = new OrdersProducts();
            ordersProducts.setProductId(product_msv.getId());
            ordersProducts.setProductUuid(product_msv.getUuid());
            orders.addOrderProduct(ordersProducts);
            orderMapper.toOrder(ordersRepository.save(orderMapper.toOrderEntity(orders)));
            return Optional.of(product_msv);
        }

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
