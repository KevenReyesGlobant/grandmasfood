package org.grandmasfood.springcloud.clients.infraestructure.repository;

import org.grandmasfood.springcloud.clients.domain.model.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface ClientsReposity extends JpaRepository<Clients, Long> {

    @Query("select c from Clients c where c.id=:id and c.active=true")
    Clients findClientsActiveById(Long id);

    @Query("select c from Clients c where c.document=:document and c.active=true")
    Clients findClientsActiveByDocument(String document);
}
