package org.grandmasfood.springcloud.orders.domain.model;

import lombok.Data;

import java.util.UUID;
@Data
public class Client {
    Long id;
    UUID uuid;
    String name;
    String email;
    String document;
    String phone;
    String deliveryAddress;
    Boolean active;
}
