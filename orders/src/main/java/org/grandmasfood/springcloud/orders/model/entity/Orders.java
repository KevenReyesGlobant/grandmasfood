package org.grandmasfood.springcloud.orders.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.grandmasfood.springcloud.orders.model.Client;
import org.grandmasfood.springcloud.orders.model.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Entity
@Table(name = "orders")
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrdersProducts> ordersProducts;

    @Transient
    private List<Product> product;

    @Transient
    private List<Client> client;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrdersClients> ordersClients;

    @NotNull(message = "UUID cannot be null")
    private UUID uuid;

    @NotNull(message = "Creation date and time cannot be null")
    private LocalDateTime creationDateTime;

    @NotEmpty(message = "Client document cannot be empty")
    private String clientDocument;

    @NotEmpty(message = "Product UUID cannot be empty")
    private String productUuid;

    @Positive(message = "Quantity must be positive")
    private int quantity;

    @NotEmpty(message = "Product extra info cannot be empty")
    private String extraInfo;

    @Positive(message = "Subtotal must be positive")
    private double subTotal;

    @Positive(message = "Tax must be positive")
    private double tax;

    @Positive(message = "Grand total must be positive")
    private double grandTotal;

    private boolean delivered;

    @NotEmpty(message = "Delivery date cannot be empty")
    private String deliveryDate;

    @JsonIgnore
    private Boolean active;

    public Optional<Orders> setInactiveOrders() {
        active = false;
        return Optional.empty();
    }

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

    public Orders() {
        ordersClients = new ArrayList<>();
        ordersProducts = new ArrayList<>();
        client = new ArrayList<>();
        product = new ArrayList<>();
    }
}