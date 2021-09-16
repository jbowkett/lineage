insert into lineage.participant_summary(
    -- with full lineage:
    select max(a.uuid) as row_id, ('{"tableName":"participant_summary", "rowId" : "'|| max(a.uuid) || '"}') :: jsonb || jsonb_object_agg('parents', lineage) , participant_id,
           sum(case when a.direction = 'BUY' then a.count else 0 end) as buy_count,
           sum(case when a.direction = 'BUY' then a.total else 0 end) as buy_total,
           sum(case when a.direction = 'SELL' then a.count else 0 end) as sell_count,
           sum(case when a.direction = 'SELL' then a.total else 0 end) as sell_total
    from(
            select cast (gen_random_uuid() as varchar) as uuid, json_agg(cast(lineage as json)) as lineage, buyer_id as participant_id, 'BUY' as direction, count(*) as count, sum(o.price * quantity) as total
            from lineage.order o
            group by buyer_id, direction
            union all
            select cast (gen_random_uuid() as varchar) as uuid, json_agg(cast(lineage as json)) as lineage, seller_id  as participant_id, 'SELL' as direction, count(*) as count, sum(o.price * quantity) as total
            from lineage.order o
            group by seller_id, direction
        ) as a
    group by a.participant_id
);
