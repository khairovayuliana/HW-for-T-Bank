-- liquibase formatted sql

-- changeset author:1
CREATE TABLE IF NOT EXISTS currencies (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    base_currency VARCHAR(3),
    price_change_range VARCHAR(50),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- rollback DROP TABLE currencies;

-- changeset author:2
CREATE INDEX idx_currency_name ON currencies(name);
CREATE INDEX idx_currency_base ON currencies(base_currency);
-- rollback DROP INDEX idx_currency_name, idx_currency_base;

-- changeset author:3 splitStatements:false
CREATE OR REPLACE FUNCTION update_currency_timestamp()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = CURRENT_TIMESTAMP;
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER currency_update_trigger
BEFORE UPDATE ON currencies
FOR EACH ROW EXECUTE FUNCTION update_currency_timestamp();
-- rollback DROP TRIGGER currency_update_trigger ON currencies; DROP FUNCTION update_currency_timestamp;