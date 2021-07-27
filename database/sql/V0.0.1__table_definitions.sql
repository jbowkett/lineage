CREATE SCHEMA IF NOT EXISTS lineage;
CREATE TABLE IF NOT EXISTS lineage.order(
    row_id SERIAL,
    description varchar
);
