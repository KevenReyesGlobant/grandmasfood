package org.grandmasfood.springcloud.clients.infraestructure.adapters.output.repository;

import org.grandmasfood.springcloud.clients.infraestructure.adapters.output.entities.ClientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientsReposity extends JpaRepository<ClientsEntity, Long> {

    @Query("select c from ClientsEntity c where c.idClient=:idClient and c.active=true")
    ClientsEntity findClientsActiveById(Long idClient);

    @Query("select c from ClientsEntity c where c.document=:document and c.active=true")
    ClientsEntity findClientsActiveByDocument(String document);


    @Query(value = "select * from ClientsEntity c where c.:column like concat('%', :value, '%') and c.active = true", nativeQuery = true)
    List<ClientsEntity> findOrderClients(@Param("column") String column, @Param("value") String value);
}
