package org.grandmasfood.springcloud.clients.infraestructure.repository;


import org.grandmasfood.springcloud.clients.domain.model.AdditionalInfoClient;
import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.domain.ports.out.ClientRepositoryPort;

import java.util.Optional;

public class ClientRepositoryAdapter implements ClientRepositoryPort {


    @Override
    public Client createClient(Client client) {
        return null;
    }

    @Override
    public Optional<Client> updateClient(Client client, String document) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> readCLientsActiveById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> readActiveClientsByDocument(String document) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> deleteClientsByDocument(String document) {
        return Optional.empty();
    }

    @Override
    public AdditionalInfoClient getAditionalClientInfoUseCase(String clientDocument) {
        return null;
    }
}
