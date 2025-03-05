package org.grandmasfood.springcloud.clients.application.usecases;

import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.domain.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.domain.ports.out.ClientRepositoryPort;
import org.grandmasfood.springcloud.clients.infraestructure.entities.ClientsEntity;
import org.grandmasfood.springcloud.clients.domain.ports.in.ICreateClientUseCase;
import org.grandmasfood.springcloud.clients.domain.uuid.GeneratedUuId;
import org.grandmasfood.springcloud.clients.infraestructure.repository.ClientsReposity;

public class CreateClientUseCaseImlp implements ICreateClientUseCase {

    private final ClientRepositoryPort clientRepositoryPort;


    public CreateClientUseCaseImlp(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }


    @Override
    public Client createClient(Client client) {
        return clientRepositoryPort.createClient(client);
    }
}
