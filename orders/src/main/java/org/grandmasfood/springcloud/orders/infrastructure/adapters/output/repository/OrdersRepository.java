package org.grandmasfood.springcloud.orders.infrastructure.adapters.output.repository;

import feign.Param;
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

    @Query("select o from OrderEntity o where o.id=:id and o.active=true")
    OrderEntity findOrdersActiveByd(Long id);

    @Query("SELECT o FROM OrderEntity o WHERE o.productUuid = :productUuid AND o.active = true")
    OrderEntity findByUuidActive(@Param("productUuid") UUID productUuid);
}
