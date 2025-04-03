CREATE TABLE orders_entity (
    id_order BIGINT PRIMARY KEY AUTO_INCREMENT,
    uuid BINARY(16) NOT NULL,
    creation_date_time TIMESTAMP NOT NULL,
    client_document VARCHAR(255) NOT NULL,
    product_uuid BINARY(16) NOT NULL,
    quantity INT NOT NULL CHECK (quantity >= 1 AND quantity < 100),
    extra_info VARCHAR(511),
    sub_total DOUBLE PRECISION NOT NULL,
    tax DOUBLE PRECISION NOT NULL,
    grand_total DOUBLE PRECISION NOT NULL,
    delivered BOOLEAN DEFAULT FALSE,
    delivery_date TIMESTAMP,
    active BOOLEAN DEFAULT TRUE
);


CREATE INDEX idx_orders_uuid ON orders_entity(uuid);

CREATE INDEX idx_client_document ON orders_entity(client_document);