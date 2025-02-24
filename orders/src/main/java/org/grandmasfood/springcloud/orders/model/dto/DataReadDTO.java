package org.grandmasfood.springcloud.orders.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DataReadDTO {

    private UUID uuid;

    private LocalDateTime creationDateTime;

    private String clientDocument;

    private UUID productUuid;

    private int quantity;

    private String extraInfomation;

    private double subTotal;

    private double tax;

    private double grandTotal;

    private Boolean delivered;

    private String deliveryDate;


}
