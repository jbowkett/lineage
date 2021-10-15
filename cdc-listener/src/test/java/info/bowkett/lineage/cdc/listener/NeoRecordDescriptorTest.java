package info.bowkett.lineage.cdc.listener;

import info.bowkett.lineage.cdc.listener.neo.NeoRecordDescriptor;
import info.bowkett.lineage.model.RecordDescriptor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

class NeoRecordDescriptorTest {

  @Test
  void ensureItCreatesRecordsWithParentsCorrectly(){
    final var parentOneDescriptor = new RecordDescriptor("parent-row-id#3", "parent-one-tableName");
    final var parentTwoDescriptor = new RecordDescriptor("parent-row-id#2", "parent-two-tableName");
    final var recordDescriptor = new RecordDescriptor("row-id", "tableName", parentOneDescriptor, parentTwoDescriptor);
    final var neoRecordDescriptor = NeoRecordDescriptor.from(recordDescriptor);
    assertEquals("should have the parents", 2, neoRecordDescriptor.getParents().size());
  }

  @Test
  void ensureItCreatesARecordWithNoParentsCorrectly(){
    final var recordDescriptor = new RecordDescriptor("row-id", "tableName");
    final var neoRecordDescriptor = NeoRecordDescriptor.from(recordDescriptor);
    assertEquals("it doesn't match", "row-id", neoRecordDescriptor.getRowId());
    assertEquals("it doesn't match", "tableName", neoRecordDescriptor.getTableName());

  }
}
