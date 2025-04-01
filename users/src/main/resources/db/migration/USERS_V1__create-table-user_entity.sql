CREATE TABLE user_entity(
    id_user BIGINT AUTO_INCREMENT PRIMARY KEY,
    uuid BINARY(16) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_user VARCHAR(255) NOT NULL,
    active BOOLEAN,
    verification VARCHAR(255),
    token_expiry TIMESTAMP,
    email_verified BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);