-- liquibase formatted sql

-- changeset author:1
-- comment: Create currency table with UUID
CREATE TABLE currency (
    id VARCHAR(36) PRIMARY KEY,          -- UUID string
    name VARCHAR(255) NOT NULL,         -- Currency name
    base_currency VARCHAR(3),           -- Base currency code (ISO 4217)
    price_change_range VARCHAR(50),     -- Price fluctuation range
    description TEXT,                   -- Detailed description
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- rollback DROP TABLE currency;

-- changeset author:2
-- comment: Create indexes
CREATE INDEX idx_currency_name ON currency(name);
CREATE INDEX idx_currency_base ON currency(base_currency);

-- rollback DROP INDEX idx_currency_name;
-- rollback DROP INDEX idx_currency_base;

-- changeset author:3
-- comment: Insert sample data
INSERT INTO currency (id, name, base_currency, price_change_range, description) VALUES
('550e8400-e29b-41d4-a716-446655440000', 'US Dollar', 'USD', '0.1-0.5%', 'Primary reserve currency'),
('3fa85f64-5717-4562-b3fc-2c963f66afa6', 'Euro', 'EUR', '0.2-0.6%', 'Official currency of EU'),
('6ec0bd7f-11c0-43da-975e-2a8ad9ebae0b', 'Japanese Yen', 'JPY', '0.3-0.8%', 'Official currency of Japan');

-- rollback DELETE FROM currency WHERE id IN (
--   '550e8400-e29b-41d4-a716-446655440000',
--   '3fa85f64-5717-4562-b3fc-2c963f66afa6',
--   '6ec0bd7f-11c0-43da-975e-2a8ad9ebae0b'
-- );

-- changeset author:4
-- comment: Add update timestamp trigger
CREATE OR REPLACE FUNCTION update_currency_timestamp()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = CURRENT_TIMESTAMP;
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER currency_update_trigger
BEFORE UPDATE ON currency
FOR EACH ROW EXECUTE FUNCTION update_currency_timestamp();

-- rollback DROP TRIGGER currency_update_trigger ON currency;
-- rollback DROP FUNCTION update_currency_timestamp;