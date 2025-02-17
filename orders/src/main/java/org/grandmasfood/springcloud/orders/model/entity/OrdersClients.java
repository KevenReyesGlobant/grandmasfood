package org.grandmasfood.springcloud.orders.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orders_clients")
@Data
public class OrdersClients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_id", unique = true)
    private Long clientId;
}
