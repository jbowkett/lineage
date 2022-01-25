insert into lineage.participant_type_summary(
    -- with full lineage:
    select max(p.uid) as row_id,
           ('{"tableName":"participant_type_summary", "rowId" : "' || max(p.uid) || '"}') ::jsonb || jsonb_object_agg('parents', ('[' || p.lineage || ',' || ps.lineage || ']')  ::jsonb) as lineage,
           p.participant_type as participant_type,
           avg(buy_count) as avg_buy_count,
           avg(buy_total) as avg_buy_total,
           avg(sell_count) as avg_sell_count,
           avg(sell_total) as avg_sell_total
    from lineage.participant_summary ps
             inner join
         (select gen_random_uuid() :: varchar as uid, lineage, participant_id, participant_type  from lineage.participant ) as p
         on p.participant_id  = ps.participant_id
    group by p.participant_type );
