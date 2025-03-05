package org.grandmasfood.springcloud.clients.infraestructure.adapters.output;

import org.grandmasfood.springcloud.clients.application.ports.output.ClientPersistencePort;
import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.infraestructure.adapters.output.mapper.ClientMapper;
import org.grandmasfood.springcloud.clients.infraestructure.adapters.output.repository.ClientsReposity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientPersistenceAdapter implements ClientPersistencePort {

    private final ClientsReposity clientsReposity;

    private final ClientMapper clientMapper;

    public ClientPersistenceAdapter(ClientsReposity clientsReposity, ClientMapper clientMapper) {
        this.clientsReposity = clientsReposity;
        this.clientMapper = clientMapper;
    }

    @Override
    public Client createClient(Client client) {
        return clientMapper.toClient(clientsReposity.save(clientMapper.toClientEntity(client)));
    }

    @Override
    public Optional<Client> updateClient(Client client, String document) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> readCLientsActiveById(Long id) {
        return Optional.ofNullable(clientMapper.toClient(clientsReposity.findClientsActiveById(id)));

    }

    @Override
    public Optional<Client> readActiveClientsByDocument(String document) {
        return Optional.ofNullable(clientMapper.toClient(clientsReposity.findClientsActiveByDocument(document)));
    }

    @Override
    public Optional<Client> deleteClientsByDocument(String document) {
        return Optional.empty();
    }
}
