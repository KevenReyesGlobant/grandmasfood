package org.grandmasfood.springcloud.clients.application.service;

import org.grandmasfood.springcloud.clients.application.ports.input.ClientsServicePort;
import org.grandmasfood.springcloud.clients.application.ports.output.ClientPersistencePort;
import org.grandmasfood.springcloud.clients.domain.exceptions.ClientNotFoundException;
import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.springframework.dao.DataIntegrityViolationException;
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
                .map(savedClient -> {
                    boolean isUpdated = false;

                    isUpdated |= updateField(client.getName(), savedClient::getName, savedClient::setName);
                    isUpdated |= updateField(client.getEmail(), savedClient::getEmail, savedClient::setEmail);
                    isUpdated |= updateField(client.getDocument(), savedClient::getDocument, savedClient::setDocument);
                    isUpdated |= updateField(client.getPhone(), savedClient::getPhone, savedClient::setPhone);
                    isUpdated |= updateField(client.getDeliveryAddress(), savedClient::getDeliveryAddress, savedClient::setDeliveryAddress);

                    if (!isUpdated) {
                        throw new DataIntegrityViolationException("No fields have been updated");
                    }

                    return persistencePort.save(savedClient);
                })
                .orElseThrow(ClientNotFoundException::new);
    }

    private <T> boolean updateField(T newValue, java.util.function.Supplier<T> getter, java.util.function.Consumer<T> setter) {
        if (newValue != null && !newValue.equals(getter.get())) {
            setter.accept(newValue);
            return true;
        }
        return false;
    }


}



