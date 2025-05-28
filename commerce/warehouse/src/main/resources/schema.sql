CREATE SCHEMA IF NOT EXISTS warehouse;

CREATE TABLE IF NOT EXISTS warehouse.products
(
    product_id UUID PRIMARY KEY,
    fragile    BOOLEAN NOT NULL,
    width      DECIMAL(10, 2) NOT NULL,
    height     DECIMAL(10, 2) NOT NULL,
    depth      DECIMAL(10, 2) NOT NULL,
    weight     DECIMAL(10, 2) NOT NULL,
    quantity   BIGINT NOT NULL CHECK (quantity >= 0)
);

CREATE TABLE IF NOT EXISTS warehouse.bookings
(
    booking_id  UUID PRIMARY KEY,
    order_id    UUID NOT NULL,
    delivery_id UUID
);

CREATE TABLE IF NOT EXISTS warehouse.booking_products
(
    booking_id UUID,
    product_id UUID,
    quantity BIGINT NOT NULL,
    PRIMARY KEY (booking_id, product_id),
    FOREIGN KEY (booking_id) REFERENCES warehouse.bookings (booking_id)
);

CREATE INDEX IF NOT EXISTS idx_bookings_order_id ON warehouse.bookings(order_id);
