package org.grandmasfood.springcloud.clients.application.usecases;


import org.grandmasfood.springcloud.clients.domain.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.infraestructure.entities.Clients;
import org.grandmasfood.springcloud.clients.domain.ports.in.IUpdateClienteUseCase;
import org.grandmasfood.springcloud.clients.infraestructure.repository.ClientsReposity;

import java.util.Optional;

public class UpdateClientUseCaseImlp implements IUpdateClienteUseCase {
    private final ClientsReposity clientsReposity;

    public UpdateClientUseCaseImlp(ClientsReposity clientsReposity) {
        this.clientsReposity = clientsReposity;
    }

    @Override
    public Clients updateClient(ClientsRequestDTO clientDTO, String document) {
        try {
            Optional<Clients> clientUpdate = Optional.ofNullable(clientsReposity.findClientsActiveByDocument(document));
            if (clientUpdate.isPresent()) {
                Clients client = clientUpdate.get();
                updateField(clientDTO.name(), client::setName);
                if (clientUpdate.stream().map(Clients::getEmail).findFirst().get().equals(clientDTO.email())) {
                    throw new RuntimeException("Email already exists");
                }
                updateField(clientDTO.email(), client::setEmail);
                updateField(clientDTO.phone(), client::setPhone);
                updateField(clientDTO.deliveryAddress(), client::setDeliveryAddress);
                return clientsReposity.save(client);
            }
            return null;
        } catch (RuntimeException e) {
            throw new RuntimeException("Error updating client: " + e.getMessage());
        }

    }

    private <T> void updateField(T newValue, java.util.function.Consumer<T> setter) {
        if (newValue != null) {
            setter.accept(newValue);
        }
    }
}
