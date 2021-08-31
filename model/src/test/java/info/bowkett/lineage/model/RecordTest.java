package info.bowkett.lineage.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
class RecordTest {
  private static final Gson gson = new Gson();

  @Test
  void ensureItCanCreateANodeWithNoParents() {
    var node = new RecordDescriptor("tb_table_name");
    var json = gson.toJson(node);
    log.info("New record with no parents [{}]", json);
    assertEquals(-1, node.getRowId());
  }

  @Test
  void ensureItCanHandleOneParentRecord() {
    var parentNode = new RecordDescriptor(12345, "tb_parent_table_1");
    var newNode = new RecordDescriptor("tb_table_name", parentNode);
    var json = gson.toJson(newNode);
    log.info("New record with one parent [{}]", json);
    assertEquals(1, newNode.getParents().length);
  }

  @Test
  void ensureItCanHandleTwoParentRecords() {
    var parentNode = new RecordDescriptor(12345, "tb_parent_table_1");
    var parentTwoNode = new RecordDescriptor(56789, "tb_parent_table_2");
    var newNode = new RecordDescriptor("tb_table_name", parentNode, parentTwoNode);
    var json = gson.toJson(newNode);
    log.info("New record with one parent [{}]", json);
    assertEquals(2, newNode.getParents().length);
  }

}
