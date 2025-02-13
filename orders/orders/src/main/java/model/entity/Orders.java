package model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data

public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private LocalDateTime creationDateTime;
    private String clientDocument;
    private String productUuid;
    private int quantity;
    private String extraInformation;
    private float subTotal;
    private float tax;
    private float grandTotal;
    private boolean delivered;
    private LocalDateTime deliveredDate;

}
