CREATE TABLE user_entity (
    id_user BIGINT AUTO_INCREMENT PRIMARY KEY,
    uuid BINARY(36) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role_user VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT FALSE,
    verification VARCHAR(255),
    token_expiry TIMESTAMP,
    email_verified BOOLEAN NOT NULL DEFAULT FALSE
);