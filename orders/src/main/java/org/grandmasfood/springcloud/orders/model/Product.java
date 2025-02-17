package org.grandmasfood.springcloud.orders.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Product {
    Long id;
    UUID uuid;
    String fantasy_name;
    String category;
    String description;
    float price;
    boolean available;
    Boolean active;
}
