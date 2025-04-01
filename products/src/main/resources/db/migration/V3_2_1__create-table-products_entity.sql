create type product_category as ENUM (
    'HAMBURGERS_AND_HOTDOGS',
    'CHICKEN',
    'FISH',
    'MEATS',
    'DESSERTS',
    'VEGAN_FOOD',
    'KIDS_MEALS'
);

CREATE TABLE products_entity (
    id_product BIGINT PRIMARY KEY AUTO_INCREMENT,
    uuid VARCHAR(36) NOT NULL,
    fantasy_name VARCHAR(255) NOT NULL,
    category product_category,
    description VARCHAR(511) NOT NULL,
    price FLOAT NOT NULL CHECK (price > 0),
    available BOOLEAN DEFAULT FALSE,
    active BOOLEAN DEFAULT TRUE
);

CREATE INDEX idx_products_uuid ON products_entity(uuid);

CREATE UNIQUE INDEX idx_unique_fantasy_name ON products_entity(fantasy_name);