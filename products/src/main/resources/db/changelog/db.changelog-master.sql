--liquibase formatted sql
--changeset DBA:create_products_entity
--comment: Create products_entity table
CREATE TABLE products_entity(
    id_product BIGINT AUTO_INCREMENT PRIMARY KEY,
    uuid BINARY(16) NOT NULL,
    fantasy_name VARCHAR(255) NOT NULL,
    category VARCHAR(50) NOT NULL,
    description VARCHAR(511) NOT NULL,
    price FLOAT NOT NULL,
    available BOOLEAN NOT NULL,
    active BOOLEAN
);

--changeset DBA:add_products_unique_constraints
--comment: Add unique constraint to fantasy_name
ALTER TABLE products_entity ADD CONSTRAINT UK_products_entity_fantasy_name UNIQUE (fantasy_name);

--changeset DBA:add_price_check_constraint
--comment: Add check constraint to ensure price is positive
ALTER TABLE products_entity ADD CONSTRAINT CK_products_entity_price CHECK (price > 0);