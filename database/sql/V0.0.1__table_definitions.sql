CREATE SCHEMA IF NOT EXISTS lineage;
CREATE TABLE IF NOT EXISTS lineage.order(
    row_id varchar primary key,
    lineage varchar,
    ticker varchar,
    quantity integer,
    price numeric,
    order_date_utc timestamp,
    buyer_id integer,
    seller_id integer
);

CREATE TABLE IF NOT EXISTS lineage.participant(
    row_id SERIAL,
    lineage varchar,
    participant_id integer,
    participant_name varchar,
    participant_type varchar
);

insert into lineage.participant(participant_id, lineage, participant_name, participant_type) VALUES (1, '{"rowId":1,"tableName":"participant","parents":[]}', 'Credit Suisse', 'brokerage');
insert into lineage.participant(participant_id, lineage, participant_name, participant_type) VALUES (2, '{"rowId":2,"tableName":"participant","parents":[]}', 'James Bowkett', 'individual');
insert into lineage.participant(participant_id, lineage, participant_name, participant_type) VALUES (3, '{"rowId":3,"tableName":"participant","parents":[]}', 'Bernie Madoff', 'individual');
insert into lineage.participant(participant_id, lineage, participant_name, participant_type) VALUES (4, '{"rowId":4,"tableName":"participant","parents":[]}', 'Citigroup, Inc.', 'brokerage');
insert into lineage.participant(participant_id, lineage, participant_name, participant_type) VALUES (5, '{"rowId":5,"tableName":"participant","parents":[]}', 'Long Term Capital Management', 'fund');
insert into lineage.participant(participant_id, lineage, participant_name, participant_type) VALUES (6, '{"rowId":6,"tableName":"participant","parents":[]}', 'Knight Capital', 'fund');

CREATE TABLE IF NOT EXISTS lineage.participant_summary(
    row_id varchar primary key,
    lineage varchar,
    participant_id integer,
    buy_count integer,
    buy_total decimal,
    sell_count integer,
    sell_total decimal
);

CREATE TABLE IF NOT EXISTS lineage.participant_type_summary(
    row_id varchar primary key,
    lineage varchar,
    participant_type varchar,
    avg_buy_count decimal,
    avg_buy_total decimal,
    avg_sell_count decimal,
    avg_sell_total decimal
);

CREATE TABLE IF NOT EXISTS lineage.data_lineage(
      row_id varchar,
      parent_row_id varchar null,
      parent_table_name varchar null,
      lineage_child_row_id varchar,
      lineage_child_table_name varchar
);
