package org.grandmasfood.springcloud.orders.repository;

import org.grandmasfood.springcloud.orders.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
