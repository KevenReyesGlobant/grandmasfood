package org.grandmasfood.springcloud.clients.application.usecases;

import org.grandmasfood.springcloud.clients.domain.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.infraestructure.entities.Clients;
import org.grandmasfood.springcloud.clients.domain.ports.in.ICreateClientUseCase;
import org.grandmasfood.springcloud.clients.domain.uuid.GeneratedUuId;
import org.grandmasfood.springcloud.clients.infraestructure.repository.ClientsReposity;

public class CreateClientUseCaseImlp implements ICreateClientUseCase {

    private final ClientsReposity clientsReposity;
    private final GeneratedUuId generatedUuId;


    public CreateClientUseCaseImlp(ClientsReposity clientsReposity, GeneratedUuId generatedUuId) {
        this.clientsReposity = clientsReposity;
        this.generatedUuId = generatedUuId;
    }

    @Override
    public Clients createClient(ClientsRequestDTO clientDTO) {
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
}
