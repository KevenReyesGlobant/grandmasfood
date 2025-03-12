package org.grandmasfood.springcloud.orders.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.grandmasfood.springcloud.orders.model.Client;
import org.grandmasfood.springcloud.orders.model.Product;
import org.grandmasfood.springcloud.orders.model.entity.OrdersClients;
import org.grandmasfood.springcloud.orders.model.entity.OrdersProducts;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Order {

    private Long id;
    private UUID uuid;
    private List<OrdersProducts> ordersProducts;
    private List<Product> product;
    private List<Client> client;
    private List<OrdersClients> ordersClients;
    private LocalDateTime creationDateTime;
    private String clientDocument;
    private String productUuid;
    private int quantity;
    private String extraInfo;
    private double subTotal;
    private double tax;
    private double grandTotal;
    private boolean delivered;
    private String deliveryDate;
    private Boolean active;

    public void addOrderProduct(OrdersProducts orderProduct) {
        if (ordersProducts == null) {
            ordersProducts = new ArrayList<>();
        }
        ordersProducts.add(orderProduct);
    }

    public void addOrderClient(OrdersClients orderClient) {
        if (ordersClients == null) {
            ordersClients = new ArrayList<>();
        }
        ordersClients.add(orderClient);
    }

    public void removeOrderProduct(OrdersProducts orderProduct) {
        ordersProducts.remove(orderProduct);
    }

    public void removeOrderClient(OrdersClients orderClient) {
        ordersClients.remove(orderClient);
    }

    public Order() {
        ordersClients = new ArrayList<>();
        ordersProducts = new ArrayList<>();
        client = new ArrayList<>();
        product = new ArrayList<>();
    }
}
