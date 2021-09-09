package info.bowkett.lineage.model;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class RecordDescriptor {
  private final String rowId;
  private final String tableName;
  private final RecordDescriptor[] parents;

  public RecordDescriptor(String tableName) {
    this(generateRowId(), tableName);
  }

  private static String generateRowId() {
    return UUID.randomUUID().toString();
  }

  public RecordDescriptor(String rowId, String tableName) {
    this(rowId, tableName, new RecordDescriptor[0]);
  }

  public RecordDescriptor(String tableName, RecordDescriptor... parentNodes) {
    this(generateRowId(), tableName, parentNodes);
  }

  public RecordDescriptor(String rowId, String tableName, RecordDescriptor... parents) {
    this.rowId = rowId;
    this.tableName = tableName;
    this.parents = parents;
  }
}
