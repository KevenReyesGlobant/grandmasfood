package org.grandmasfood.springcloud.clients.infraestructure.adapters.output.repository;

import org.grandmasfood.springcloud.clients.infraestructure.adapters.output.entities.ClientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface ClientsReposity extends JpaRepository<ClientsEntity, Long> {

    @Query("select c from ClientsEntity c where c.idClient=:idClient and c.active=true")
    ClientsEntity findClientsActiveById(Long idClient);

    @Query("select c from ClientsEntity c where c.document=:document and c.active=true")
    ClientsEntity findClientsActiveByDocument (String document);
}
