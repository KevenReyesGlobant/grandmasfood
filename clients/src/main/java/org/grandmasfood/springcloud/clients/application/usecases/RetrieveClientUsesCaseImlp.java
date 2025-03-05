package org.grandmasfood.springcloud.clients.application.usecases;


import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.application.ports.output.ClientPersistencePort;
import org.grandmasfood.springcloud.clients.application.ports.input.IRetrieveClientUseCase;

import java.util.Optional;

public class RetrieveClientUsesCaseImlp implements IRetrieveClientUseCase {
    private final ClientPersistencePort clientPersistencePort;

    public RetrieveClientUsesCaseImlp(ClientPersistencePort clientPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
    }

    @Override
    public Optional<Client> readCLientsActiveById(Long id) {
        return clientPersistencePort.readCLientsActiveById(id);
    }

    @Override
    public Optional<Client> readActiveClientsByDocument(String document) {
        return clientPersistencePort.deleteClientsByDocument(document);
    }
}
