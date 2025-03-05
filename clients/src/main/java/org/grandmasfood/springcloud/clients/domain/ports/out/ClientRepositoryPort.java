package org.grandmasfood.springcloud.clients.domain.ports.out;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.domain.model.AdditionalInfoClient;
import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.infraestructure.entities.ClientsEntity;

import java.util.Optional;

public interface ClientRepositoryPort {
    Client createClient(@Valid Client client);

    Optional<Client> updateClient(@Valid Client client, String document);

    Optional<Client> readCLientsActiveById(Long id);

    Optional<Client> readActiveClientsByDocument(String document);

    Optional<Client> deleteClientsByDocument(String document);

    AdditionalInfoClient getAditionalClientInfoUseCase(String clientDocument);
}
