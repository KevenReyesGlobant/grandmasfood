package org.grandmasfood.springcloud.clients.domain.ports.in;

import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.infraestructure.entities.ClientsEntity;

import java.util.Optional;

public interface IRetrieveClientUseCase {

    Optional<Client> readCLientsActiveById(Long id);

    Optional<Client> readActiveClientsByDocument(String document);
}
