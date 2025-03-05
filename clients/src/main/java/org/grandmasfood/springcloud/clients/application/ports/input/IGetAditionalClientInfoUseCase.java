package org.grandmasfood.springcloud.clients.application.ports.input;

import org.grandmasfood.springcloud.clients.domain.model.AdditionalInfoClient;

public interface IGetAditionalClientInfoUseCase {

    AdditionalInfoClient getAditionalClientInfoUseCase(String clientDocument);
}
