package org.grandmasfood.springcloud.clients.infraestructure.adapters.output;

import org.grandmasfood.springcloud.clients.application.ports.output.ClientPersistencePort;
import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.infraestructure.adapters.output.mapper.ClientMapper;
import org.grandmasfood.springcloud.clients.infraestructure.adapters.output.repository.ClientsReposity;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public Client save(Client client) {
        return clientMapper.toClient(clientsReposity.save(clientMapper.toClientEntity(client)));
    }

    @Override
    public Optional<Client> findActiveByDocument(String document) {
        return Optional.ofNullable(clientMapper.toClient(clientsReposity.findClientsActiveByDocument(document)));
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientsReposity.findById(id).map(clientMapper::toClient);
    }


    @Override
    public Optional<Client> findActiveById(Long id) {
        return Optional.ofNullable(clientMapper.toClient(clientsReposity.findClientsActiveById(id)));
    }

    @Override
    public List<Client> findAll() {
        return clientsReposity.findAll().stream().map(clientMapper::toClient).toList();
    }


    @Override
    public Client deleteByDocument(String document) {
        Client client = clientMapper.toClient(clientsReposity.findClientsActiveByDocument(document));
        client.setActive(false);
        return clientMapper.toClient(clientsReposity.save(clientMapper.toClientEntity(client)));
    }
}