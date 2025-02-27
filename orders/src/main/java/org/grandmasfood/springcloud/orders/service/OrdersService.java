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
import org.grandmasfood.springcloud.orders.model.entity.OrdersClients;
import org.grandmasfood.springcloud.orders.model.entity.OrdersProducts;
import org.grandmasfood.springcloud.orders.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Orders createOrders(@Valid OrdersDTO ordersDTO) {
        Orders orders = new Orders();
        orders.setUuid(generatedUuId.generateUuid());
        orders.setCreationDateTime(ordersDTO.creationDateTime());
        orders.setClientDocument(ordersDTO.clientDocument());
        orders.setProductUuid(ordersDTO.productUuid());
        orders.setQuantity(ordersDTO.quantity());
        orders.setExtraInfo(ordersDTO.extraInfo());
        orders.setSubTotal(ordersDTO.subTotal());
        orders.setTax(ordersDTO.tax());
        orders.setGrandTotal(ordersDTO.grandTotal());
        orders.setDelivered(ordersDTO.delivered());
        orders.setDeliveryDate(ordersDTO.deliveryDate());
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
        return Optional.ofNullable(ordersRepository.findOrdersActiveById(id));
    }

    @Override
    public void deleteOrdersById(Long id) {

    }

    @Override
    @Transactional
    public Optional<Client> signedClient(Client client, Long id) {
        Optional<Orders> order = ordersRepository.findById(id);
        if (order.isPresent()) {
            Client client_msv = iClientClientRest.readClientActiveById(client.getId());

            Orders orders = order.get();

            OrdersClients ordersClients = new OrdersClients();
            ordersClients.setClientId(client_msv.getId());
            ordersClients.setClientDocument(client_msv.getDocument());
            orders.addOrderClient(ordersClients);
            ordersRepository.save(orders);
            return Optional.of(client_msv);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Client> createClient(Client client, Long id) {
        Optional<Orders> order = ordersRepository.findById(id);
        if (order.isPresent()) {
            Client client_msv = iClientClientRest.createClientRest(client);

            Orders orders = order.get();

            OrdersClients ordersClients = new OrdersClients();
            ordersClients.setClientId(client_msv.getId());
            orders.addOrderClient(ordersClients);
            ordersRepository.save(orders);
            return Optional.of(client_msv);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Client> designedClient(Client client, Long id) {
        Optional<Orders> order = ordersRepository.findById(id);
        if (order.isPresent()) {
            Client client_msv = iClientClientRest.readClientActiveById(client.getId());

            Orders orders = order.get();

            OrdersClients ordersClients = new OrdersClients();
            ordersClients.setClientId(client_msv.getId());
            orders.removeOrderClient(ordersClients);
            ordersRepository.save(orders);
            return Optional.of(client_msv);
        }

        return Optional.empty();

    }

    @Override
    public Optional<Product> signedProduct(Product product, Long id) {
        Optional<Orders> order = ordersRepository.findById(id);
        if (order.isPresent()) {
            Product product_msv = iProductClientRest.readProductActiveByID(product.getId());

            Orders orders = order.get();

            OrdersProducts ordersProducts = new OrdersProducts();
            ordersProducts.setProductId(product_msv.getId());
            ordersProducts.setProductUuid(product_msv.getUuid());
            orders.addOrderProduct(ordersProducts);
            ordersRepository.save(orders);
            return Optional.of(product_msv);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Product> createProduct(Product product, Long id) {

        Optional<Orders> order = ordersRepository.findById(id);
        if (order.isPresent()) {
            Product product_msv = iProductClientRest.createProductRest(product);

            Orders orders = order.get();

            OrdersProducts ordersProducts = new OrdersProducts();
            ordersProducts.setProductId(product_msv.getId());
            orders.addOrderProduct(ordersProducts);

            ordersRepository.save(orders);
            return Optional.of(product_msv);
        }


        return Optional.empty();
    }

    @Override
    public Optional<Product> designedProduct(Product product, Long id) {
        Optional<Orders> order = ordersRepository.findById(id);
        if (order.isPresent()) {
            Product product_msv = iProductClientRest.readProductActiveByID(product.getId());

            Orders orders = order.get();

            OrdersProducts ordersProducts = new OrdersProducts();
            ordersProducts.setProductId(product_msv.getId());
            orders.removeOrderProduct(ordersProducts);

            ordersRepository.save(orders);
            return Optional.of(product_msv);
        }

        return Optional.empty();
    }
}