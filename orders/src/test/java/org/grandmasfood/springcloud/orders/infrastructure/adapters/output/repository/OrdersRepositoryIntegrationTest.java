package org.grandmasfood.springcloud.orders.infrastructure.adapters.output.repository;

import org.grandmasfood.springcloud.orders.infrastructure.adapters.output.entities.OrderEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class OrdersRepositoryIntegrationTest {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Long activeOrderId;
    private UUID activeOrderUuid;
    private Long inactiveOrderId;

    @BeforeEach
    public void createInsertData() {
        // Create an active order
        OrderEntity activeOrder = new OrderEntity();
        activeOrder.setUuid(UUID.randomUUID());
        activeOrder.setActive(true);
        activeOrder.setCreationDateTime(LocalDateTime.now());
        activeOrder.setGrandTotal(100.50F);
        activeOrder.setSubTotal(90.00F);
        activeOrder.setTax(10.50F);
        activeOrder.setQuantity(2); // Quantity must be between 1 and 99
        activeOrder.setProductUuid(UUID.randomUUID()); // Add a product UUID
        activeOrder.setClientDocument("12345678"); // Add a client document
        activeOrder.setDelivered(false);
        activeOrder = entityManager.persistAndFlush(activeOrder);
        activeOrderId = activeOrder.getIdOrder();
        activeOrderUuid = activeOrder.getUuid();

        // Create an inactive order
        OrderEntity inactiveOrder = new OrderEntity();
        inactiveOrder.setUuid(UUID.randomUUID());
        inactiveOrder.setActive(false);
        inactiveOrder.setCreationDateTime(LocalDateTime.now());
        inactiveOrder.setGrandTotal(75.25F);
        inactiveOrder.setSubTotal(68.00F);
        inactiveOrder.setTax(7.25F);
        inactiveOrder.setQuantity(1);
        inactiveOrder.setProductUuid(UUID.randomUUID());
        inactiveOrder.setClientDocument("87654321");
        inactiveOrder.setDelivered(false);
        inactiveOrder = entityManager.persistAndFlush(inactiveOrder);
        inactiveOrderId = inactiveOrder.getIdOrder();
    }

    @Test
    public void testFindOrdersActive() {
        Page<OrderEntity> activeOrders = ordersRepository.findOrdersActive(PageRequest.of(0, 10));

        assertThat(activeOrders).isNotEmpty();
        assertThat(activeOrders.getContent()).allMatch(OrderEntity::getActive);
    }

    @Test
    public void testFindOrdersActiveById() {
        OrderEntity foundOrder = ordersRepository.findOrdersActiveById(activeOrderId);

        assertThat(foundOrder).isNotNull();
        assertThat(foundOrder.getIdOrder()).isEqualTo(activeOrderId);
        assertThat(foundOrder.getActive()).isTrue();
    }

    @Test
    public void testFindOrdersActiveByIdWhenOrderNotActive() {
        OrderEntity foundOrder = ordersRepository.findOrdersActiveById(inactiveOrderId);

        assertThat(foundOrder).isNull();
    }

    @Test
    public void testFindByUuidActive() {
        OrderEntity foundOrder = ordersRepository.findByUuidActive(activeOrderUuid);

        assertThat(foundOrder).isNotNull();
        assertThat(foundOrder.getUuid()).isEqualTo(activeOrderUuid);
        assertThat(foundOrder.getActive()).isTrue();
    }

    @Test
    public void testFindByUuidActiveWhenOrderNotActive() {
        OrderEntity inactiveOrder = entityManager.find(OrderEntity.class, inactiveOrderId);
        OrderEntity foundOrder = ordersRepository.findByUuidActive(inactiveOrder.getUuid());

        assertThat(foundOrder).isNull();
    }
}