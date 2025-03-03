package org.grandmasfood.springcloud.clients.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {


    private Long id;

    private UUID uuid;

    private String name;

    private String email;

    private String document;

    private String phone;

    private String deliveryAddress;
    private Boolean active;

    public Boolean setInactiveClient() {
        return active = false;
    }


}
