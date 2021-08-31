package info.bowkett.lineage.model;

import lombok.Getter;

@Getter
public class RecordDescriptor {
  private static final int UNKNOWN_ROW_ID = -1;
  private final int rowId;
  private final String tableName;
  private final RecordDescriptor[] parents;


  public RecordDescriptor(String tableName) {
    this(UNKNOWN_ROW_ID, tableName);
  }

  public RecordDescriptor(int rowId, String tableName) {
    this(rowId, tableName, new RecordDescriptor[0]);
  }

  public RecordDescriptor(String tableName, RecordDescriptor... parentNodes) {
    this(UNKNOWN_ROW_ID, tableName, parentNodes);
  }

  public RecordDescriptor(int rowId, String tableName, RecordDescriptor... parents) {
    this.rowId = rowId;
    this.tableName = tableName;
    this.parents = parents;
  }
}
