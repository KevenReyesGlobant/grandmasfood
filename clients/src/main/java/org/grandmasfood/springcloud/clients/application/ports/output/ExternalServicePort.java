package org.grandmasfood.springcloud.clients.application.ports.output;

import org.grandmasfood.springcloud.clients.domain.model.AdditionalInfoClient;

public interface ExternalServicePort {
    AdditionalInfoClient getAditionalClientInfo(String document);
}
