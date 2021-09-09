-- CREATE SCHEMA  lineage;

create table "TRADE"
(
    row_id         varchar primary key,
    lineage        varchar   null,
    ticker         varchar   null,
    quantity       integer   null,
    price          numeric   null,
    order_date_utc timestamp null,
    buyer_id       integer   null,
    seller_id      integer   null
);

create table THING_ENTITY
(
    id SERIAL
);

-- CREATE SCHEMA IF NOT EXISTS lineage;
CREATE TABLE IF NOT EXISTS "ORDER"
(
    row_id         numeric null,
    lineage        varchar null,
    ticker         varchar null,
    quantity       integer null,
    price          numeric null,
    order_date_utc timestamp null,
    buyer_id       integer null,
    seller_id      integer null
);
