package org.grandmasfood.springcloud.orders.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
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
