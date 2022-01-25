set -e

curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ -d ' {"name": "orders-connector",              "config": {"connector.class": "io.debezium.connector.postgresql.PostgresConnector", "database.hostname": "postgres", "database.port": "5432", "database.user": "postgres", "database.password": "development", "database.dbname": "postgres", "database.server.name": "lineage-server", "table.whitelist": "lineage.order"}}  '
echo "sleeping for 30s..."
sleep 30
curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ -d ' {"name": "participant-summary-connector", "config": {"connector.class": "io.debezium.connector.postgresql.PostgresConnector", "database.hostname": "postgres", "database.port": "5432", "database.user": "postgres", "database.password": "development", "database.dbname": "postgres", "database.server.name": "lineage-server", "table.whitelist": "lineage.participant_summary"}}  '
echo "sleeping for 30s..."
sleep 30
curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ -d ' {"name": "participant-type-summary-connector", "config": {"connector.class": "io.debezium.connector.postgresql.PostgresConnector", "database.hostname": "postgres", "database.port": "5432", "database.user": "postgres", "database.password": "development", "database.dbname": "postgres", "database.server.name": "lineage-server", "table.whitelist": "lineage.participant_type_summary"}}  '
echo "sleeping for 30s..."
sleep 30
curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ -d ' {"name": "participant-connector",         "config": {"connector.class": "io.debezium.connector.postgresql.PostgresConnector", "database.hostname": "postgres", "database.port": "5432", "database.user": "postgres", "database.password": "development", "database.dbname": "postgres", "database.server.name": "lineage-server", "table.whitelist": "lineage.participant"}}  '
