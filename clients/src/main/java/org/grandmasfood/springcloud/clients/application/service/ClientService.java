package org.grandmasfood.springcloud.clients.application.service;

import lombok.RequiredArgsConstructor;
import org.grandmasfood.springcloud.clients.application.ports.input.*;
import org.grandmasfood.springcloud.clients.application.ports.output.ClientPersistencePort;
import org.grandmasfood.springcloud.clients.domain.exceptions.ClientNotFoundException;
import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ClientService implements ClientsServicePort {

    private final ClientPersistencePort persistencePort;

    public ClientService(ClientPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public Client findById(Long id) {
        return persistencePort.findById(id)
                .orElseThrow(ClientNotFoundException::new);
    }

    @Override
    public List<Client> findAll() {
        return persistencePort.findAll();
    }


    @Override
    public Client save(Client client) {
        return persistencePort.save(client);
    }

    @Override
    public Client update(Long id, Client client) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
//        if (persistencePort.findById(id).isEmpty()) {
//            throw new StudentNotFoundException();
//        }
//
//        persistencePort.deleteById(id);
//    }
    }
}
