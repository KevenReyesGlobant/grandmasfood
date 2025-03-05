package org.grandmasfood.springcloud.clients.application.usecases;


import org.grandmasfood.springcloud.clients.domain.model.AdditionalInfoClient;
import org.grandmasfood.springcloud.clients.application.ports.input.IGetAditionalClientInfoUseCase;
import org.grandmasfood.springcloud.clients.application.ports.output.ExternalServicePort;

public class GetAditionalInfoUseCaseImlp implements IGetAditionalClientInfoUseCase {

    private final ExternalServicePort externalServicePort;

    public GetAditionalInfoUseCaseImlp(ExternalServicePort externalServicePort) {
        this.externalServicePort = externalServicePort;
    }


    @Override
    public AdditionalInfoClient getAditionalClientInfoUseCase(String clientDocument) {
        return externalServicePort.getAditionalClientInfo(clientDocument);
    }
}
