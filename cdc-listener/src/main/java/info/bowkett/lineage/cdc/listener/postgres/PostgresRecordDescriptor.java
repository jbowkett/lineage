package info.bowkett.lineage.cdc.listener.postgres;

import info.bowkett.lineage.model.RecordDescriptor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
//import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.stream;
import static lombok.AccessLevel.NONE;
import static org.assertj.core.util.Arrays.isNullOrEmpty;

import org.springframework.data.relational.core.mapping.Column;
import  org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Entity
@EqualsAndHashCode/*(exclude = {"orderDateUtc"})*/
@ToString
@Slf4j
@Table(value="data_lineage")
public class PostgresRecordDescriptor implements Persistable {

  @Id
  @Setter(NONE)
  @Column("row_id")
  private String descriptorRowId;

  private String parentRowId,parentTableName, lineageChildRowId, lineageChildTableName;

  public PostgresRecordDescriptor(String parentRowId, String parentTableName, String lineageChildRowId, String lineageChildTableName) {
    this.descriptorRowId = generateRowId();
    this.parentRowId = parentRowId;
    this.parentTableName = parentTableName;
    this.lineageChildRowId = lineageChildRowId;
    this.lineageChildTableName = lineageChildTableName;
  }

  private static String generateRowId() {
    return UUID.randomUUID().toString();
  }

  public static List<PostgresRecordDescriptor> from(RecordDescriptor childRecordDescriptor) {
    final List<PostgresRecordDescriptor> allRecs = new ArrayList<>();
    recurse(childRecordDescriptor, allRecs);
    return allRecs;
  }

  private static void recurse(RecordDescriptor childRecordDescriptor, List<PostgresRecordDescriptor> allRecs) {
    if(isNullOrEmpty(childRecordDescriptor.getParents())){
      allRecs.add(new PostgresRecordDescriptor( null, null, childRecordDescriptor.getRowId(), childRecordDescriptor.getTableName()));
    }
    stream(childRecordDescriptor.getParents()).forEach(parent -> {
        allRecs.add(new PostgresRecordDescriptor(parent.getRowId(), parent.getTableName(), childRecordDescriptor.getRowId(), childRecordDescriptor.getTableName()));
        recurse(parent, allRecs);
      }
    );
  }

  @Override
  public Object getId() {
    return this.descriptorRowId;
  }

  @Override
  public boolean isNew() {
    return true;
  }
}
