package org.grandmasfood.springcloud.clients.application.service;

import org.grandmasfood.springcloud.clients.application.ports.input.ClientsServicePort;
import org.grandmasfood.springcloud.clients.application.ports.output.ClientPersistencePort;
import org.grandmasfood.springcloud.clients.domain.exceptions.ClientNotFoundException;
import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements ClientsServicePort {

    private final ClientPersistencePort persistencePort;

    public ClientService(ClientPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public Client save(Client client) {
        return persistencePort.save(client);
    }

    @Override
    public Client findActiveByDocument(String document) {
        return persistencePort.findActiveByDocument(document)
                .orElseThrow(ClientNotFoundException::new);
    }

    @Override
    public Client findActiveById(Long id) {
        return persistencePort.findActiveById(id)
                .orElseThrow(ClientNotFoundException::new);
    }

    @Override
    public Client deleteByDocument(String document) {
        return persistencePort.deleteByDocument(document);
    }


    @Override
    public Client update(String document, Client client) {
        return persistencePort.findActiveByDocument(document)
                .map(savedCLient -> {

                    updateField(client.getName(), savedCLient::setName);
                    updateField(client.getEmail(), savedCLient::setEmail);
                    updateField(client.getDocument(), savedCLient::setDocument);
                    updateField(client.getPhone(), savedCLient::setPhone);
                    updateField(client.getDeliveryAddress(), savedCLient::setDeliveryAddress);
                    return persistencePort.save(savedCLient);
                })
                .orElseThrow(ClientNotFoundException::new);
    }

    private <T> void updateField(T newValue, java.util.function.Consumer<T> setter) {
        if (newValue != null) {
            setter.accept(newValue);
        }
    }


}



