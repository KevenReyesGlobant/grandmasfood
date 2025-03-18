package org.grandmasfood.springcloud.orders.infrastructure.adapters.output.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.grandmasfood.springcloud.orders.domain.model.Client;
import org.grandmasfood.springcloud.orders.domain.model.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders_entity")
@Data
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long idOrder;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrdersProductsEntity> ordersProducts;

    @Transient
    private List<Product> product;

    @Transient
    private List<Client> client;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrdersClientsEntity> ordersClients;
    private UUID uuid;
    private LocalDateTime creationDateTime;
    private String clientDocument;
    private UUID productUuid;
    @Positive(message = "Quantity must be positive")
    private int quantity;
    @NotEmpty(message = "Product extra info cannot be empty")
    private String extraInfo;
    private double subTotal;
    private double tax;
    private double grandTotal;

    private boolean delivered;

    private LocalDateTime deliveryDate;

    @JsonIgnore
    private Boolean active;

    @PrePersist
    public void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}
