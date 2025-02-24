package org.grandmasfood.springcloud.orders.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Product {
    Long id;
    UUID uuid;
    String fantasyName;
    String category;
    String description;
    float price;
    boolean available;
    Boolean active;
}
