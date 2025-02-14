package org.grandmasfood.springcloud.clients.service;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.config.interfaces.IClientServices;
import org.grandmasfood.springcloud.clients.model.dto.ClientsDTO;
import org.grandmasfood.springcloud.clients.model.entity.Clients;
import org.grandmasfood.springcloud.clients.repository.ClientsReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientsService implements IClientServices {
    @Autowired
    private ClientsReposity clientsReposity;

    @Transactional
    @Override
    public Clients createClient(@Valid ClientsDTO clientDTO) {
        Clients client = new Clients();
        client.setUuid(clientDTO.uuid());
        client.setName(clientDTO.name());
        client.setEmail(clientDTO.email());
        client.setDocument(clientDTO.document());
        client.setPhone(clientDTO.phone());
        client.setDeliveryAddress(clientDTO.deliveryAddress());
        return clientsReposity.save(client);

    }
}
