package org.grandmasfood.springcloud.orders.infrastructure.adapters.output.repository;

import org.grandmasfood.springcloud.orders.infrastructure.adapters.output.entities.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface OrdersRepository extends JpaRepository<OrderEntity, Long> {

    @Query("select o from OrderEntity o where o.active=true")
    Page<OrderEntity> findOrdersActive(Pageable pageable);

    @Query("select o from OrderEntity o where o.idOrder=:idOrder and o.active=true")
    OrderEntity findOrdersActiveById(Long idOrder);

    @Query("SELECT o FROM OrderEntity o WHERE o.uuid=:uuid and o.active=true")
    OrderEntity findByUuidActive(UUID uuid);
}
