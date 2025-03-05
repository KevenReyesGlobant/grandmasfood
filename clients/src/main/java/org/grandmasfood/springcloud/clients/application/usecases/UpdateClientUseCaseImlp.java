package org.grandmasfood.springcloud.clients.application.usecases;


import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.domain.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.domain.ports.out.ClientRepositoryPort;
import org.grandmasfood.springcloud.clients.infraestructure.entities.ClientsEntity;
import org.grandmasfood.springcloud.clients.domain.ports.in.IUpdateClienteUseCase;
import org.grandmasfood.springcloud.clients.infraestructure.repository.ClientsReposity;

import java.util.Optional;

public class UpdateClientUseCaseImlp implements IUpdateClienteUseCase {
    private final ClientRepositoryPort clientRepositoryPort;

    public UpdateClientUseCaseImlp(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }


    @Override
    public Optional<Client> updateClient(Client client, String document) {

        return clientRepositoryPort.updateClient(client, document);
    }
}
