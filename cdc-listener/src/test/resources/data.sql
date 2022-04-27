CREATE TABLE IF NOT EXISTS "data_lineage"
(
    row_id                   varchar,
    parent_row_id            varchar null,
    parent_table_name        varchar null,
    lineage_child_row_id     varchar,
    lineage_child_table_name varchar
);
