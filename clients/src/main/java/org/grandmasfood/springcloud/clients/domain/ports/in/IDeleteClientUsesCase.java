package org.grandmasfood.springcloud.clients.domain.ports.in;

import org.grandmasfood.springcloud.clients.domain.model.Client;
import org.grandmasfood.springcloud.clients.infraestructure.entities.ClientsEntity;

import java.util.Optional;

public interface IDeleteClientUsesCase {
    Optional<Client> deleteClientsByDocument(String document);
}
