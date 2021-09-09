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

insert into lineage.participant(participant_id, lineage, participant_name, participant_type) VALUES (1, '{"rowId":1,"tableName":"lineage.participant","parents":[]}', 'Credit Suisse', 'brokerage');
insert into lineage.participant(participant_id, lineage, participant_name, participant_type) VALUES (2, '{"rowId":2,"tableName":"lineage.participant","parents":[]}', 'James Bowkett', 'individual');
insert into lineage.participant(participant_id, lineage, participant_name, participant_type) VALUES (3, '{"rowId":3,"tableName":"lineage.participant","parents":[]}', 'Bernie Madoff', 'individual');
insert into lineage.participant(participant_id, lineage, participant_name, participant_type) VALUES (4, '{"rowId":4,"tableName":"lineage.participant","parents":[]}', 'Citigroup, Inc.', 'brokerage');
insert into lineage.participant(participant_id, lineage, participant_name, participant_type) VALUES (5, '{"rowId":5,"tableName":"lineage.participant","parents":[]}', 'Long Term Capital Management', 'fund');
insert into lineage.participant(participant_id, lineage, participant_name, participant_type) VALUES (6, '{"rowId":6,"tableName":"lineage.participant","parents":[]}', 'Knight Capital', 'fund');
