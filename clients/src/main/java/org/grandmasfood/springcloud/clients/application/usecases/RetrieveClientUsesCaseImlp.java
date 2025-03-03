package org.grandmasfood.springcloud.clients.application.usecases;


import org.grandmasfood.springcloud.clients.domain.model.entity.Clients;
import org.grandmasfood.springcloud.clients.domain.ports.in.IRetrieveClientUseCase;
import org.grandmasfood.springcloud.clients.infraestructure.repository.ClientsReposity;

import java.util.Optional;

public class RetrieveClientUsesCaseImlp implements IRetrieveClientUseCase {
    private final ClientsReposity clientsReposity;

    public RetrieveClientUsesCaseImlp(ClientsReposity clientsReposity) {
        this.clientsReposity = clientsReposity;
    }


    @Override
    public Optional<Clients> readCLientsActiveById(Long id) {
        return Optional.ofNullable(clientsReposity.findClientsActiveById(id));
    }

    @Override
    public Optional<Clients> readActiveClientsByDocument(String document) {
        return Optional.ofNullable(clientsReposity.findClientsActiveByDocument(document.toString()));
    }
}
