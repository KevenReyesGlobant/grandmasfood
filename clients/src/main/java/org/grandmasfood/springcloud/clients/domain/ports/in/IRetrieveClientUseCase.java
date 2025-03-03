package org.grandmasfood.springcloud.clients.domain.ports.in;

import org.grandmasfood.springcloud.clients.domain.model.entity.Clients;

import java.util.Optional;

public interface IRetrieveClientUseCase {

    Optional<Clients> readCLientsActiveById(Long id);

    Optional<Clients> readActiveClientsByDocument(String document);
}
