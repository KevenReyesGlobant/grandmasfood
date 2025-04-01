CREATE TABLE orders_clients_entity (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT,
    FOREIGN KEY (order_id) REFERENCES orders_entity(id_order)
);

