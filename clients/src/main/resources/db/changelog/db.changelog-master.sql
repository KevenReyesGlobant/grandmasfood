--liquibase formatted sql
--changeset DBA:create_clients_entity
--comment: Create clients_entity table
create TABLE clients_entity(
    id_client BIGINT AUTO_INCREMENT PRIMARY KEY,
    uuid BINARY(16) NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    document VARCHAR(24) NOT NULL,
    phone VARCHAR(10) NOT NULL,
    delivery_address VARCHAR(500) NOT NULL,
    active BOOLEAN
);

--changeset DBA:add_unique_constraints
--comment: Add unique constraints to email and document
alter table clients_entity add CONSTRAINT UK_clients_entity_email UNIQUE (email);
alter table clients_entity add CONSTRAINT UK_clients_entity_document UNIQUE (document);

--changeset DBA:add_audit_columns
--comment: Add audit columns for tracking creation and update timestamps
alter table clients_entity add column created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
alter table clients_entity add column updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;