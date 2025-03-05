package org.grandmasfood.springcloud.clients.infraestructure.adapters.output.repository;


import org.grandmasfood.springcloud.clients.domain.model.AdditionalInfoClient;
import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.application.ports.output.ClientPersistencePort;
import org.grandmasfood.springcloud.clients.infraestructure.adapters.output.entities.ClientsEntity;

import java.util.Optional;

public class ClientRepositoryAdapter implements ClientPersistencePort {

    private final ClientsReposity clientsReposity;

    public ClientRepositoryAdapter(ClientsReposity clientsReposity) {
        this.clientsReposity = clientsReposity;
    }


    @Override
    public Client createClient(Client client) {
        ClientsEntity clientsEntity = ClientsEntity.fromDomainModel(client);
        ClientsEntity savedClient = clientsReposity.save(clientsEntity);
        return savedClient.toDomainModel();
    }

    @Override
    public Optional<Client> updateClient(Client client, String document) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> readCLientsActiveById(Long id) {
        return null;
    }

    @Override
    public Optional<Client> readActiveClientsByDocument(String document) {
        return null;
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
