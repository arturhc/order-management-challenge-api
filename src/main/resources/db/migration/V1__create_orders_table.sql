-- noinspection SqlDialectInspectionForFile

CREATE TABLE orders
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
    product_id  BIGINT NOT NULL,
    quantity    INT    NOT NULL,
    price       DECIMAL(10, 2),
    status      VARCHAR(50),
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP
);

CREATE INDEX idx_customer_id ON orders (customer_id);
CREATE INDEX idx_product_id ON orders (product_id);
CREATE INDEX idx_status ON orders (status);