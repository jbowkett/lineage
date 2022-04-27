package info.bowkett.lineage.cdc.listener.postgres;

import info.bowkett.lineage.cdc.listener.neo.NeoRecordDescriptor;
import info.bowkett.lineage.model.RecordDescriptor;
import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class PostgresRecordDescriptorTest {

  @Test
  void ensureItCreatesRecordsWithParentsCorrectly(){
    final var parentOneDescriptor = new RecordDescriptor("parent-row-id#3", "parent-one-tableName");
    final var parentTwoDescriptor = new RecordDescriptor("parent-row-id#2", "parent-two-tableName");
    final var recordDescriptor = new RecordDescriptor("row-id", "tableName", parentOneDescriptor, parentTwoDescriptor);
    final var allChildrenAndParents = PostgresRecordDescriptor.from(recordDescriptor);
    assertEquals("should have the parents", 4, allChildrenAndParents.size());
  }

  @Test
  void ensureItCreatesARecordWithNoParentsCorrectly(){
    final var recordDescriptor = new RecordDescriptor("row-id", "tableName");
    final var neoRecordDescriptor = NeoRecordDescriptor.from(recordDescriptor);
    assertEquals("it doesn't match", "row-id", neoRecordDescriptor.getRowId());
    assertEquals("it doesn't match", "tableName", neoRecordDescriptor.getTableName());
  }

}
