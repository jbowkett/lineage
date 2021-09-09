CREATE TABLE IF NOT EXISTS "ORDER"
(
    row_id         varchar primary key,
    lineage        varchar null,
    ticker         varchar null,
    quantity       integer null,
    price          numeric null,
    order_date_utc timestamp null,
    buyer_id       integer null,
    seller_id      integer null
);
