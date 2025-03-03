package org.grandmasfood.springcloud.clients.domain.ports.in;

import org.grandmasfood.springcloud.clients.domain.model.AditionalInfoClient;

public interface IGetAditionalClientInfoUseCase {

    AditionalInfoClient getAditionalClientInfoUseCase(String document);
}
