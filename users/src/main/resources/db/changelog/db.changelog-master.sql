--liquibase formatted sql
--changeset DBA:create_user_entity
--comment: Create user_entity table
CREATE TABLE user_entity(
    id_user BIGINT AUTO_INCREMENT PRIMARY KEY,
    uuid BINARY(16) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_user VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT FALSE,
    verification VARCHAR(255),
    token_expiry TIMESTAMP,
    email_verified BOOLEAN NOT NULL DEFAULT FALSE
);

--changeset DBA:add_user_unique_constraints
--comment: Add unique constraint to email
ALTER TABLE user_entity ADD CONSTRAINT UK_user_entity_email UNIQUE (email);

--changeset DBA:create_user_entity_indexes
--comment: Add indexes for common search fields
CREATE INDEX IDX_user_entity_email ON user_entity(email);
CREATE INDEX IDX_user_entity_role ON user_entity(role_user);