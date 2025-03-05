package org.grandmasfood.springcloud.clients.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AdditionalInfoClient {

    private final Long clientId;
    private final String clientName;
    private final UUID clientUuid;
    private final String clientEmail;
    private final String clientDocument;

}
