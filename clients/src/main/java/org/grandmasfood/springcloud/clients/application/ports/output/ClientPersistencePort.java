package org.grandmasfood.springcloud.clients.application.ports.output;

import jakarta.validation.Valid;
import org.grandmasfood.springcloud.clients.domain.model.AdditionalInfoClient;
import org.grandmasfood.springcloud.clients.domain.model.Client;

import java.util.Optional;

public interface ClientPersistencePort {
    Client createClient(@Valid Client client);

    Optional<Client> updateClient(@Valid Client client, String document);

    Optional<Client> readCLientsActiveById(Long id);

    Optional<Client> readActiveClientsByDocument(String document);

    Optional<Client> deleteClientsByDocument(String document);

//    AdditionalInfoClient getAditionalClientInfoUseCase(String clientDocument);
}
