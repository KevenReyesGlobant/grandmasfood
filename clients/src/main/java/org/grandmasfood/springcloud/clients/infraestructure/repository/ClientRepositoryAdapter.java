package org.grandmasfood.springcloud.clients.infraestructure.repository;

import org.grandmasfood.springcloud.clients.domain.model.dto.ClientsRequestDTO;
import org.grandmasfood.springcloud.clients.domain.ports.out.ClientRepositoryPort;
import org.grandmasfood.springcloud.clients.infraestructure.entities.Clients;

import java.util.Optional;

public class ClientRepositoryAdapter implements ClientRepositoryPort {

    private final ClientsReposity clientsReposity;

    public ClientRepositoryAdapter(ClientsReposity clientsReposity) {
        this.clientsReposity = clientsReposity;
    }


    @Override
    public Clients createClient(ClientsRequestDTO clientDTO) {
        return null;
    }

    @Override
    public Clients updateClient(ClientsRequestDTO clientDTO, String document) {
        return null;
    }

    @Override
    public Optional<Clients> readCLientsActiveById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Clients> readActiveClientsByDocument(String document) {
        return Optional.empty();
    }

    @Override
    public Optional<Clients> deleteClientsByDocument(String document) {
        return Optional.empty();
    }
}
