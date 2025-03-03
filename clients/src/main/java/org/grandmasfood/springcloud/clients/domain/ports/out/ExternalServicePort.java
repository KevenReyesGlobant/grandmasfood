package org.grandmasfood.springcloud.clients.domain.ports.out;

import org.grandmasfood.springcloud.clients.domain.model.AditionalInfoClient;

public interface ExternalServicePort {
    AditionalInfoClient getAditionalClientInfo(String document);
}
