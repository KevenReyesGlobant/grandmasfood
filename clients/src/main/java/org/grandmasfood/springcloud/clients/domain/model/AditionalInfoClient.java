package org.grandmasfood.springcloud.clients.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AditionalInfoClient {

    private final Long clientId;
    private final String clienName;
    private final UUID clientUuid;
    private final String clientEmail;

}
