CREATE SCHEMA IF NOT EXISTS lineage;
CREATE TABLE IF NOT EXISTS lineage.order(
    row_id SERIAL,
    ticker varchar,
    quantity integer,
    price numeric,
    order_date_utc timestamp,
    buyer_id integer,
    seller_id integer

);

CREATE TABLE IF NOT EXISTS lineage.participant(
    row_id SERIAL,
    participant_id integer,
    participant_name varchar,
    participant_type varchar
);

insert into lineage.participant(participant_id, participant_name, participant_type) VALUES (1, 'Credit Suisse', 'brokerage');
insert into lineage.participant(participant_id, participant_name, participant_type) VALUES (2, 'James Bowkett', 'individual');
insert into lineage.participant(participant_id, participant_name, participant_type) VALUES (3, 'Bernie Madoff', 'individual');
insert into lineage.participant(participant_id, participant_name, participant_type) VALUES (4, 'Citigroup, Inc.', 'brokerage');
insert into lineage.participant(participant_id, participant_name, participant_type) VALUES (5, 'Long Term Capital Management', 'fund');
insert into lineage.participant(participant_id, participant_name, participant_type) VALUES (6, 'Knight Capital', 'fund');
