package org.grandmasfood.springcloud.orders.repository;

import org.grandmasfood.springcloud.orders.model.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query("select o from Orders o where o.active=true")
    Page<Orders> findOrdersActive(Pageable pageable);

    @Query("select o from Orders o where o.id=:id and o.active=true")
    List<Orders> findOrdersActiveById(Long id);

}
