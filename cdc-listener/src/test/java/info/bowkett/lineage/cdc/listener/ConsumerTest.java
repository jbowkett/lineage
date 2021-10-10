package info.bowkett.lineage.cdc.listener;

import com.google.gson.Gson;
import info.bowkett.lineage.model.RecordDescriptor;
import org.junit.jupiter.api.Test;
import org.mockito.verification.VerificationMode;

import static info.bowkett.lineage.cdc.listener.Consumer.GSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ConsumerTest {

  private static final String HAPPY_PATH_MSG = """

      {
        "schema": {
          "type": "struct",
          "fields": [
            {
              "type": "struct",
              "fields": [
                {
                  "type": "string",
                  "optional": false,
                  "field": "row_id"
                },
                {
                  "type": "string",
                  "optional": true,
                  "field": "lineage"
                },
                {
                  "type": "string",
                  "optional": true,
                  "field": "ticker"
                },
                {
                  "type": "int32",
                  "optional": true,
                  "field": "quantity"
                },
                {
                  "type": "struct",
                  "fields": [
                    {
                      "type": "int32",
                      "optional": false,
                      "field": "scale"
                    },
                    {
                      "type": "bytes",
                      "optional": false,
                      "field": "value"
                    }
                  ],
                  "optional": true,
                  "name": "io.debezium.data.VariableScaleDecimal",
                  "version": 1,
                  "doc": "Variable scaled decimal",
                  "field": "price"
                },
                {
                  "type": "int64",
                  "optional": true,
                  "name": "io.debezium.time.MicroTimestamp",
                  "version": 1,
                  "field": "order_date_utc"
                },
                {
                  "type": "int32",
                  "optional": true,
                  "field": "buyer_id"
                },
                {
                  "type": "int32",
                  "optional": true,
                  "field": "seller_id"
                }
              ],
              "optional": true,
              "name": "lineage_server.lineage.order.Value",
              "field": "before"
            },
            {
              "type": "struct",
              "fields": [
                {
                  "type": "string",
                  "optional": false,
                  "field": "row_id"
                },
                {
                  "type": "string",
                  "optional": true,
                  "field": "lineage"
                },
                {
                  "type": "string",
                  "optional": true,
                  "field": "ticker"
                },
                {
                  "type": "int32",
                  "optional": true,
                  "field": "quantity"
                },
                {
                  "type": "struct",
                  "fields": [
                    {
                      "type": "int32",
                      "optional": false,
                      "field": "scale"
                    },
                    {
                      "type": "bytes",
                      "optional": false,
                      "field": "value"
                    }
                  ],
                  "optional": true,
                  "name": "io.debezium.data.VariableScaleDecimal",
                  "version": 1,
                  "doc": "Variable scaled decimal",
                  "field": "price"
                },
                {
                  "type": "int64",
                  "optional": true,
                  "name": "io.debezium.time.MicroTimestamp",
                  "version": 1,
                  "field": "order_date_utc"
                },
                {
                  "type": "int32",
                  "optional": true,
                  "field": "buyer_id"
                },
                {
                  "type": "int32",
                  "optional": true,
                  "field": "seller_id"
                }
              ],
              "optional": true,
              "name": "lineage_server.lineage.order.Value",
              "field": "after"
            },
            {
              "type": "struct",
              "fields": [
                {
                  "type": "string",
                  "optional": false,
                  "field": "version"
                },
                {
                  "type": "string",
                  "optional": false,
                  "field": "connector"
                },
                {
                  "type": "string",
                  "optional": false,
                  "field": "name"
                },
                {
                  "type": "int64",
                  "optional": false,
                  "field": "ts_ms"
                },
                {
                  "type": "string",
                  "optional": true,
                  "name": "io.debezium.data.Enum",
                  "version": 1,
                  "parameters": {
                    "allowed": "true,last,false"
                  },
                  "default": "false",
                  "field": "snapshot"
                },
                {
                  "type": "string",
                  "optional": false,
                  "field": "db"
                },
                {
                  "type": "string",
                  "optional": true,
                  "field": "sequence"
                },
                {
                  "type": "string",
                  "optional": false,
                  "field": "schema"
                },
                {
                  "type": "string",
                  "optional": false,
                  "field": "table"
                },
                {
                  "type": "int64",
                  "optional": true,
                  "field": "txId"
                },
                {
                  "type": "int64",
                  "optional": true,
                  "field": "lsn"
                },
                {
                  "type": "int64",
                  "optional": true,
                  "field": "xmin"
                }
              ],
              "optional": false,
              "name": "io.debezium.connector.postgresql.Source",
              "field": "source"
            },
            {
              "type": "string",
              "optional": false,
              "field": "op"
            },
            {
              "type": "int64",
              "optional": true,
              "field": "ts_ms"
            },
            {
              "type": "struct",
              "fields": [
                {
                  "type": "string",
                  "optional": false,
                  "field": "id"
                },
                {
                  "type": "int64",
                  "optional": false,
                  "field": "total_order"
                },
                {
                  "type": "int64",
                  "optional": false,
                  "field": "data_collection_order"
                }
              ],
              "optional": true,
              "field": "transaction"
            }
          ],
          "optional": false,
          "name": "lineage_server.lineage.order.Envelope"
        },
        "payload": {
          "before": null,
          "after": {
            "row_id": "0ff624a0-66e4-4018-839f-3c758521ac8a",
            "lineage": "{\\"rowId\\":\\"0ff624a0-66e4-4018-839f-3c758521ac8a\\",\\"tableName\\":\\"order\\",\\"parents\\":[]}",
            "ticker": "NASDAQ:AAPL",
            "quantity": 681,
            "price": {
              "scale": 2,
              "value": "OSw="
            },
            "order_date_utc": 1632730102055000,
            "buyer_id": 2,
            "seller_id": 1
          },
          "source": {
            "version": "1.6.2.Final",
            "connector": "postgresql",
            "name": "lineage-server",
            "ts_ms": 1632726502065,
            "snapshot": "false",
            "db": "postgres",
            "sequence": "[\\"24045064\\",\\"24045064\\"]",
            "schema": "lineage",
            "table": "order",
            "txId": 580,
            "lsn": 24045064,
            "xmin": null
          },
          "op": "c",
          "ts_ms": 1632726502236,
          "transaction": null
        }
      }   
      """;

  @Test
  void ensureItCanParseAValidMessage(){
    final var consumer = new Consumer(mock(DescriptorPersistence.class));
    final var parsedLineage = consumer.parseLineage(HAPPY_PATH_MSG);
    assertEquals("{\"rowId\":\"0ff624a0-66e4-4018-839f-3c758521ac8a\",\"tableName\":\"order\",\"parents\":[]}", parsedLineage);
  }

  @Test
  void ensureItCanTranslateAValidMessage(){
    final var consumer = new Consumer(mock(DescriptorPersistence.class));
    final var expected = new RecordDescriptor("0ff624a0-66e4-4018-839f-3c758521ac8a", "order");
    var result = consumer.consume(HAPPY_PATH_MSG);
    assertEquals(expected, result);
  }

  @Test
  void ensureItWillPersistAValidMessage(){
    final DescriptorPersistence persistence = mock(DescriptorPersistence.class);
    final var consumer = new Consumer(persistence);
    var result = consumer.consume(HAPPY_PATH_MSG);
    verify(persistence, once()).persist(result);
  }

  private VerificationMode once() {
    return times(1);
  }
}
