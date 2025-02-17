package org.grandmasfood.springcloud.orders.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "order_id", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<OrdersProducts> ordersProducts;

    @OneToMany(mappedBy = "order_id", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<OrdersClients> ordersClients;

    @NotNull(message = "UUID cannot be null")
    private UUID uuid;

    @NotNull(message = "Creation date and time cannot be null")
    private LocalDateTime creation_date_time;

    @NotEmpty(message = "Client document cannot be empty")
    private String client_document;

    @NotEmpty(message = "Product UUID cannot be empty")
    private String product_uuid;

    @Positive(message = "Quantity must be positive")
    private int quantity;

    private String extra_info;

    @Positive(message = "Subtotal must be positive")
    private double sub_total;

    @Positive(message = "Tax must be positive")
    private double tax;

    @Positive(message = "Grand total must be positive")
    private double grand_total;

    private boolean delivered;

    private String delivery_date;

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
    }
}