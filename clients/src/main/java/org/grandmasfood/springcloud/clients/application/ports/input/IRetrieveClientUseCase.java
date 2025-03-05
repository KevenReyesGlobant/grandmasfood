package org.grandmasfood.springcloud.clients.application.ports.input;

import org.grandmasfood.springcloud.clients.domain.model.Client;

import java.util.Optional;

public interface IRetrieveClientUseCase {

    Optional<Client> readCLientsActiveById(Long id);

    Optional<Client> readActiveClientsByDocument(String document);
}
