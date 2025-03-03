package org.grandmasfood.springcloud.clients.domain.ports.in;

import org.grandmasfood.springcloud.clients.infraestructure.entities.Clients;

import java.util.Optional;

public interface IDeleteClientUsesCase {
    Optional<Clients> deleteClientsByDocument(String document);
}
