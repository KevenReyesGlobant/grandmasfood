package org.grandmasfood.springcloud.clients.service;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.ClientsApplication;
import org.grandmasfood.springcloud.clients.config.interfaces.IClientServices;
import org.grandmasfood.springcloud.clients.config.uuid.GeneratedUuId;
import org.grandmasfood.springcloud.clients.model.dto.ClientsDTO;
import org.grandmasfood.springcloud.clients.model.entity.Clients;
import org.grandmasfood.springcloud.clients.repository.ClientsReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientsService implements IClientServices {
    @Autowired
    private ClientsReposity clientsReposity;

    @Autowired
    private GeneratedUuId generatedUuId;

    @Transactional
    @Override
    public Clients createClient(@Valid ClientsDTO clientDTO) {
        Clients client = new Clients();
        client.setUuid(generatedUuId.generateUuid());
        client.setName(clientDTO.name());
        client.setEmail(clientDTO.email());
        client.setDocument(clientDTO.document());
        client.setPhone(clientDTO.phone());
        client.setDeliveryAddress(clientDTO.deliveryAddress());
        client.setActive(clientDTO.active());
        return clientsReposity.save(client);

    }


    @Override
    public Clients updateClient(@Valid ClientsDTO clientDTO, @Valid String document) {

        Optional<Clients> clientUpdate = Optional.ofNullable(clientsReposity.findClientsActiveByDocument(document));
        if (clientUpdate.isPresent()) {
            Clients client = clientUpdate.get();
            updateField(clientDTO.name(), client::setName);
            updateField(clientDTO.email(), client::setEmail);
            updateField(clientDTO.document(), client::setDocument);
            updateField(clientDTO.phone(), client::setPhone);
            updateField(clientDTO.deliveryAddress(), client::setDeliveryAddress);
            updateField(clientDTO.active(), client::setActive);
            return clientsReposity.save(client);
        }
        return null;
    }

    private <T> void updateField(T newValue, java.util.function.Consumer<T> setter) {
        if (newValue != null) {
            setter.accept(newValue);
        }
    }


    @Transactional
    @Override
    public Optional<Clients> readCLientsActiveById(@Valid Long id) {
        return Optional.ofNullable(clientsReposity.findClientsActiveById(id));
    }

    @Transactional
    @Override
    public Optional<Clients> readActiveClientsByDocument(@Valid String document) {
        return Optional.ofNullable(clientsReposity.findClientsActiveByDocument(document.toString()));
    }

    @Override
    public Optional<Clients> deleteClientsByDocument(String document) {
        Clients clients = clientsReposity.findClientsActiveByDocument(document);
        if (clients != null) {
            clients.setActive(false);
            clientsReposity.save(clients);
            return Optional.of(clients);
        }
        return Optional.empty();

    }

}
