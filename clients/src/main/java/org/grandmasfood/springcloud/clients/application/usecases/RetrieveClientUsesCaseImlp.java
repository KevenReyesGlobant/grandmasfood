package org.grandmasfood.springcloud.clients.application.usecases;


import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.domain.ports.out.ClientRepositoryPort;
import org.grandmasfood.springcloud.clients.infraestructure.entities.ClientsEntity;
import org.grandmasfood.springcloud.clients.domain.ports.in.IRetrieveClientUseCase;
import org.grandmasfood.springcloud.clients.infraestructure.repository.ClientsReposity;

import java.util.Optional;

public class RetrieveClientUsesCaseImlp implements IRetrieveClientUseCase {
    private final ClientRepositoryPort clientRepositoryPort;

    public RetrieveClientUsesCaseImlp(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public Optional<Client> readCLientsActiveById(Long id) {
        return clientRepositoryPort.readCLientsActiveById(id);
    }

    @Override
    public Optional<Client> readActiveClientsByDocument(String document) {
        return clientRepositoryPort.deleteClientsByDocument(document);
    }
}
