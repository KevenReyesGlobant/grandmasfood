package org.grandmasfood.springcloud.clients.infraestructure.adapters;

import org.grandmasfood.springcloud.clients.domain.model.AdditionalInfoClient;
import org.grandmasfood.springcloud.clients.domain.ports.out.ExternalServicePort;

public class ExternalServiceAdapter implements ExternalServicePort {
    @Override
    public AdditionalInfoClient getAditionalClientInfo(String document) {
        return null;
    }
}
