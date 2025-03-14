package org.grandmasfood.springcloud.orders.infrastructure.adapters.output.repository;

import org.grandmasfood.springcloud.clients.infraestructure.adapters.output.entities.ClientsEntity;
import org.grandmasfood.springcloud.orders.infrastructure.adapters.output.entities.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface OrdersRepository extends JpaRepository<OrderEntity, Long> {

    @Query("select o from OrderEntity o where o.active=true")
    Page<OrderEntity> findOrdersActive(Pageable pageable);

    @Query("select o from OrderEntity o where o.id=:id and o.active=true")
    OrderEntity findOrdersActiveById(Long id);

    @Query("select p from OrderEntity o where o.client_document=:client_document and o.product_uuid=:product_uuid  and o.active=true")
    OrderEntity updateUuidAndDocument(String client_document, UUID product_uuid);
}
