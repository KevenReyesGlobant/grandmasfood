package org.grandmasfood.springcloud.orders.infrastructure.adapters.output.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orders_clients_entity")
@Data
public class OrdersClientsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_id", unique = true)
    private Long clientId;
    @Column(name = "client_document", unique = true)
    private String clientDocument;

}
