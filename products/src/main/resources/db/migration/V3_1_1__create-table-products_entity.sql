CREATE TABLE products_entity(
    id_product BIGINT PRIMARY KEY AUTO_INCREMENT,
    uuid VARCHAR(36) NOT NULL,
    fantasy_name VARCHAR(255) NOT NULL,
    category VARCHAR(50) NOT NULL CHECK (
        category IN (
            'HAMBURGERS_AND_HOTDOGS', 'CHICKEN', 'FISH', 'MEATS',
            'DESSERTS', 'VEGAN_FOOD', 'KIDS_MEALS'
        )
    ),
    description VARCHAR(511) NOT NULL,
    price FLOAT NOT NULL CHECK (price > 0),
    available BOOLEAN DEFAULT FALSE,
    active BOOLEAN DEFAULT TRUE
);