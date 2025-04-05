--liquibase formatted sql

--changeset DBA:create_orders_entity
--comment: Create the main orders_entity table
CREATE TABLE orders_entity (
    id_order BIGINT AUTO_INCREMENT PRIMARY KEY,
    uuid BINARY(16) NOT NULL,
    creation_date_time DATETIME NOT NULL,
    client_document VARCHAR(24) NOT NULL,
    product_uuid BINARY(16) NOT NULL,
    quantity INT NOT NULL,
    extra_info VARCHAR(511),
    sub_total DOUBLE NOT NULL,
    tax DOUBLE NOT NULL,
    grand_total DOUBLE NOT NULL,
    delivered BOOLEAN NOT NULL DEFAULT FALSE,
    delivery_date DATETIME,
    active BOOLEAN,
    CONSTRAINT CK_orders_entity_quantity CHECK (quantity BETWEEN 1 AND 99)
);

--changeset DBA:create_orders_clients_entity
--comment: Create the orders_clients_entity junction table
CREATE TABLE orders_clients_entity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    client_document VARCHAR(24) NOT NULL,
    CONSTRAINT FK_orders_clients_entity_order_id FOREIGN KEY (order_id) REFERENCES orders_entity(id_order)
);

--changeset DBA:create_orders_products_entity
--comment: Create the orders_products_entity junction table
CREATE TABLE orders_products_entity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_uuid BINARY(16) NOT NULL,
    CONSTRAINT FK_orders_products_entity_order_id FOREIGN KEY (order_id) REFERENCES orders_entity(id_order)
);

--changeset DBA:add_orders_entity_indexes
--comment: Add indexes to improve query performance
CREATE INDEX IDX_orders_entity_client_document ON orders_entity(client_document);
CREATE INDEX IDX_orders_entity_product_uuid ON orders_entity(product_uuid);
CREATE INDEX IDX_orders_entity_delivery_date ON orders_entity(delivery_date);
CREATE INDEX IDX_orders_clients_entity_client ON orders_clients_entity(client_document);
CREATE INDEX IDX_orders_products_entity_product ON orders_products_entity(product_uuid);