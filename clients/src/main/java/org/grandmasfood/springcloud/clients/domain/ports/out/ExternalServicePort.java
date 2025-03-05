package org.grandmasfood.springcloud.clients.domain.ports.out;

import org.grandmasfood.springcloud.clients.domain.model.AdditionalInfoClient;

public interface ExternalServicePort {
    AdditionalInfoClient getAditionalClientInfo(String document);
}
