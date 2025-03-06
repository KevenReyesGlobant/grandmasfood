package org.grandmasfood.springcloud.clients.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private Long id;
    private String name;
    private String email;
    private String document;
    private String phone;
    private String deliveryAddress;
    private Boolean active;

}
