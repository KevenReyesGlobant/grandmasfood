package org.grandmasfood.springcloud.clients.domain.ports.in;

import org.grandmasfood.springcloud.clients.domain.model.AdditionalInfoClient;
import org.grandmasfood.springcloud.clients.domain.model.Client;

public interface IGetAditionalClientInfoUseCase {

    AdditionalInfoClient getAditionalClientInfoUseCase(String clientDocument);
}
