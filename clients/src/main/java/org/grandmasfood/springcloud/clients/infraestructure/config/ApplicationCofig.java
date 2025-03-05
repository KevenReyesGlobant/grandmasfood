package org.grandmasfood.springcloud.clients.infraestructure.config;

import org.grandmasfood.springcloud.clients.application.service.ClientsService;
import org.grandmasfood.springcloud.clients.application.usecases.*;
import org.grandmasfood.springcloud.clients.domain.ports.in.IGetAditionalClientInfoUseCase;
import org.grandmasfood.springcloud.clients.domain.ports.out.ClientRepositoryPort;
import org.grandmasfood.springcloud.clients.domain.ports.out.ExternalServicePort;
import org.grandmasfood.springcloud.clients.infraestructure.adapters.ExternalServiceAdapter;
import org.grandmasfood.springcloud.clients.infraestructure.repository.ClientRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationCofig {

    @Bean
    public ClientsService clientsService(ClientRepositoryPort clientRepositoryPort, GetAditionalInfoUseCaseImlp getAditionalInfoUseCaseImlp) {
        return new ClientsService(
                new CreateClientUseCaseImlp(clientRepositoryPort),
                new RetrieveClientUsesCaseImlp(clientRepositoryPort),
                new DeleteClientUseCaseImlp(clientRepositoryPort),
                new UpdateClientUseCaseImlp(clientRepositoryPort),
                getAditionalInfoUseCaseImlp
        );

    }

    @Bean
    public ClientRepositoryPort clientRepositoryPort(ClientRepositoryAdapter clientRepositoryAdapter) {
        return clientRepositoryAdapter;
    }

    @Bean
    public IGetAditionalClientInfoUseCase iGetAditionalClientInfoUseCase(ExternalServicePort externalServicePort) {
        return new GetAditionalInfoUseCaseImlp(externalServicePort);
    }

    @Bean
    public ExternalServicePort externalServicePort() {
        return new ExternalServiceAdapter();
    }
}
