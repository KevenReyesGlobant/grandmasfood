package org.grandmasfood.springcloud.clients.service;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.config.interfaces.IClientServices;
import org.grandmasfood.springcloud.clients.config.uuid.GeneratedUuId;
import org.grandmasfood.springcloud.clients.model.dto.ClientsDTO;
import org.grandmasfood.springcloud.clients.model.entity.Clients;
import org.grandmasfood.springcloud.clients.repository.ClientsReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Clients> readAllActiveClients(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Clients> readCLientsById(Long id) {
        return Optional.ofNullable(clientsReposity.findClientsActiveById(id));
    }
}
