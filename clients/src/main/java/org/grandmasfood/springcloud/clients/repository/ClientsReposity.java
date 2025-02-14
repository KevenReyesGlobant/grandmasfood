package org.grandmasfood.springcloud.clients.repository;

import org.grandmasfood.springcloud.clients.model.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsReposity extends JpaRepository<Clients, Long> {
}
