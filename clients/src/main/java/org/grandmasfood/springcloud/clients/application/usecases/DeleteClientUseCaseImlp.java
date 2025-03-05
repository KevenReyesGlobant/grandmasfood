package org.grandmasfood.springcloud.clients.application.usecases;


import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.domain.ports.out.ClientRepositoryPort;
import org.grandmasfood.springcloud.clients.infraestructure.entities.ClientsEntity;
import org.grandmasfood.springcloud.clients.domain.ports.in.IDeleteClientUsesCase;
import org.grandmasfood.springcloud.clients.infraestructure.repository.ClientsReposity;

import java.util.Optional;

public class DeleteClientUseCaseImlp implements IDeleteClientUsesCase {

    private final ClientRepositoryPort clientRepositoryPort;

    public DeleteClientUseCaseImlp(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public Optional<Client> deleteClientsByDocument(String document) {
        return clientRepositoryPort.deleteClientsByDocument(document);
    }
}
