package org.grandmasfood.springcloud.clients.infraestructure.config;

import org.grandmasfood.springcloud.clients.application.service.ClientsService;
import org.grandmasfood.springcloud.clients.application.usecases.*;
import org.grandmasfood.springcloud.clients.domain.ports.out.ClientRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationCofig {

//    @Bean
//    public ClientsService clientsService(ClientRepositoryPort clientRepositoryPort, GetAditionalInfoUseCaseImlp getAditionalInfoUseCaseImlp) {
//        return new ClientsService(
//                new CreateClientUseCaseImlp(clientRepositoryPort),
//                new RetrieveClientUsesCaseImlp(clientRepositoryPort),
//                new DeleteClientUseCaseImlp(clientRepositoryPort),
//                new UpdateClientUseCaseImlp(clientRepositoryPort),
//                getAditionalInfoUseCaseImlp
//
//        );
//
//    }
}
