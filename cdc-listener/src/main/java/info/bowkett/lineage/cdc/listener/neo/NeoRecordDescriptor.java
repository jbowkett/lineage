package info.bowkett.lineage.cdc.listener.neo;
//import info.bowkett.lineage.model.RecordDescriptor;

import info.bowkett.lineage.model.RecordDescriptor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.Collections.EMPTY_LIST;
import static java.util.stream.Collectors.toList;
import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;

@AllArgsConstructor
@Node("RecordDescriptor")
@Getter
public class NeoRecordDescriptor {

  @Id
  String id;

  private final String rowId;

  private final String tableName;

  @Relationship(type = "PARENT_OF", direction = INCOMING)
  private final List<NeoRecordDescriptor> parents;

  public static NeoRecordDescriptor from(RecordDescriptor descriptor) {
    final var parents = descriptor.getParents();
    final var convertedParents = stream(parents)
        .map((parent) -> NeoRecordDescriptor.from(parent))
        .collect(toList());
    return new NeoRecordDescriptor(descriptor.getRowId(), descriptor.getRowId(), descriptor.getTableName(), convertedParents);
  }
}
